package org.pablo.basicExamples.performance.test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
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

import com.gs.collections.api.LazyIterable;
import com.gs.collections.api.list.ImmutableList;
import com.gs.collections.api.list.MutableList;
import com.gs.collections.api.list.ParallelListIterable;
import com.gs.collections.impl.factory.Lists;
import com.gs.collections.impl.list.mutable.FastList;

@State(Scope.Thread)
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.SECONDS)
public class FilterAndActionPerformanceTest {
	
	private final static int SAMPLES_AMOUNT = 1000000;
	
	private final static String[] DICTIONARY_NAMES = {"Jare", "David", "Dani", "Alberto", "Pablo", "Maria", "Luis", "Roque", "Kike", "Javi", "Giovana", "Mati", "Fernando"};
	
	private FilterAndAction filterAndAction = new FilterAndAction();
	
	private List<String> stringTestList = new ArrayList<String>();
	
	
	ExecutorService threadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
	int batchSize = 10_000;
	
	private FastList<String> fastList = new FastList();
		
	@Setup
    public void setUp()
    {
		
		for(int i = 0; i < SAMPLES_AMOUNT; i++){
			String temp = DICTIONARY_NAMES[(new Random()).nextInt(DICTIONARY_NAMES.length)];
			
			fastList.add(temp);
			stringTestList.add(temp);
		}        
    }
	
	@Benchmark
	public void filterWordsLongerThan4AndUpperCaseJava8StreamsTest(){
    	Stream<String> result = filterAndAction.filterWordsLongerThan4AndUpperCaseExample(stringTestList.stream());
    	result.max(new forTestComparator()::compare);
	}
	
	@Benchmark
	public void filterWordsLongerThan4AndUpperCaseJava8StreamsParallelTest(){
    	Stream<String> result = filterAndAction.filterWordsLongerThan4AndUpperCaseExample(stringTestList.stream().parallel());
    	result.max(new forTestComparator()::compare);
	}
	
	@Benchmark
	public void filterWordsLongerThan4AndUpperCaseIretaringTest(){
    	Set<String> result = filterAndAction.filterWordsLongerThan4AndUpperCaseExampleIterating(stringTestList);
    	result.stream().max(new forTestComparator()::compare);
	}
    
	@Benchmark
	public void filterWordsLongerThan4AndUpperCaseGsCollectionLazyTest(){
    	LazyIterable<String> result = filterAndAction.filterWordsLongerThan4AndUpperCaseGsCollectionTest(fastList.asLazy());
    	result.max(new forTestComparator()::compare);
	}
    
	@Benchmark
	public void filterWordsLongerThan4AndUpperCaseGsCollectionEagerTest(){
    	MutableList<String> result = filterAndAction.filterWordsLongerThan4AndUpperCaseGsCollectionEagerTest(fastList);
    	result.max(new forTestComparator()::compare);
	}
    
	@Benchmark
	public void filterWordsLongerThan4AndUpperCaseGsCollectionParallelTest(){
    	ParallelListIterable<String> result = filterAndAction.filterWordsLongerThan4AndUpperCaseGsCollectionTest(fastList.asParallel(threadPool, batchSize));
    	result.max(new forTestComparator()::compare);
	}
   
	@Benchmark
	public void filterWordsLongerThan4AndUpperCaseGsCollectionUnmodificableTest(){
    	ImmutableList<String> result = filterAndAction.filterWordsLongerThan4AndUpperCaseGsCollectionUnmodificableTest(Lists.immutable.ofAll(fastList));
    	result.max(new forTestComparator()::compare);
	}
    
    public static class forTestComparator implements Comparator<String> {

        @Override
        public int compare(String val1, String val2) {
            return val1.compareTo(val2);
        }
    }
	
}
