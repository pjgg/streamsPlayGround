package org.pablo.mongoExamples.springDataMongo.reactive;

import com.mongodb.reactivestreams.client.MongoDatabase;

public class ReactiveDbFactory {

	private final MongoDatabase database;

	public MongoDatabase getDatabase() {
		return database;
	}

	public ReactiveDbFactory(ReactiveMongoDB reactiveMongoDB,
			String dataBaseName) {
		database = reactiveMongoDB.getMongoClient().getDatabase(dataBaseName);
	}
}
