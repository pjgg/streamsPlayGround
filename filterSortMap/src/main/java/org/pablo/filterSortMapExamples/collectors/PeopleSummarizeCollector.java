package org.pablo.filterSortMapExamples.collectors;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import org.pablo.filterSortMapExamples.model.PeopleSummarize;
import org.pablo.filterSortMapExamples.model.Person;



public class PeopleSummarizeCollector implements Collector<Person, PeopleSummarize , PeopleSummarize>{

	@Override
	public Supplier<PeopleSummarize> supplier() {
		return PeopleSummarize::new;
	}

	@Override
	public BiConsumer<PeopleSummarize, Person> accumulator() {
		return (PeopleSummarize, Person) -> {
			PeopleSummarize.setAgeSum(Person.getAge() + PeopleSummarize.getAgeSum());
			PeopleSummarize.setFemaleSum((Person.getGender().equalsIgnoreCase("F"))?PeopleSummarize.getFemaleSum() + 1: PeopleSummarize.getFemaleSum());
			PeopleSummarize.setMaleSum((Person.getGender().equalsIgnoreCase("M"))?PeopleSummarize.getMaleSum() + 1: PeopleSummarize.getMaleSum());
	      };
	}

	@Override
	public BinaryOperator<PeopleSummarize> combiner() {
		return (acc1, acc2) -> {
		    throw new UnsupportedOperationException();
		  };
	}

	@Override
	public Function<PeopleSummarize, PeopleSummarize> finisher() {
		throw new UnsupportedOperationException("Finisher not possible");
	}

	@Override
	public Set<java.util.stream.Collector.Characteristics> characteristics() {
		return new HashSet<Characteristics>(Arrays.asList(Characteristics.IDENTITY_FINISH));
	}

}
