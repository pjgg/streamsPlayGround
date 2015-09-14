package org.pablo.mongoExamples.repository;

import java.util.List;
import java.util.stream.Stream;

import org.bson.types.ObjectId;
import org.pablo.mongoExamples.model.Person;
import org.pablo.mongoExamples.utils.AbstractMongoDao;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Query;

public class PersonRepositoryImpl extends AbstractMongoDao<Person, String, ObjectId> implements PersonRepository{
	 
	 private final static Query FIND_ALL_QUERY = new BasicQuery("{}");

	   public PersonRepositoryImpl(MongoOperations mongoOperations) {
			super(Person.COLLECTION, mongoOperations);
		}

		@Override
		protected Class<Person> getEntityClass() {
			return Person.class;
		}

		@Override
		protected String getStorageField(String field) {
			return field;
		}

		@Override
		public List<Person> findAllPersonList() {
			return super.find(FIND_ALL_QUERY);
		}

		@Override
		public Stream<Person> findAllPersonStreams() {
			return super.findWithStream(FIND_ALL_QUERY);
		}

		@Override
		public void saveSinglePerson(Person p) {
			super.save(p);
		}
		
}
