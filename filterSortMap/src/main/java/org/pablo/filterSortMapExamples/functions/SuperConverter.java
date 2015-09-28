package org.pablo.filterSortMapExamples.functions;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@FunctionalInterface
public interface SuperConverter<A, B> extends Function<A, B> {
	
    default Stream<B> convertToStream(final Stream<A> input) {
        return input.map(this::apply);
    }
    
    default List<B> convertToList(final List<A> input) {
        return input.stream().map(this::apply).collect(Collectors.toList());
    }
   
}
