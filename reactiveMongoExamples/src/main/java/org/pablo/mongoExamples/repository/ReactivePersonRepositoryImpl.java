package org.pablo.mongoExamples.repository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.pablo.mongoExamples.converters.DocumentConverter;
import org.pablo.mongoExamples.exceptions.MongoException;
import org.pablo.mongoExamples.model.Person;
import org.pablo.mongoExamples.springDataMongo.reactive.ReactiveMongoTemplate;
import org.pablo.mongoExamples.utils.ReactiveAbstractMongoDao;

public class ReactivePersonRepositoryImpl extends ReactiveAbstractMongoDao
		implements PersonRepository {

	protected ReactivePersonRepositoryImpl(ReactiveMongoTemplate mongoOperations) {
		super(Person.COLLECTION, mongoOperations);
	}

	@Override
	public List<Person> findAllPersonList() throws MongoException {
		return super.findAll().stream()
				.map(document -> DocumentConverter.apply(document))
				.collect(Collectors.toList());
	}

	@Override
	public Stream<Person> findAllPersonStreams() throws MongoException {
		return super.findWithStream().map(
				document -> DocumentConverter.apply(document));
	}

	@Override
	public void saveSinglePerson(Person p) {
		super.save(DocumentConverter.apply(p));
	}

}
