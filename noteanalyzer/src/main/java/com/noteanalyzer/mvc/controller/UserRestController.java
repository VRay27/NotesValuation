package com.noteanalyzer.mvc.controller;
 
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import com.noteanalyzer.mvc.model.NoteModel;
import com.noteanalyzer.mvc.model.NoteUploadValidator;
import com.noteanalyzer.mvc.model.User;
import com.noteanalyzer.mvc.service.UserService;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;
import au.com.bytecode.opencsv.bean.CsvToBean;
 
@RestController
public class UserRestController {
 
    @Autowired
    UserService userService;  //Service which will do all data retrieval/manipulation work
 
    
    //-------------------Retrieve All Users--------------------------------------------------------
     
    @RequestMapping(value = "/user/", method = RequestMethod.GET)
    public ResponseEntity<List<User>> listAllUsers() {
    	System.out.println("Inside Arvind listAllUsers");
        List<User> users = userService.findAllUsers();
        if(users.isEmpty()){
            return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
       return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }
 
    
    private static String UPLOAD_LOCATION="C:/mytemp/";
	  
	
	 
    @Autowired
    NoteUploadValidator noteUploadValidator;
    @InitBinder("multiNoteBucket")
    protected void initBinderMultiFileBucket(WebDataBinder binder) {
        binder.setValidator(noteUploadValidator);
    }
    
    
  @RequestMapping(value = "/console", method = RequestMethod.POST)
    public ResponseEntity<List<NoteModel>> multiFileUpload(MultipartHttpServletRequest request,
            RedirectAttributes redirectAttributes) throws IOException {
 
	  Iterator<String> iterator = request.getFileNames();
      MultipartFile multipart = null;
      List<NoteModel> responseList =  new ArrayList<>();
      while (iterator.hasNext()) {
          multipart = request.getFile(iterator.next());
          //do something with the file.....
      }
	  System.out.println("Index controler for filr upload called>>"+multipart);
        if (multipart == null) {
            System.out.println("Empty uploaded file");
            return new ResponseEntity<List<NoteModel>>(responseList, HttpStatus.BAD_REQUEST);
        } else {
            System.out.println("Fetching files"+multipart);
                FileCopyUtils.copy(multipart.getBytes(), new File(UPLOAD_LOCATION + multipart.getOriginalFilename()));
 
                ColumnPositionMappingStrategy strat = new ColumnPositionMappingStrategy();
                strat.setType(NoteModel.class);
                String[] columns = new String[] {"noteName", "noteValue"}; // the fields to bind do in your JavaBean
                strat.setColumnMapping(columns);
                File file = convert(multipart);
                CsvToBean csv = new CsvToBean();
                FileReader fr = new FileReader(file);
                CSVReader csvReader = new CSVReader(new FileReader(file));

                List list = csv.parse(strat, csvReader);
                for (Object object : list) {
                	NoteModel note = (NoteModel) object;
                	System.out.println(note);
                	responseList.add(note);
                }
            return new ResponseEntity<List<NoteModel>>(responseList, HttpStatus.OK);
        }
    }
 
  
  public File convert(MultipartFile file) throws IOException
  {    
      File convFile = new File(file.getOriginalFilename());
      convFile.createNewFile(); 
      FileOutputStream fos = new FileOutputStream(convFile); 
      fos.write(file.getBytes());
      fos.close(); 
      return convFile;
  }
    
    //-------------------Retrieve Single User--------------------------------------------------------
     
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUser(@PathVariable("id") long id) {
        System.out.println("Fetching User with id " + id);
        User user = userService.findById(id);
        if (user == null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
 
     
     
    //-------------------Create a User--------------------------------------------------------
     
    @RequestMapping(value = "/user/", method = RequestMethod.POST)
    public ResponseEntity<Void> createUser(@RequestBody User user,    UriComponentsBuilder ucBuilder) {
        System.out.println("Creating User " + user.getUsername());
 
        if (userService.isUserExist(user)) {
            System.out.println("A User with name " + user.getUsername() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
 
        userService.saveUser(user);
 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
 
    
     
    //------------------- Update a User --------------------------------------------------------
     
    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user) {
        System.out.println("Updating User " + id);
         
        User currentUser = userService.findById(id);
         
        if (currentUser==null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
 
        currentUser.setUsername(user.getUsername());
        currentUser.setAddress(user.getAddress());
        currentUser.setEmail(user.getEmail());
         
        userService.updateUser(currentUser);
        return new ResponseEntity<User>(currentUser, HttpStatus.OK);
    }
 
    
    
    //------------------- Delete a User --------------------------------------------------------
     
    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteUser(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting User with id " + id);
 
        User user = userService.findById(id);
        if (user == null) {
            System.out.println("Unable to delete. User with id " + id + " not found");
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
 
        userService.deleteUserById(id);
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }
 
     
    
    //------------------- Delete All Users --------------------------------------------------------
     
    @RequestMapping(value = "/user/", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteAllUsers() {
        System.out.println("Deleting All Users");
 
        userService.deleteAllUsers();
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }
 
}