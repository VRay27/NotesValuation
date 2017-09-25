package com.noteanalyzer.mvc.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.noteanalyzer.entity.user.UserSubscriptions;
import com.noteanalyzer.mvc.model.AddressModel;
import com.noteanalyzer.mvc.model.LoanTypeModel;
import com.noteanalyzer.mvc.model.NoteDashboardModel;
import com.noteanalyzer.mvc.model.NoteDetailModel;
import com.noteanalyzer.mvc.model.NoteInputFormModel;
import com.noteanalyzer.mvc.model.NoteTypeModel;
import com.noteanalyzer.mvc.model.PropertyTypeModel;
import com.noteanalyzer.mvc.model.RequestStatusModel;
import com.noteanalyzer.mvc.model.UserModel;
import com.noteanalyzer.mvc.service.NoteService;
import com.noteanalyzer.mvc.service.UserService;
import com.noteanalyzer.utility.NoteUtility;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;
import au.com.bytecode.opencsv.bean.CsvToBean;

/**
 * This class is responsible for all communication with UI for Notes related activity. This is restful webservice can be called from any UI or third party as and when needed.
 * @author Arvind Ray
 *
 */
@RestController
public class NoteRestController {

	@Autowired
	NoteService noteService;

	@Autowired
	UserService userService;

	private final static Logger LOG = Logger.getLogger(NoteRestController.class.getName());

	/**
	 * @return the userService
	 */
	public UserService getUserService() {
		return userService;
	}

	/**
	 * @param userService
	 *            the userService to set
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * @return the noteService
	 */
	public NoteService getNoteService() {
		return noteService;
	}

	/**
	 * @param noteService
	 *            the noteService to set
	 */
	public void setNoteService(NoteService noteService) {
		this.noteService = noteService;
	}

	/**
	 * This method will fetch the details of zipcode given by user and populate
	 * it with city and state details. It will also give list of note type and
	 * property type.
	 * 
	 * @param noteInputFormModel
	 * @return
	 */
	@RequestMapping(value = "/analyzeNote", method = RequestMethod.POST)
	public ResponseEntity<NoteInputFormModel> analyzeNote(@RequestBody NoteInputFormModel noteInputFormModel) {
		String zipCode = noteInputFormModel.getZipCode();
		if (StringUtils.isEmpty(zipCode)) {
			noteInputFormModel.setErrorMessage("Zip code is mandtory to process your request ");
			return new ResponseEntity<NoteInputFormModel>(noteInputFormModel, HttpStatus.BAD_REQUEST);
		}
		LOG.info("Fetching Zip Code details with zipCode " + zipCode);
		Optional<AddressModel> address = noteService.getZipCodeDetails(zipCode);
		if (!address.isPresent()) {
			noteInputFormModel.setErrorMessage("Unable to find detail of zip code " + zipCode);
			return new ResponseEntity<NoteInputFormModel>(noteInputFormModel, HttpStatus.NOT_FOUND);
		}
		AddressModel addressModel = address.get();
		noteInputFormModel.setAddressModel(addressModel);
		noteInputFormModel.setZipCode(zipCode);

		Optional<List<LoanTypeModel>> loanTypeModelList = noteService.getLoanType();
		if (loanTypeModelList.isPresent()) {
			List<LoanTypeModel> loanTypeList = loanTypeModelList.get();
			noteInputFormModel.setLoanTypeList(loanTypeList);
		}
		
		Optional<List<NoteTypeModel>> noteTypeModelList = noteService.getAllNoteType();
		if (noteTypeModelList.isPresent()) {
			List<NoteTypeModel> noteTypeList = noteTypeModelList.get();
			noteInputFormModel.setNoteTypeList(noteTypeList);
		}

		Optional<List<PropertyTypeModel>> propTypeModelList = noteService.getPropertyType();
		if (propTypeModelList.isPresent()) {
			noteInputFormModel.setPropTypeList(propTypeModelList.get());
		}

		return new ResponseEntity<NoteInputFormModel>(noteInputFormModel, HttpStatus.OK);

	}

