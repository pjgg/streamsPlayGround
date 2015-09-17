package org.pablo.completableFuture.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = Person.COLLECTION)
public class Person {

	public final static String COLLECTION = "person";

	private @Id String id;
	private String firstname;
	private String lastname;

	public Person(String fName, String lName) {
		this.firstname = fName;
		this.lastname = lName;
	}

	public Person() {
	}

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
