package com.example.user;

import java.lang.reflect.Method;
import java.net.URI;
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContext;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.ControllerLinkRelationProvider;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserController {
	//33 Internationalization
	//4)Implement that ResourceBundleMessageSource here
	@Autowired
	private ResourceBundleMessageSource messagesource;
	
	@Autowired
	private UserDao dao;
	
	@GetMapping(path="/Hell")
	public String hello()
	{
		return "Hello Parjanya";
	}
	
	@GetMapping("/userdao")
	public List<User> findall()
	{
		return dao.findall();
		
	}
	@GetMapping(path="/userdaos/{id}")
	public User findone(@PathVariable int id)
	{
	
		User user = dao.findone(id);
		if(user==null)
		{
			throw new UsernotfoundException("Not found id->"+id);
		}
		
		//Implementation of HATEOS(hypermedia as the engine of application as the state. add links to the resourse)
		//Before doing that add the dependency called : spring-boot-starter-hateoas
		//then create  WebMvcLinkBuilder then 
		EntityModel<User> resource = EntityModel.of(user);
		WebMvcLinkBuilder linkTo =	(WebMvcLinkBuilder) WebMvcLinkBuilder.methodOn(this.getClass()).findall();
		resource.add((Link) WebMvcLinkBuilder.methodOn(this.getClass()).findall());
		
		return user;
	}
	@DeleteMapping(path="/deleteuser/{id}")
	public User deleteone(@PathVariable int id)
	{
	
		User user = dao.deleteById(id);
		if(user==null)
		{
			throw new UsernotfoundException("Not found id->"+id);
		}
		return user;
		
	}
	//post mapping is to save the data
	@PostMapping(path="usersave")
	public ResponseEntity<Object> saveone(@Valid @RequestBody User user)
	{
		User usersaved = dao.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usersaved.getId()).toUri();
		return ResponseEntity.created(location).build();
		
	}
	
	//33 Internationalization
	//5) Create a method which will get the message from properties with requestheader parameter as "Accept-Language"
	@GetMapping(path="/internationalization")
	public String internationalization(@RequestHeader(name = "Accept-Language", required = false) Locale locale)
	{
		
		return messagesource.getMessage("good.morning.message",null,locale);
		
	}
	//34 Internationalization 2
	//1) Instead of locale parameter we are using LocaleContextHolder
	@GetMapping(path="/internationalization2")
	public String internationalization2()
	{
		return messagesource.getMessage("good.morning.message", null,LocaleContextHolder.getLocale());
		
	}
	
}
