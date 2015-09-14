package org.pablo.mongoExamples.utils;

import java.util.List;
import java.util.stream.Stream;

import org.bson.Document;
import org.pablo.mongoExamples.exceptions.MongoException;
import org.pablo.mongoExamples.springDataMongo.reactive.ReactiveMongoTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class ReactiveAbstractMongoDao {

	private static final Logger LOG = LoggerFactory
			.getLogger(ReactiveAbstractMongoDao.class);

	private final String collectionName;
	private final ReactiveMongoTemplate reactiveMongoTemplate;

	protected ReactiveAbstractMongoDao(String collectionName,
			ReactiveMongoTemplate mongoOperations) {
		this.collectionName = collectionName;
		this.reactiveMongoTemplate = mongoOperations;
	}

	protected List<Document> findAll() throws MongoException {
		try {
			return reactiveMongoTemplate.findAll(getCollectionName());
		} catch (Throwable e) {
			throw new MongoException(e.getMessage());
		}
	}

	protected Stream<Document> findWithStream() throws MongoException {
		try {
			return reactiveMongoTemplate.stream(getCollectionName());
		} catch (Throwable e) {
			e.printStackTrace();
			throw new MongoException(e.getMessage());
		}
	}

	protected void save(Document entity) {
		reactiveMongoTemplate.saveSingle(getCollectionName(), entity);
	}

	public String getCollectionName() {
		return collectionName;
	}

}
