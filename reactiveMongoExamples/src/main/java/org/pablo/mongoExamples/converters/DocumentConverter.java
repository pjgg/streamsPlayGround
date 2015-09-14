package org.pablo.mongoExamples.converters;

import org.bson.Document;
import org.pablo.mongoExamples.model.Person;
import org.pablo.mongoExamples.model.PersonFields;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public interface DocumentConverter {
	
	final static Gson gson = new GsonBuilder().create();
	
	static <T> T apply(Class<T> entityClass, String documentAsString){
		return gson.fromJson(documentAsString, entityClass);
	}
	
	static Person apply(Document document){
		return new Person(document.get(PersonFields.FIRST_NAME.getCode()).toString(), document.get(PersonFields.LAST_NAME.getCode()).toString());
	}
	
	static Document apply(Person person){
		return new Document().append(PersonFields.FIRST_NAME.getCode(), person.getFirstname()).append(PersonFields.LAST_NAME.getCode(), person.getLastname());
	}
	
}