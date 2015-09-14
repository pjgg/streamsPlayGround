package org.pablo.mongoExamples.exceptions;

public class MongoException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public MongoException(String msg){
		super(msg);
	}

}