	/**
	 * This method will create note with given input details and associate it
	 * with logged in user.
	 * 
	 * @param noteInputFormModel
	 * @return
	 */
	@RequestMapping(value = "/api/createNote", method = RequestMethod.POST)
	public ResponseEntity<RequestStatusModel> createNote(@RequestBody NoteInputFormModel noteInputFormModel) {
		RequestStatusModel statusModel = new RequestStatusModel();
		String userName = NoteUtility.getLoggedInUserName();
		Optional<UserModel> loggedInuser = userService.getByUsername(userName);
		if (loggedInuser.isPresent()) {
			noteInputFormModel.setUserId(loggedInuser.get().getUserId());
		} else {
			LOG.severe("Invalid logged in user name");
			statusModel.setErrorMessage("Invalid logged in user");
			return new ResponseEntity<RequestStatusModel>(statusModel, HttpStatus.UNAUTHORIZED);
		}

		String errorMessage = NoteUtility.validateInputModel(noteInputFormModel);
		if (StringUtils.isNotBlank(errorMessage)) {
			statusModel.setErrorMessage(errorMessage);
			return new ResponseEntity<RequestStatusModel>(statusModel, HttpStatus.BAD_REQUEST);
		}

		try {
			noteService.createNote(noteInputFormModel);
		} catch (ParseException e) {
			LOG.log(Level.SEVERE, "Unable to parse input message", e.getCause());
			statusModel.setErrorMessage("Unable to parse input message");
			return new ResponseEntity<RequestStatusModel>(statusModel, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<RequestStatusModel>(HttpStatus.OK);
	}

	/**
	 * This method will enable user to download a template for bulk create note
	 * upload.
	 * 
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/templateDownload", method = RequestMethod.GET)
	public void downloadFile(HttpServletResponse response) throws IOException {

		File file = null;

		// if(type.equalsIgnoreCase("internal")){
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		file = new File(classloader.getResource("noteTemplate.csv").getFile());
		// }else{
		// file = new File(EXTERNAL_FILE_PATH);
		// }

		/*
		 * if(!file.exists()){ String errorMessage =
		 * "Sorry. The file you are looking for does not exist";
		 * System.out.println(errorMessage); OutputStream outputStream =
		 * response.getOutputStream();
		 * outputStream.write(errorMessage.getBytes(Charset.forName("UTF-8")));
		 * outputStream.close(); return; }
		 */
		String mimeType = URLConnection.guessContentTypeFromName(file.getName());
		if (mimeType == null) {
			System.out.println("mimetype is not detectable, will take default");
			mimeType = "application/octet-stream";
		}

		System.out.println("mimetype : " + mimeType);

		response.setContentType(mimeType);

		/*
		 * "Content-Disposition : inline" will show viewable types [like
		 * images/text/pdf/anything viewable by browser] right on browser while
		 * others(zip e.g) will be directly downloaded [may provide save as
		 * popup, based on your browser setting.]
		 */
		// response.setHeader("Content-Disposition", String.format("inline;
		// filename=\"" + file.getName() +"\""));

		/*
		 * "Content-Disposition : attachment" will be directly download, may
		 * provide save as popup, based on your browser setting
		 */
		response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));

		response.setContentLength((int) file.length());

		InputStream inputStream = new BufferedInputStream(new FileInputStream(file));

