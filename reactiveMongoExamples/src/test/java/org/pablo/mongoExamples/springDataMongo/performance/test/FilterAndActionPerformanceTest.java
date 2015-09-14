package org.pablo.mongoExamples.springDataMongo.performance.test;

import java.util.Comparator;
import java.util.List;
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
import org.pablo.mongoExamples.repository.ReactivePersonRepositoryImpl;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@State(Scope.Thread)
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.SECONDS)
public class FilterAndActionPerformanceTest {
	
	private PersonRepository reactivePersonRepository;
	
	private FilterAndAction filterAndAction = new FilterAndAction();

	@Setup
	public void setUp() {		
		reactivePersonRepository = new ClassPathXmlApplicationContext("mongoExamples-context.xml").getBean(ReactivePersonRepositoryImpl.class);
	}

	@Benchmark
	public void findAllListAndFilterAndUpperCaseReactiveStreams() throws MongoException {
		List<Person> people = reactivePersonRepository.findAllPersonList();
		Stream<String> result = filterAndAction.filterWordsLongerThan4AndUpperCaseExample(people.stream().map(p ->p.getFirstname()));
		result.max(new forTestComparator()::compare);
	}
	
	@Benchmark
	public void findAllStreamAndFilterAndUpperCaseReactiveStreams() throws MongoException {
		Stream<Person> people = reactivePersonRepository.findAllPersonStreams();
		Stream<String> result = filterAndAction.filterWordsLongerThan4AndUpperCaseExample(people.map(p ->p.getFirstname()));
		result.max(new forTestComparator()::compare);
	}
	
	public static class forTestComparator implements Comparator<String> {

		@Override
		public int compare(String val1, String val2) {
			return val1.compareTo(val2);
		}
	}

}
