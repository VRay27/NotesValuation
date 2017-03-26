package com.realestate.springmvc.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.realestate.springmvc.model.User;
import com.realstate.dao.GenericDao;
import com.realstate.entity.notes.Note;

@Service("userService")
public class UserServiceImpl implements UserService{
	
	private static final AtomicLong counter = new AtomicLong();
	
	private static List<User> users;
	
	@Autowired
	GenericDao genericDao;
	
	
	
	public GenericDao getGenericDao() {
		return genericDao;
	}

	public void setGenericDao(GenericDao genericDao) {
		this.genericDao = genericDao;
	}

	static{
		users= populateDummyUsers();
		RestTemplate restTemplate = new RestTemplate();
		
//	String url = "http://www.zillow.com/webservice/GetSearchResults.htm?zws-id=X1-ZWz19dj8ldqvwr_38l6u&address=2114+Bigelow+Ave&citystatezip=Seattle%2C+WA";
	//System.out.println(restTemplate.getForObject(url, Source.class));
		
	}

	public List<User> findAllUsers() {
		return users;
	}
	
	public User findById(long id) {
		for(User user : users){
			if(user.getId() == id){
				return user;
			}
		}
		return null;
	}
	
	public User findByName(String name) {
		RestTemplate restTemplate = new RestTemplate();
		genericDao.getAll(Note.class);
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
		Jaxb2RootElementHttpMessageConverter jaxbMessageConverter = new Jaxb2RootElementHttpMessageConverter();
		List<MediaType> mediaTypes = new ArrayList<MediaType>();
		mediaTypes.add(MediaType.TEXT_HTML);
		jaxbMessageConverter.setSupportedMediaTypes(mediaTypes);
		messageConverters.add(jaxbMessageConverter);
		restTemplate.setMessageConverters(messageConverters);
		
		String url = "http://www.zillow.com/webservice/GetSearchResults.htm?zws-id=X1-ZWz19dj8ldqvwr_38l6u&address=2114+Bigelow+Ave&citystatezip=Seattle%2C+WA";
		String responseString =   restTemplate.getForObject(url, String.class);
	//	JSONObject  jsonObj = XML.toJSONObject(responseString);
		
		//System.out.println("Arvind Output"+jsonObj);
		
		User user = new User();
		 
		 return user;
		/*for(User user : users){
			if(user.getUsername().equalsIgnoreCase(name)){
				return user;
			}
		}
		return null;*/
	}
	
	public void saveUser(User user) {
		user.setId(counter.incrementAndGet());
		users.add(user);
	}

	public void updateUser(User user) {
		int index = users.indexOf(user);
		users.set(index, user);
	}

	public void deleteUserById(long id) {
		
		for (Iterator<User> iterator = users.iterator(); iterator.hasNext(); ) {
		    User user = iterator.next();
		    if (user.getId() == id) {
		        iterator.remove();
		    }
		}
	}

	public boolean isUserExist(User user) {
		return findByName(user.getUsername())!=null;
	}
	
	public void deleteAllUsers(){
		users.clear();
	}

	private static List<User> populateDummyUsers(){
		List<User> users = new ArrayList<User>();
		users.add(new User(counter.incrementAndGet(),"Sam", "NY", "sam@abc.com"));
		users.add(new User(counter.incrementAndGet(),"Tomy", "ALBAMA", "tomy@abc.com"));
		users.add(new User(counter.incrementAndGet(),"Kelly", "NEBRASKA", "kelly@abc.com"));
		return users;
	}

}
