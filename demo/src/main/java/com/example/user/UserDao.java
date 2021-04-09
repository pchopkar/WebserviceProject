package com.example.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDao {
	private static int usercount=3;
	
	private static List<User> userlist = new ArrayList<User>();
	
	static
	{
		userlist.add(new User(1,"Arjun",new Date()));
		userlist.add(new User(2,"karan",new Date()));
		userlist.add(new User(3,"krishna",new Date()));
		
	}
	
	public List<User> findall()
	{
		return userlist;
	}
	
	public User save(User user)
	{
		/*
		 * if(user.getId()equals(null)) {
		 */
			user.setId(++usercount);
			userlist.add(user);
		
		return user;
	}
	
	public User findone(int id)
	{
		for(User user:userlist)
		{
			if(user.getId()==id)
			{
				return user;
			}
		}
		return null;
	}
	public User deleteById(int id)
	{
		Iterator<User> iterator = userlist.iterator();
		while(iterator.hasNext())
		{
			User user = iterator.next();
			if(user.getId()==id)
			{
				iterator.remove();
				return user;
			}
		}
		return null;
		
	}
}

