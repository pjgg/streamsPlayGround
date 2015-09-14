package org.pablo.mongoExamples.springDataMongo.reactive;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import org.bson.Document;
import org.pablo.mongoExamples.utils.SubscriberHelpers.ObservableSubscriber;
import org.pablo.mongoExamples.utils.SubscriberHelpers.OperationSubscriber;
import org.reactivestreams.Publisher;

import com.mongodb.reactivestreams.client.MongoCollection;
import com.mongodb.reactivestreams.client.Success;

public class ReactiveMongoTemplate {

	private final ReactiveDbFactory reactiveDbFactory;

	public ReactiveMongoTemplate(ReactiveDbFactory reactiveDbFactory) {
		this.reactiveDbFactory = reactiveDbFactory;
	}

	public List<Document> findAll(String collectionName) throws Throwable {
		MongoCollection<Document> collection = reactiveDbFactory.getDatabase()
				.getCollection(collectionName, Document.class);
		ObservableSubscriber<Document> subscriber = new ObservableSubscriber<Document>();
		collection.find().subscribe(subscriber);
		return subscriber.get(60000, TimeUnit.MILLISECONDS);
	}

	public Stream<Document> stream(String collectionName) throws Throwable {
		MongoCollection<Document> collection = reactiveDbFactory.getDatabase()
				.getCollection(collectionName, Document.class);
		ObservableSubscriber<Document> subscriber = new ObservableSubscriber<Document>();
		collection.find().subscribe(subscriber);
		return subscriber.get(10000, TimeUnit.MILLISECONDS).stream();
	}

	public void saveSingle(String collectionName, Document document) {
		MongoCollection<Document> collection = reactiveDbFactory.getDatabase()
				.getCollection(collectionName, Document.class);
		OperationSubscriber<Success> subscriber = new OperationSubscriber<Success>();
		Publisher<Success> publisher = collection.insertOne(document);
		publisher.subscribe(subscriber);
	}

}
