package com.noteanalyzer.utility;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Base64;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;

import com.noteanalyzer.mvc.model.NoteInputFormModel;
import com.noteanalyzer.security.security.model.UserContext;

public class NoteUtility {

	protected static final String RESET_TOKEN_SEP = "!#$^";

	public static File convert(MultipartFile file) throws IOException {
		File convFile = new File(file.getOriginalFilename());
		convFile.createNewFile();
		FileOutputStream fos = new FileOutputStream(convFile);
		fos.write(file.getBytes());
		fos.close();
		return convFile;
	}

	public static String encodeResetToken(String userName, String token) {
		return Base64.getEncoder().encodeToString((userName + RESET_TOKEN_SEP + token).getBytes());
	}

	public static String getUserNameFromResetToken(String token) {
		String tokenStr = new String(Base64.getDecoder().decode(token));
		return StringUtils.substringBefore(tokenStr, RESET_TOKEN_SEP);
	}

	public static String decodeResetToken(String token) {
		String tokenStr = new String(Base64.getDecoder().decode(token));
		return StringUtils.substringAfter(tokenStr, RESET_TOKEN_SEP);
	}

	public static String getLoggedInUserName() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userName;
		if (principal instanceof UserContext) {
			userName = ((UserContext) principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}

	public static String validateInputModel(NoteInputFormModel noteInputFormModel) {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<NoteInputFormModel>> violations = validator.validate(noteInputFormModel);
		if (!violations.isEmpty()) {
			StringBuilder errorMessage = new StringBuilder();
			for (ConstraintViolation<NoteInputFormModel> model : violations) {
				errorMessage.append(", ").append(model.getMessage());
			}
			return errorMessage.toString();
		}
		return null;
	}

	/**
	 * method to convert Document to String
	 * 
	 * @param doc
	 * @return
	 */
	public static String getStringFromDocument(Document doc) {
		try {
			if (doc == null) {
				return null;
			}
			DOMSource domSource = new DOMSource(doc);
			StringWriter writer = new StringWriter();
			StreamResult result = new StreamResult(writer);
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			transformer.transform(domSource, result);
			return writer.toString();
		} catch (TransformerException ex) {
			ex.printStackTrace();
			return null;
		}
	}
}
