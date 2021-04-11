package com.example.user;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

//53 Relationsship between two table - 1) create Post class
@Entity
public class Post {

	@Id
	@GeneratedValue
	private int id;
	private String description;
	
	//53 Relationsship between two table - 2) As user can have many post so here on Post class Many to one relation. Post will be child table
	// fetchtype should be Lazy because it will call when we call for Post. not call automatically
	//remember dont create constructor for User
	//this user name will be use on parent table
	@ManyToOne(fetch = FetchType.LAZY)
	//54 retrieve all post - needs to ignore because as we dont want user data we want only post data 
	@JsonIgnore
	private User user;
	
	

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", description=" + description + "]";
	}
	public Post()
	{
		super();
	}

	public Post(int id, String description) {
		super();
		this.id = id;
		this.description = description;
	}
	
	
	
	
}
