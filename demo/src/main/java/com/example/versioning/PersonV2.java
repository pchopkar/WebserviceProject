package com.example.versioning;
//45 Versioning - here name class object use of version 2
public class PersonV2 {
	
	private Name name;

	public PersonV2()
	{
		super();
	}
	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}

	public PersonV2(Name name) {
		super();
		this.name = name;
	}

	@Override
	public String toString() {
		return "PersonV2 [name=" + name + "]";
	}
	
	
	

}
