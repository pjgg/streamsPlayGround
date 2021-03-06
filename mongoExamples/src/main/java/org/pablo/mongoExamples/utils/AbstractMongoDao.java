package org.pablo.mongoExamples.utils;

import java.util.List;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.util.StreamUtils;

public abstract class AbstractMongoDao<E, F, I> {

	private static final Logger LOG = LoggerFactory
			.getLogger(AbstractMongoDao.class);

	private final String collectionName;
	private final MongoOperations mongoOperations;

	protected AbstractMongoDao(String collectionName,
			MongoOperations mongoOperations) {
		this.collectionName = collectionName;
		this.mongoOperations = mongoOperations;
	}

	/**
	 * @return the collectionName
	 */
	protected String getCollectionName() {
		return collectionName;
	}

	/**
	 * @return the mongoOperations
	 */
	protected MongoOperations getMongoOperations() {
		return mongoOperations;
	}

	protected List<E> find(Query query) {
		return mongoOperations.find(query, getEntityClass(),
				getCollectionName());
	}

	protected Stream<E> findWithStream(Query query) {
		return StreamUtils.createStreamFromIterator(mongoOperations.stream(
				query, getEntityClass()));
	}

	protected E save(E entity) {
		mongoOperations.save(entity, collectionName);
		return entity;
	}

	/* ----------------- template methods -- */
	protected abstract Class<E> getEntityClass();

	protected abstract String getStorageField(F field);
}
