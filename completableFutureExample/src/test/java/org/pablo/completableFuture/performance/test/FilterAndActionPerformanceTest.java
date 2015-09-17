package org.pablo.completableFuture.performance.test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
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
import org.pablo.completableFuture.model.Person;
import org.pablo.completableFuture.repository.PersonRepository;
import org.pablo.completableFuture.repository.PersonRepositoryImpl;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mongodb.MongoException;

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
				"completableFuture-context.xml")
				.getBean(PersonRepositoryImpl.class);

//		 for (int i = 0; i < SAMPLES_AMOUNT; i++) {
//		 String temp = DICTIONARY_NAMES[(new
//		 Random()).nextInt(DICTIONARY_NAMES.length)];
//		
//		 stringTestList.add(new Person(temp,temp));
//		 }
	}

//	 @Benchmark
//	 public void savePersonIterating() {
//	
//	 for(Person p: stringTestList){
//	 personRepository.saveSinglePerson(p);
//	 }
//	
//	 }

	@Benchmark
	public void findAllAndFilterAndUpperCaseList() throws MongoException, InterruptedException, ExecutionException {
		
		CompletableFuture<List<Person>> people = personRepository.findAllPersonList();
		people.thenApply(p -> getNames(p)).thenApply(names -> filterAndAction.filterWordsLongerThan4AndUpperCaseExample(names).max(new forTestComparator()::compare));	
	}

	@Benchmark
	public void findAllStreamsAndFilterAndUpperCaseStreams()
			throws MongoException {
		CompletableFuture<Stream<Person>> people = personRepository.findAllPersonStreams();
		people.thenApply(p -> filterAndAction.filterWordsLongerThan4AndUpperCaseExample(p.map(person -> person.getFirstname())).max(new forTestComparator()::compare));

	}

	public static class forTestComparator implements Comparator<String> {

		@Override
		public int compare(String val1, String val2) {
			return val1.compareTo(val2);
		}
	}
	
	private Stream<String> getNames(List<Person> people){
		return people.stream().map(p -> p.getFirstname());
	}

}
