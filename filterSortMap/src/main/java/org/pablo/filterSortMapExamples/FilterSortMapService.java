package org.pablo.filterSortMapExamples;

import java.util.Comparator;
import java.util.function.Predicate;
import java.util.stream.Stream;

import org.pablo.filterSortMapExamples.dto.PersonDTO;
import org.pablo.filterSortMapExamples.functions.PersonToPersonDtoConverter;
import org.pablo.filterSortMapExamples.model.Person;
import org.pablo.filterSortMapExamples.predicates.PersonPredicates;


public class FilterSortMapService{		
	
	private final PersonToPersonDtoConverter converter;
	
	public FilterSortMapService(){
		converter = new PersonToPersonDtoConverter();
	}
	
	public Stream<Person> filterPerson(Stream<Person> people, Predicate<Person> filterCondition){
		return PersonPredicates.filterEmployees(people, filterCondition);
	}
	
	public Stream<Person> sortPeople(Stream<Person> people, Comparator<Person> comparatorCondition,  boolean reversed){
		return (reversed)?people.sorted(comparatorCondition.reversed()):people.sorted(comparatorCondition);
	}
	
	public Stream<PersonDTO> mapPeopleToPeopleDTO(Stream<Person> people){
		return converter.convertToStream(people);
	}
	
}
