package org.pablo.filterSortMapExamples.predicates;

import java.util.function.Predicate;
import java.util.stream.Stream;

import org.pablo.filterSortMapExamples.model.Person;

public class PersonPredicates {

	public static Predicate<Person> isAdultMale() {
        return p -> p.getAge() > 21 && p.getGender().equalsIgnoreCase("M");
    }
     
    public static Predicate<Person> isAdultFemale() {
        return p -> p.getAge() > 18 && p.getGender().equalsIgnoreCase("F");
    }
     
    public static Predicate<Person> isAgeMoreThan(Integer age) {
        return p -> p.getAge() > age;
    }
     
    public static Stream<Person> filterEmployees (Stream<Person> people, Predicate<Person> predicate) {
        return people.filter( predicate );
    }
    
}
