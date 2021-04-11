package com.example.user;

import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import antlr.collections.List;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
//40 API Swagger Documentation
@ApiModel(description = "All details about the user")
//50 JPA - entity tag over the table want to create
@Entity
public class User {
	//50 JPA - for primary key
	@Id
	//50 JPA -  for sequence
	// 3) create data.sql where add insert query and the go to http://localhost:8080/h2-console/
	@GeneratedValue
	private Integer id;
	
	@Size(min = 2, message = "Minimum 2 string required")
	@ApiModelProperty(notes = "Minimum 2 string required")
	private String name;
	
	@Past
	@ApiModelProperty(notes = "dob should be before todays date")
	private Date dob;
	
	//53 Relationsship between two table - 3)as User is parent and have one user only so add oneToMany relation with mappedby=the name given in child table class post
	//Remember here Post will be list from util class then generate getter setter for post
	//4) add insert queries on data.sql and 
	//5) check on console of h2 Post table might have created with data
	@OneToMany(mappedBy = "user")
	private java.util.List<Post> post;
	
	

	public java.util.List<Post> getPost() {
		return post;
	}
	public void setPost(java.util.List<Post> post) {
		this.post = post;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + "]";
	}
	public User()
	{
		super();
	}
	public User(int id, String name, Date dob) {
		super();
		this.id = id;
		this.name = name;
		this.dob = dob;
	}
	
	

}
