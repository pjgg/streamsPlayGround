package org.pablo.mongoExamples.repository;

import java.util.List;
import java.util.stream.Stream;

import org.pablo.mongoExamples.exceptions.MongoException;
import org.pablo.mongoExamples.model.Person;

public interface PersonRepository{
	
	List<Person> findAllPersonList() throws MongoException;
	
	Stream<Person> findAllPersonStreams() throws MongoException;
	
	void saveSinglePerson(Person p);
}
