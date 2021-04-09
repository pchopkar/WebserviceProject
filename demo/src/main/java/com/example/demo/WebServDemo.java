package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.user.User;
import com.example.user.UserDao;

@RestController
public class WebServDemo {
	
	//@RequestMapping(method = RequestMethod.GET, path="/hello-world")
	//Or we can use Getmapping
	@GetMapping(path="/Hello")
	public String hello()
	{
		return "Hello World";
	}
	//here we are creating bean
	@GetMapping(path="/hello-bean")
	public Helloworld helloN()
	{
		return new Helloworld("Hello World");
	}

	//here we are sending parameter
	@GetMapping(path="/hello-beans/{name}")
	public Helloworld hellopathvariable(@PathVariable String name)
	{
		return new Helloworld("Hello Mister "+name);
	}
	
	@Autowired
	private UserDao dao;
	
	@GetMapping(path="/Hel")
	public String hello4()
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
		return dao.findone(id);
		
	}
}
