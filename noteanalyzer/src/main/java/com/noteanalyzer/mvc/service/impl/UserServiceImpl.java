package com.noteanalyzer.mvc.service.impl;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.noteanalyzer.dao.GenericDao;
import com.noteanalyzer.entity.user.User;
import com.noteanalyzer.mvc.model.UserModel;
import com.noteanalyzer.mvc.service.UserService;
import com.noteanalyzer.utility.ConverterUtility;

import io.jsonwebtoken.lang.Collections;

@Service("userService")
public class UserServiceImpl implements UserService {


	@Autowired
	GenericDao genericDao;
	
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
		genericDao.create(ConverterUtility.convertUserModelToUserEntity(user,encoder));
	}

	public Optional<User> getUser(String userName) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("userName", userName);
		List<User> userList = genericDao.getResultByNamedQuery(User.class, User.GET_USER_DETAILS, parameters);
		if (Collections.isEmpty(userList)) {
			return Optional.empty();
		}
		return Optional.of(userList.get(0));
	}

	@Override
	public Optional<UserModel> getByUsername(String userName) {
		Optional<User> userOptional = getUser(userName);
		if (userOptional.isPresent()) {
			UserModel userModel = ConverterUtility.convertUserToUserModel(userOptional.get());
			return Optional.of(userModel);
		}
		return Optional.empty();		
	}

	@Override
	public Optional<UserModel> resetUserPassword(String userName) {

		Optional<User> userOpt = getUser(userName);
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
		Optional<User> userOpt = getUser(inputUser.getEmail());
		if (userOpt.isPresent()) {
			User user = userOpt.get();
			if (isResetTokenNotRequired || inputUser.getResetToken().equals(user.getResetToken())) {
				user.setPassword(encoder.encode(inputUser.getPassword()));
				User updatedUser = genericDao.update(user);
				return Optional.of(ConverterUtility.convertUserToUserModel(updatedUser));
			}
		}
		return Optional.empty();
	}

	@Override
	public Optional<UserModel> updateUser(UserModel inputUser) {
		Optional<User> userOptional = getUser(inputUser.getEmail());
		if(userOptional.isPresent()){
			User user = userOptional.get();
			user.setFirstName(inputUser.getEmail());
			user.setContactNumber(inputUser.getPhoneNumber());
			User updatedUser = genericDao.update(user);
			return Optional.of(ConverterUtility.convertUserToUserModel(updatedUser));
		}
		return Optional.empty();
	}

	public Optional<UserModel> getByUsernameWithPassword(String username) {
		Optional<User> userOptional = getUser(username);
		if(userOptional.isPresent()){
			UserModel userModel = ConverterUtility.convertUserToUserModel(userOptional.get());
			userModel.setPassword(userOptional.get().getPassword());
			return Optional.of(userModel);
		}
		return Optional.empty();
	}



}
