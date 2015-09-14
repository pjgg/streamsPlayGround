package org.pablo.mongoExamples.performance.test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.pablo.basicExamples.FilterAndAction;
import org.pablo.mongoExamples.exceptions.MongoException;
import org.pablo.mongoExamples.model.Person;
import org.pablo.mongoExamples.repository.PersonRepository;
import org.pablo.mongoExamples.repository.PersonRepositoryImpl;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@State(Scope.Thread)
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.SECONDS)
public class FilterAndActionPerformanceTest {

	private final static int SAMPLES_AMOUNT = 100;

	private final static String[] DICTIONARY_NAMES = { "Jare", "David", "Dani",
			"Alberto", "Pablo", "Maria", "Luis", "Roque", "Kike", "Javi",
			"Giovana", "Mati", "Fernando" };

	private List<Person> stringTestList = new ArrayList<Person>();

	private PersonRepository personRepository;

	private FilterAndAction filterAndAction = new FilterAndAction();

	@Setup
	public void setUp() {

		personRepository = new ClassPathXmlApplicationContext(
				"mongoExamples-context.xml")
				.getBean(PersonRepositoryImpl.class);

		// for (int i = 0; i < SAMPLES_AMOUNT; i++) {
		// String temp = DICTIONARY_NAMES[(new
		// Random()).nextInt(DICTIONARY_NAMES.length)];
		//
		// stringTestList.add(new Person(temp,temp));
		// }
	}

	// @Benchmark
	// public void savePersonIterating() {
	//
	// for(Person p: stringTestList){
	// personRepository.saveSinglePerson(p);
	// }
	//
	// }

	@Benchmark
	public void findAllAndFilterAndUpperCaseIterating() throws MongoException {
		List<String> names = new ArrayList<String>();
		List<Person> people = personRepository.findAllPersonList();

		for (Person p : people) {
			names.add(p.getFirstname());
		}

		Set<String> result = filterAndAction
				.filterWordsLongerThan4AndUpperCaseExampleIterating(names);
		result.stream().max(new forTestComparator()::compare);
	}

	@Benchmark
	public void findAllStreamsAndFilterAndUpperCaseStreams()
			throws MongoException {
		Stream<Person> people = personRepository.findAllPersonStreams();
		Stream<String> result = filterAndAction
				.filterWordsLongerThan4AndUpperCaseExample(people.map(p -> p
						.getFirstname()));
		result.max(new forTestComparator()::compare);
	}

	public static class forTestComparator implements Comparator<String> {

		@Override
		public int compare(String val1, String val2) {
			return val1.compareTo(val2);
		}
	}

}
