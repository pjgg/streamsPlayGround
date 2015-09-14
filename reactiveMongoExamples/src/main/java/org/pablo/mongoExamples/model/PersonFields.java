package org.pablo.mongoExamples.model;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum PersonFields {
	
	ID(Person.ID),
	FIRST_NAME(Person.FIRST_NAME),
	LAST_NAME(Person.LAST_NAME);
	
	private String type;
	private static final Map<String,PersonFields> lookUp = new HashMap<String,PersonFields>();
	
	static {
		for(PersonFields applicationField : EnumSet.allOf(PersonFields.class))
			lookUp.put(applicationField.getCode(), applicationField);
	}
	
	private PersonFields(String type){
		this.type = type;
	}
	
	public String getCode(){
		return type;
	}
	
	public static PersonFields getType(final String code){
		if (lookUp.containsKey(code))
			return lookUp.get(code);
		throw new IllegalStateException();
	}
};
