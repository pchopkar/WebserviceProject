package com.example.user;

import java.lang.reflect.Method;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.tomcat.util.digester.ArrayStack;
//import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContext;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.filtering.SomeBean;
import com.example.versioning.Name;
import com.example.versioning.PersonV1;
import com.example.versioning.PersonV2;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

//51 JPA GET - 1)Create new controller class 
@RestController
public class UserJPAController {
	//33 Internationalization
	//4)Implement that ResourceBundleMessageSource here
	@Autowired
	private ResourceBundleMessageSource messagesource;
	
	//51 JPA GET - 3) autowire the created repository
	@Autowired
	private UserRepository userrepository;
	
	@Autowired
	private PostRepository postrepository;
	
	@Autowired
	private UserDao dao;
	
	//51 JPA GET - 4) call the internal method findAll
	@GetMapping("/jpa/userdao")
	public List<User> findall()
	{
		return userrepository.findAll();
		
	}
	//51 JPA GET - 5) call the method findById with proper handling of exception as !user.isPresent()
	@GetMapping(path="/jpa/userdaos/{id}")
	public Optional<User> findone(@PathVariable int id)
	{
	
		Optional<User> user = userrepository.findById(id);
		if(!user.isPresent())
		{
			throw new UsernotfoundException("Not found id->"+id);
		}
		
		return user;
	}
	//52 JPA post and delete - 1) change the method to deleteById
	@DeleteMapping(path="/jpa/deleteuser/{id}")
	public void deleteone(@PathVariable int id)
	{
	
		userrepository.deleteById(id);
	}
	//52 JPA post and delete - 1) change the method for save
	@PostMapping(path="/jpa/usersave")
	public ResponseEntity<Object> saveone(@Valid @RequestBody User user)
	{
		User usersaved = userrepository.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usersaved.getId()).toUri();
		return ResponseEntity.created(location).build();
		
	}
	
	//54 retrieve all post - 1) retrieve all post from post table with the user id 
	@GetMapping(path="/jpa/userdaos/{id}/post")
	public List<Post> retrieveallpost(@PathVariable int id)
	{
	
		Optional<User> user = userrepository.findById(id);
		if(!user.isPresent())
		{
			throw new UsernotfoundException("Not found id->"+id);
		}
		//54 retrieve all post - here it will get all user and post data but we want only post data
		return user.get().getPost();
	}
	@PostMapping(path="/jpa/createpost/{id}")
	public ResponseEntity<Object> createPost(@Valid @PathVariable int id, @RequestBody Post post)
	{
		Optional<User> user = userrepository.findById(id);
		if(!user.isPresent())
		{
			throw new UsernotfoundException("Not found id->"+id);
		}
		
		post.setUser(user.get());
		Post pst = postrepository.save(post);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pst.getId()).toUri();
		return ResponseEntity.created(location).build();
		
	}
	
	}