		// Copy bytes from source to destination(outputstream in this example),
		// closes both streams.
		FileCopyUtils.copy(inputStream, response.getOutputStream());
	}

	/**
	 * This method will be used to bulk upload the note by user.
	 * 
	 * @param request
	 * @param redirectAttributes
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/api/noteUpload", method = RequestMethod.POST)
	public ResponseEntity<List<NoteInputFormModel>> multiFileUpload(MultipartHttpServletRequest request,
			RedirectAttributes redirectAttributes) throws IOException {

		RequestStatusModel statusModel = new RequestStatusModel();
		Iterator<String> iterator = request.getFileNames();
		MultipartFile multipart = null;
		List<NoteInputFormModel> responseList = new ArrayList<>();
		while (iterator.hasNext()) {
			multipart = request.getFile(iterator.next());
			// do something with the file.....
		}
		System.out.println("Index controler for filr upload called>>" + multipart);
		if (multipart == null) {
			System.out.println("Empty uploaded file");
			return new ResponseEntity<List<NoteInputFormModel>>(responseList, HttpStatus.BAD_REQUEST);
		} else {
			System.out.println("Fetching files" + multipart);

			ColumnPositionMappingStrategy strat = new ColumnPositionMappingStrategy();
			strat.setType(NoteInputFormModel.class);
			String[] columns = new String[] { "typeOfNote", "address", "property", "dateOfNote", "upb", "rate",
					"pdiPayment", "tdiPayment", "remainingTerm" }; // the fields
																	// to bind
																	// do in
																	// your
																	// JavaBean
			strat.setColumnMapping(columns);
			File file = NoteUtility.convert(multipart);
			CsvToBean csv = new CsvToBean();
			FileReader fr = new FileReader(file);
			CSVReader csvReader = new CSVReader(new FileReader(file));

			List list = csv.parse(strat, csvReader);
			for (Object object : list) {
				NoteInputFormModel note = (NoteInputFormModel) object;
				System.out.println(note);
				responseList.add(note);
			}
			return new ResponseEntity<List<NoteInputFormModel>>(responseList, HttpStatus.OK);
		}
	}

	/**
	 * This method will return all the notes summary for logged in user.
	 * 
	 * @return
	 */
	@RequestMapping(value = "/api/fetchAllNotes", method = RequestMethod.GET)
	public ResponseEntity<List<NoteDashboardModel>> listAllNotes() {

		String loggedInUserName = NoteUtility.getLoggedInUserName();
		Optional<UserModel> loggedInuser = userService.getByUsername(loggedInUserName);
		if (loggedInuser.isPresent()) {
			System.out.println("Inside  listAllNotes loggedInUserName" + loggedInUserName);
			long userId = loggedInuser.get().getUserId();
			Optional<UserSubscriptions> subscriptionList = userService.getUserSubscription(userId);
			String subscription = null;
			if(subscriptionList.isPresent()){
				subscription = subscriptionList.get().getSubscriptionName();
			}
			Optional<List<NoteDashboardModel>> notesList = noteService.getAllNotes(userId,subscription);
			if (!notesList.isPresent()) {
				return new ResponseEntity<List<NoteDashboardModel>>(HttpStatus.NO_CONTENT);// You
			}
			return new ResponseEntity<List<NoteDashboardModel>>(notesList.get(), HttpStatus.OK);
		} else {
			LOG.severe("Error with loggedin user name and ID " + loggedInUserName);
			return new ResponseEntity<List<NoteDashboardModel>>(HttpStatus.FORBIDDEN);
		}
	}

	/**
	 * This method will be used to update given note value and recalculate the
	 * parameters.
	 * 
	 * @param noteDetailModel
	 * @return 
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/api/updateAndSaveMarketValue", method = RequestMethod.POST)
	public ResponseEntity<NoteInputFormModel> updateAndSaveMarketValue(@RequestBody NoteInputFormModel noteInputFormModel) throws ParseException {

		if (noteInputFormModel == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Optional<Object> status = noteService.updateMarketValueDetails(noteInputFormModel.getNoteId());
		if(status.isPresent()){
			Optional<NoteInputFormModel> model = noteService.updateNote(noteInputFormModel);
			if (model.isPresent()) {
				LOG.info("Inside updateAndSaveMarketValue with note id value " + noteInputFormModel.getNoteId());
				return new ResponseEntity<NoteInputFormModel>(model.get(), HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	/**
	 * This method will be used to update given note value and recalculate the
	 * parameters.
	 * 
	 * @param noteDetailModel
	 * @return 
	 * @return
	 */
	@RequestMapping(value = "/api/updateMarketValue", method = RequestMethod.POST)
	public ResponseEntity<NoteInputFormModel> updateMarketValue(@RequestBody NoteInputFormModel noteInputFormModel) {

		if (noteInputFormModel == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Optional<Object> status = noteService.updateMarketValueDetails(noteInputFormModel.getNoteId());
		if(status.isPresent()){
			Optional<NoteInputFormModel> noteDetailModel = noteService.getNoteDetailNew(noteInputFormModel.getNoteId());
			if (noteDetailModel.isPresent()) {
				LOG.info("Inside Get Note Details with  noteInputFormModel value " + noteInputFormModel.getNoteId());
				return new ResponseEntity<NoteInputFormModel>(noteDetailModel.get(), HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	/**
	 * This method will be used to update given note value and recalculate the
	 * parameters.
	 * 
	 * @param noteDetailModel
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/api/saveNote", method = RequestMethod.POST)
	public ResponseEntity<NoteInputFormModel> saveNote(@RequestBody NoteInputFormModel noteModel) throws ParseException {

		if (noteModel == null) {
			NoteInputFormModel model = new NoteInputFormModel();
			model.setErrorMessage("Invalid input details");
			return new ResponseEntity<NoteInputFormModel>(model, HttpStatus.BAD_REQUEST);
		}
		Optional<NoteInputFormModel> model = noteService.updateNote(noteModel);
		return new ResponseEntity<NoteInputFormModel>(model.get(), HttpStatus.OK);
	}

	/**
	 * This method will be used to update given note value and recalculate the
	 * parameters.
	 * 
	 * @param noteDetailModel
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/api/subscribeNote", method = RequestMethod.POST)
	public ResponseEntity<NoteInputFormModel> subscribeNote(@RequestBody NoteInputFormModel noteModel) throws ParseException {

		if (noteModel == null) {
			NoteInputFormModel model = new NoteInputFormModel();
			model.setErrorMessage("Invalid input details");
			return new ResponseEntity<NoteInputFormModel>(model, HttpStatus.BAD_REQUEST);
		}
		Optional<NoteInputFormModel> model = noteService.subscribeNote(noteModel);
		return new ResponseEntity<NoteInputFormModel>(model.get(), HttpStatus.OK);
	}


	
	/**
	 * This method will be used to delete the given note using note id for
	 * logged in user.
	 * 
	 * @param noteDetailModel
	 * @return
	 */
	@RequestMapping(value = "/api/deleteNote", method = RequestMethod.POST)
	public ResponseEntity<RequestStatusModel> deleteNote(@RequestBody NoteInputFormModel noteDetailModel) {
		Optional<UserModel>  user  = userService.getByUsername(NoteUtility.getLoggedInUserName());
		if (noteDetailModel == null || !user.isPresent()) {
			RequestStatusModel model = new RequestStatusModel();
			model.setErrorMessage("Invalid input details");
			return new ResponseEntity<RequestStatusModel>(model, HttpStatus.BAD_REQUEST);
		}
		
		LOG.info("Inside DELETE with deleteNote noteDetailModel value " + noteDetailModel);
		boolean isDeleted = noteService.deleteNote(noteDetailModel,user.get().getUserId());
		if (isDeleted) {
			return new ResponseEntity<RequestStatusModel>(HttpStatus.OK);
		} else {
			return new ResponseEntity<RequestStatusModel>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * This method will fetch the details of given note present for given logged
	 * in user.
	 * 
	 * @param noteId
	 * @return
	 */
	@RequestMapping(value = "/api/getNoteDetailOld/{noteId}", method = RequestMethod.GET)
	public ResponseEntity<NoteDetailModel> getNoteDetailOld(@PathVariable String noteId) {
		NoteDetailModel model = new NoteDetailModel();
		if (StringUtils.isEmpty(noteId)) {
			model.setErrorMessage("Invalid input details");
			return new ResponseEntity<NoteDetailModel>(model, HttpStatus.BAD_REQUEST);
		}
		Optional<NoteDetailModel> noteDetailModel = noteService.getNoteDetail(Long.valueOf(noteId));
		if (noteDetailModel.isPresent()) {
			LOG.info("Inside Get Note Details with  noteDetailModel value " + noteId);
			return new ResponseEntity<NoteDetailModel>(noteDetailModel.get(), HttpStatus.OK);
		}
		model.setErrorMessage("Unable to find the details of input note");
		return new ResponseEntity<NoteDetailModel>(model, HttpStatus.NOT_FOUND);
	}

	/**
	 * This method will fetch the details of given note present for given logged
	 * in user.
	 * 
	 * @param noteId
	 * @return
	 */
	@RequestMapping(value = "/api/getNoteDetail/{noteId}", method = RequestMethod.GET)
	public ResponseEntity<NoteInputFormModel> getNoteDetail(@PathVariable String noteId) {
		NoteInputFormModel model = new NoteInputFormModel();
		if (StringUtils.isEmpty(noteId)) {
			model.setErrorMessage("Invalid input details");
			return new ResponseEntity<NoteInputFormModel>(model, HttpStatus.BAD_REQUEST);
		}
		Optional<NoteInputFormModel> noteDetailModel = noteService.getNoteDetailNew(Long.valueOf(noteId));
		if (noteDetailModel.isPresent()) {
			LOG.info("Inside Get Note Details with  noteDetailModel value " + noteId);
			return new ResponseEntity<NoteInputFormModel>(noteDetailModel.get(), HttpStatus.OK);
		}
		model.setErrorMessage("Unable to find the details of input note");
		return new ResponseEntity<NoteInputFormModel>(model, HttpStatus.NOT_FOUND);
	}

	/**
	 * This method will return the list of note type supported by this
	 * application.
	 * 
	 * @return
	 */
	@RequestMapping(value = "/api/getLoanType", method = RequestMethod.GET)
	public ResponseEntity<List<LoanTypeModel>> getLoanType() {
		Optional<List<LoanTypeModel>> loanTypeModelList = noteService.getLoanType();
		if (loanTypeModelList.isPresent()) {
			return new ResponseEntity<List<LoanTypeModel>>(loanTypeModelList.get(), HttpStatus.OK);
		}
		return new ResponseEntity<List<LoanTypeModel>>(HttpStatus.NOT_FOUND);
	}

	/**
	 * This method will return the list of property type supporte by this
	 * application.
	 * 
	 * @return
	 */
	@RequestMapping(value = "/api/getNoteType", method = RequestMethod.GET)
	public ResponseEntity<List<NoteTypeModel>> getNoteType() {
		Optional<List<NoteTypeModel>> noteTypeModelList = noteService.getAllNoteType();
		if (noteTypeModelList.isPresent()) {
			return new ResponseEntity<List<NoteTypeModel>>(noteTypeModelList.get(), HttpStatus.OK);
		}
		return new ResponseEntity<List<NoteTypeModel>>(HttpStatus.NOT_FOUND);
	}

	
	/**
	 * This method will return the list of property type supporte by this
	 * application.
	 * 
	 * @return
	 */
	@RequestMapping(value = "/api/getPropertyType", method = RequestMethod.GET)
	public ResponseEntity<List<PropertyTypeModel>> getPropertyType() {

		Optional<List<PropertyTypeModel>> propTypeModelList = noteService.getPropertyType();
		if (propTypeModelList.isPresent()) {
			return new ResponseEntity<List<PropertyTypeModel>>(propTypeModelList.get(), HttpStatus.OK);
		}
		return new ResponseEntity<List<PropertyTypeModel>>(HttpStatus.NOT_FOUND);
	}

	/**
	 * This method will return the list of state and city for given zip code.
	 * 
	 * @param zipCode
	 * @return
	 */
	@RequestMapping(value = "/getStateCityList/{zipCode}", method = RequestMethod.GET)
	public ResponseEntity<AddressModel> getStateCityList(@PathVariable String zipCode) {
		AddressModel model = new AddressModel();
		if (StringUtils.isEmpty(zipCode)) {
			model.setErrorMessage("Empty zipCode");
			return new ResponseEntity<AddressModel>(model, HttpStatus.BAD_REQUEST);
		}

		Optional<AddressModel> address = noteService.getZipCodeDetails(zipCode);
		if (address.isPresent()) {
			return new ResponseEntity<AddressModel>(address.get(), HttpStatus.OK);
		}
		model.setErrorMessage("ZipCode details not found");
		return new ResponseEntity<AddressModel>(HttpStatus.NOT_FOUND);

	}

}