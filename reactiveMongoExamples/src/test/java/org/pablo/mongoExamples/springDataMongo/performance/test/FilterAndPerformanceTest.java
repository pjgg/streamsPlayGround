package org.pablo.mongoExamples.springDataMongo.performance.test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pablo.basicExamples.FilterAndAction;
import org.pablo.mongoExamples.exceptions.MongoException;
import org.pablo.mongoExamples.model.Person;
import org.pablo.mongoExamples.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:mongoExamples-context.xml")
public class FilterAndPerformanceTest {
	
	private final static int SAMPLES_AMOUNT = 100;

	private final static String[] DICTIONARY_NAMES = { "Jare", "David", "Dani",
			"Alberto", "Pablo", "Maria", "Luis", "Roque", "Kike", "Javi",
			"Giovana", "Mati", "Fernando" };

	private List<Person> stringTestList = new ArrayList<Person>();
	
	@Autowired
	private PersonRepository reactivePersonRepository;
	
	private FilterAndAction filterAndAction = new FilterAndAction();
	
	@Ignore
	@Test
	public void basicTest() throws MongoException{
		Stream<Person> people = reactivePersonRepository.findAllPersonStreams();
		Stream<String> result = filterAndAction.filterWordsLongerThan4AndUpperCaseExample(people.map(p ->p.getFirstname()));
		result.max(new forTestComparator()::compare);
	}
	
	@Ignore
	@Test
	public void saveTest() throws MongoException{
		reactivePersonRepository.saveSinglePerson(new Person("test","test"));
	}
	
	public static class forTestComparator implements Comparator<String> {

		@Override
		public int compare(String val1, String val2) {
			return val1.compareTo(val2);
		}
	}

}
