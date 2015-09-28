package org.pablo.filterSortMapExamples.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import junit.framework.TestCase;

import org.pablo.filterSortMapExamples.FilterSortMapService;
import org.pablo.filterSortMapExamples.ServiceLocator;
import org.pablo.filterSortMapExamples.dto.PersonDTO;
import org.pablo.filterSortMapExamples.model.Person;

public class MapTest extends TestCase{

	private FilterSortMapService filterSortMapService = ServiceLocator.INSTANCE.getFilterSortMapService();
	//private PersonToPersonDtoConverter converter = ServiceLocator.INSTANCE.getConverter();
	private List<Person> people = new ArrayList<Person>();
	
	@Override
	public void setUp() {
		Person p1 = new Person(1,23,"M","Rick","Beethovan");
	    Person p2 = new Person(2,13,"F","Martina","Hengis");
	    Person p3 = new Person(3,43,"M","Ricky","Martin");
	    Person p4 = new Person(4,26,"M","Jon","Lowman");
	    Person p5 = new Person(5,19,"F","Cristine","Maria");
	    Person p6 = new Person(6,15,"M","David","Feezor");
	    Person p7 = new Person(7,68,"F","Melissa","Roy");
	    Person p8 = new Person(8,79,"M","Alex","Gussin");
	    Person p9 = new Person(9,15,"F","Neetu","Singh");
	    Person p10 = new Person(10,45,"M","Naveen","Jain");
	    
	    people.addAll(Arrays.asList(new Person[]{p1,p2,p3,p4,p5,p6,p7,p8,p9,p10}));
	}
	
	public void testMap(){
		Stream<PersonDTO> result = filterSortMapService.mapPeopleToPeopleDTO(people.stream());
		org.junit.Assert.assertEquals(result.count(), 10);
	}
	
}
