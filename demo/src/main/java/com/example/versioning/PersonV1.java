package com.example.versioning;
//45 Versioning - Create a PersonVersion 1 where only name accepted 
public class PersonV1 {
String name;

public  PersonV1()
{
super();	
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public PersonV1(String name) {
	super();
	this.name = name;
}

@Override
public String toString() {
	return "Person [name=" + name + "]";
}

}
