package org.pablo.basicExamples;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import com.gs.collections.api.LazyIterable;
import com.gs.collections.api.list.ImmutableList;
import com.gs.collections.api.list.MutableList;
import com.gs.collections.api.list.ParallelListIterable;

public class FilterAndAction {		
	
	public Stream<String> filterWordsLongerThan4AndUpperCaseExample(Stream<String> input){		
		return input.filter(e -> e.length() > 4).map(String::toUpperCase);
	}
	
	public Set<String> filterWordsLongerThan4AndUpperCaseExampleIterating(List<String> input){
		Set<String> result = new HashSet<String>(); 
		for(String word: input){
			if(word.length() > 4){
				result.add(word.toUpperCase());
			}
		}
		
		return result;
	}
	
	public LazyIterable<String> filterWordsLongerThan4AndUpperCaseGsCollectionTest(
			LazyIterable<String> lazyIterable) {
		return lazyIterable.select(e -> e.length() > 4).collect(
				String::toUpperCase);
	}
	
	public ParallelListIterable<String> filterWordsLongerThan4AndUpperCaseGsCollectionTest(ParallelListIterable<String> parallelListIterable){
		 return parallelListIterable.select(e -> e.length() > 4).collect(String::toUpperCase);
	}
	
	public MutableList<String> filterWordsLongerThan4AndUpperCaseGsCollectionEagerTest(MutableList<String> mutableList){
		 return mutableList.select(e -> e.length() > 4).collect(String::toUpperCase);
	}
	
	public ImmutableList<String> filterWordsLongerThan4AndUpperCaseGsCollectionUnmodificableTest(ImmutableList<String> mutableList){
		 return mutableList.select(e -> e.length() > 4).collect(String::toUpperCase);
	}
	
	public MutableList<String> filterWordsLongerThan4AndUpperCaseGsCollectionTest(MutableList<String> mutableList){
		 return mutableList.select(e -> e.length() > 4).collect(String::toUpperCase);
	}
	
}
