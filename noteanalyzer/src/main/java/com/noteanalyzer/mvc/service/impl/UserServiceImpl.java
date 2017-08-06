package com.noteanalyzer.mvc.service.impl;

import static com.noteanalyzer.mvc.constant.NoteConstant.*;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.noteanalyzer.dao.GenericDao;
import com.noteanalyzer.entity.notes.Parameters;
import com.noteanalyzer.entity.user.User;
import com.noteanalyzer.mvc.model.UserModel;
import com.noteanalyzer.mvc.service.NoteService;
import com.noteanalyzer.mvc.service.UserService;
import com.noteanalyzer.utility.ConverterUtility;

import io.jsonwebtoken.lang.Collections;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	GenericDao genericDao;

	@Autowired
	NoteService noteService;

	@Autowired
	private BCryptPasswordEncoder encoder;

	/**
	 * @return the encoder
	 */
	public BCryptPasswordEncoder getEncoder() {
		return encoder;
	}

	public GenericDao getGenericDao() {
		return genericDao;
	}

	public void setGenericDao(GenericDao genericDao) {
		this.genericDao = genericDao;
	}

	public void createUser(UserModel user) {
		genericDao.create(ConverterUtility.convertUserModelToUserEntity(user, encoder));
	}

	public Optional<User> getActiveUser(String userName) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("userName", StringUtils.lowerCase(userName));
		List<User> userList = genericDao.getResultByNamedQuery(User.class, User.GET_ACTIVE_USER_DETAILS, parameters);
		if (Collections.isEmpty(userList)) {
			return Optional.empty();
		}
		return Optional.of(userList.get(0));
	}

	public Optional<User> getUser(String userName) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("userName", StringUtils.lowerCase(userName));
		List<User> userList = genericDao.getResultByNamedQuery(User.class, User.GET_USER_DETAILS, parameters);
		if (Collections.isEmpty(userList)) {
			return Optional.empty();
		}
		return Optional.of(userList.get(0));
	}

	public Optional<User> getInActiveUser(String userName) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("userName", StringUtils.lowerCase(userName));
		List<User> userList = genericDao.getResultByNamedQuery(User.class, User.GET_IN_ACTIVE_USER_DETAILS, parameters);
		if (Collections.isEmpty(userList)) {
			return Optional.empty();
		}
		return Optional.of(userList.get(0));
	}

	@Override
	public Optional<UserModel> getByUsername(String userName) {
		Optional<User> userOptional = getActiveUser(userName);
		if (userOptional.isPresent()) {
			UserModel userModel = ConverterUtility.convertUserToUserModel(userOptional.get());
			return Optional.of(userModel);
		}
		return Optional.empty();
	}

	@Override
	public Optional<UserModel> resetUserPassword(String userName) {

		Optional<User> userOpt = getActiveUser(userName);
		if (!userOpt.isPresent()) {
			return Optional.empty();
		}
		User user = userOpt.get();
		user.setResetToken(RandomStringUtils.randomAlphanumeric(40).toUpperCase());
		user.setResetTokenCreationTime(ZonedDateTime.now());
		User updatedUser = genericDao.update(user);
		return Optional.of(ConverterUtility.convertUserToUserModel(updatedUser));
	}

	@Override
	public Optional<UserModel> changePassword(UserModel inputUser, boolean isResetTokenNotRequired) {
		Optional<User> userOpt = getActiveUser(inputUser.getEmail());
		if (userOpt.isPresent()) {
			User user = userOpt.get();
			if (isResetTokenNotRequired || inputUser.getResetToken().equals(user.getResetToken())) {
				user.setPassword(encoder.encode(inputUser.getPassword()));
				user.setResetTokenCreationTime(ZonedDateTime.now());
				User updatedUser = genericDao.update(user);
				return Optional.of(ConverterUtility.convertUserToUserModel(updatedUser));
			}
		}
		return Optional.empty();
	}

	@Override
	public Optional<UserModel> updateUser(UserModel inputUser) {
		Optional<User> userOptional = getActiveUser(inputUser.getEmail());
		if (userOptional.isPresent()) {
			User user = userOptional.get();
			user.setDisplayName(inputUser.getDisplayName());
			user.setContactNumber(inputUser.getPhoneNumber());
			user.setStreet(inputUser.getStreetAddress());
			user.setCity(inputUser.getSelCity());
			user.setState(inputUser.getSelState());
			user.setZipcode(inputUser.getZipCode());
			User updatedUser = genericDao.update(user);
			return Optional.of(ConverterUtility.convertUserToUserModel(updatedUser));
		}
		return Optional.empty();
	}

	public Optional<UserModel> getByUsernameWithPassword(String userName) {
		Optional<User> userOptional = getUser(userName);
		if (userOptional.isPresent()) {
			UserModel userModel = ConverterUtility.convertUserToUserModel(userOptional.get());
			userModel.setPassword(userOptional.get().getPassword());
			return Optional.of(userModel);
		}
		return Optional.empty();
	}

	@Override
	public Optional<UserModel> verifyUser(UserModel inputUser) {
		Optional<User> userOpt = getInActiveUser(inputUser.getEmail());
		if (userOpt.isPresent()) {
			User user = userOpt.get();
			if (inputUser.getVerificationToken().equals(user.getVerificationToken())) {
				user.setIsActive(ACTIVE_USER_FLAG);
				user.setVerificationTokenCreationTime(ZonedDateTime.now());
				User updatedUser = genericDao.update(user);
				return Optional.of(ConverterUtility.convertUserToUserModel(updatedUser));
			}
		}
		return Optional.empty();
	}

	@Override
	public String getUserStatus(String userName) {
		Optional<User> userOptional = getUser(userName);
		if (userOptional.isPresent()) {
			User user = userOptional.get();
			return user.getIsActive();
		}
		return null;
	}

	@Override
	public void updateUnsuccessfullAttempt(String loginStatus, long userId, String userEmailId) {
		User user = genericDao.getById(User.class, userId);
		if (user != null) {
			Parameters param = noteService.getParameterValue("MAX_LOGIN_ATTEMPTS", null);
			int maxAttemptAllowed = (int) ((param != null && param.getParameterValue() != null)
					? Integer.valueOf(param.getParameterValue()).intValue() : MAX_UNSUCCESSFUL_ATTEMPT);

			if (LOGIN_FAIL.equalsIgnoreCase(loginStatus)) {
				long unsuccessfulLoginAttempts = user.getUnsuccessfulLoginAttempts() + 1;
				if (unsuccessfulLoginAttempts > maxAttemptAllowed) {
					user.setIsActive(BLOCK_USER_FLAG);
				}
				user.setUnsuccessfulLoginAttempts(unsuccessfulLoginAttempts);
			} else if (LOGIN_SUCCESS.equalsIgnoreCase(loginStatus)) {
				user.setUnsuccessfulLoginAttempts(new Long(0));
				user.setIsActive(ACTIVE_USER_FLAG);
			}
			genericDao.update(user);
		}

	}

}
