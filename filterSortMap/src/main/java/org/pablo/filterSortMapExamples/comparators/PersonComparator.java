package org.pablo.filterSortMapExamples.comparators;

import java.util.Comparator;

import org.pablo.filterSortMapExamples.model.Person;

public class PersonComparator {

	public static Comparator<Person> byFirstName() {
        return Comparator.comparing(Person::getFirstName);
    }
	
	public static Comparator<Person> byAge() {
        return Comparator.comparing(Person::getAge);
    }
	
	public static Comparator<Person> byFirstNameAndLastName() {
        return Comparator.comparing(Person::getFirstName).thenComparing(Person::getLastName);
    }
	
}
