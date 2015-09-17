package org.pablo.completableFuture.utils;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.mongodb.core.query.Query;

public interface MongoDao<E, F, I> {

	List<E> find(String applicationId, Query query);

	Stream<E> findWithStream(String applicationId, Query query);

	E save(String applicationId, E entity);

}