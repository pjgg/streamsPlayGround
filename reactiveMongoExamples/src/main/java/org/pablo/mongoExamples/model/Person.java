package org.pablo.mongoExamples.model;

import java.io.Serializable;


public class Person implements Serializable{

	private static final long serialVersionUID = 1L;

	public final static String COLLECTION = "person";
	
	public final static String ID = "id";
	public final static String FIRST_NAME = "firstname";
	public final static String LAST_NAME = "lastname";

	private String id;
	private String firstname;
	private String lastname;

	public Person(String fName, String lName) {
		this.firstname = fName;
		this.lastname = lName;
	}
	
	public Person(){}

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
}
