package org.pablo.mongoExamples.springDataMongo.reactive;

import static java.util.Arrays.asList;

import java.net.UnknownHostException;

import com.mongodb.ServerAddress;
import com.mongodb.async.client.MongoClientSettings;
import com.mongodb.connection.ClusterSettings;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;

public class ReactiveMongoDB {
	
	private final MongoClient mongoClient;
	
	public ReactiveMongoDB(String host) throws UnknownHostException{
		ClusterSettings clusterSettings = ClusterSettings.builder().hosts(asList(new ServerAddress(host))).build();
		MongoClientSettings settings = MongoClientSettings.builder().clusterSettings(clusterSettings).build();
		mongoClient = MongoClients.create(settings);
	}

	public MongoClient getMongoClient() {
		return mongoClient;
	}
	
}
