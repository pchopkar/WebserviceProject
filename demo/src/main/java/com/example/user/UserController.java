package com.example.user;

import java.lang.reflect.Method;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

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
		/*
		 * EntityModel<User> resource = EntityModel.of(user); WebMvcLinkBuilder linkTo =
		 * (WebMvcLinkBuilder) WebMvcLinkBuilder.methodOn(this.getClass()).findall();
		 * resource.add((Link) WebMvcLinkBuilder.methodOn(this.getClass()).findall());
		 */
		
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
	//43 - Filtering - 2) create the function
	@GetMapping(path = "/filtering")
	public SomeBean retrievesomebean()
	{
		return new SomeBean("Value1","Value2","Value3");
		
	}
	//43 - Filtering list
	@GetMapping(path = "/filtering-list")
	public List<SomeBean> retrievesomebeanlist()
	{
		return Arrays.asList(new SomeBean("Value1","Value2","Value3"), new SomeBean("Value1","Value2","Value3"));
		
	}
	//44 - Dynamic filtering - create method and use MappingJacksonValue where set filter 
	//and now on FilterProvider - set the name of filter 
	// SimpleBeanPropertyFilter add the filter with except
	// add filter name on Bean
	@GetMapping(path = "/dynamicfiltering")
	public MappingJacksonValue dynamicretrievesomebean()
	{
		SomeBean somebean =  new SomeBean("Value1","Value2","Value3");
		MappingJacksonValue mapping = new MappingJacksonValue(somebean);
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1","field2");
		FilterProvider filters = new SimpleFilterProvider().addFilter("somebeanfilter", filter );
		mapping.setFilters(filters);
		return mapping;
		
	}
	//44 - Dynamic filtering  for list of Beans
	@GetMapping(path = "/dynamicfiltering-list")
	public MappingJacksonValue  dynamicretreivesomebean2()
	{
List<SomeBean> as	=	Arrays.asList(new SomeBean("Value1","Value2","Value3"), new SomeBean("Value1","Value2","Value3"));

MappingJacksonValue mapping = new MappingJacksonValue(as);
SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field2","field3");
FilterProvider filters = new SimpleFilterProvider().addFilter("somebeanfilter", filter );
mapping.setFilters(filters );
return mapping;

	}

	/*
	 * 44/45 - Versioning Media type
	 * Versioning(accept header) - Github Header
	 * Versioning - Microsoft 
	 * URI versioning - Twitter 
	 * Parameter Versioning - Amazon
	 */
	//45 Versioning - URI method version 1 call
	@GetMapping("v1/person")
	public PersonV1 personv1()
	{
		return new PersonV1("Bob Charlie");
	}
	//45 Versioning -URI method version 2 call
	@GetMapping("v2/person")
	public PersonV2 personv2()
	{
		return new PersonV2(new Name("Bob","Charlie"));
	}
	//46 Versioning - parameter passing version 1 call
	//http://localhost:8080/person/param?version=1
	@GetMapping(value="person/param", params = "version=1")
	public PersonV1 personparamv1()
	{
		return new PersonV1("Bob Charlie");
	}
	//46 Versioning - parameter passing version 2 call
	
		@GetMapping(value="person/param", params = "version=2")
		public PersonV2 personparamv2()
		{
			return new PersonV2(new Name("Bob","Charlie"));
		}
		//46 Versioning - Header method passing version 1 call
		//after entering url the pas the header as key and 1 in value 
		@GetMapping(value="person/header", headers = "X-API-VERSION=1")
		public PersonV1 personheaderv1()
		{
			return new PersonV1("Bob Charlie");
		}
		//46 Versioning - Header method passing version 2 call
		//after entering url the pas the header as key and 2 in value 
		@GetMapping(value="person/header", headers = "X-API-VERSION=2")
		public PersonV2 personheaderv2()
		{
			return new PersonV2(new Name("Bob","Charlie"));
		}
		//46 Versioning - produces method passing version 1 call
		//in header tab add accept as key and value as provided in produces : application/, + jason is mendatory  
		@GetMapping(value="person/produces", produces = "application/v1+json")
		public PersonV1 personproducesv1()
		{
			return new PersonV1("Bob Charlie");
		}
		//46 Versioning - produces method passing version 2 call
		//in header tab add accept as key and value as provided in produces : application/, + jason is mendatory
		@GetMapping(value="person/produces", produces = "application/v2+json")
		public PersonV2 personproducesv2()
		{
			return new PersonV2(new Name("Bob","Charlie"));
		}
	
	
	}
