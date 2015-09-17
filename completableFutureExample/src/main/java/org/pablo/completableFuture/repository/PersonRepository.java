package org.pablo.completableFuture.repository;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

import org.pablo.completableFuture.model.Person;

import com.mongodb.MongoException;

public interface PersonRepository {
	
	CompletableFuture<List<Person>> findAllPersonList() throws MongoException;

	CompletableFuture<Stream<Person>> findAllPersonStreams() throws MongoException;

	void saveSinglePerson(Person p);
}
