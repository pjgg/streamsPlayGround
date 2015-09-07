# Streams Play Ground

This is a simple multi-pom project to have fun and test java Stream API, completable futures and other new java 8 features. 

## basicExamples

Comparison between Java 8  Stream API and GS Collections through JMH micro benchmark.

Java 8 has come with a lot of new features as declarative functional programming functions (Lamdba expressions), completableFutures (fixing java 7 futures, locking thread behavior), or the new Streams API, that it's a new way to handler collections and manage parallelism.  

In this module I will be focus in Streams API, more in detail I would like to compare Java 8 VS Gs Collections VS iterating model. 

If you have a look to different approaches to manage collections you would find four ways:
-  Iterating module
-  Java 8 Streams
-  Gs Collections
-  Scala

Having a look to several videos as http://www.infoq.com/presentations/java-streams-scala-parallel-collections and documents as http://www.slideshare.net/InfoQ/parallellazy-performance-java-8-vs-scala-vs-gs-collections you get the conclusion that Gs Collections it's far away the best approach, however as a engineer you have to test it, I mean you should not believe what a random person tell you in order to make a decision, you should build your own test suit and check if make sense what this person is telling you. 

According GSColleciton guys a serial Count operation performance, rounds the following numbers:

- GS Collection 370 operations per second (ops)
- Java 8 220 ops
- Scala 170 ops

I am going to avoid set up a Scala environment, because it's not my target measure Scala performance(I agree with GSC guys). So that I am going to validate are the other cases. In stead of a serial count example, that it's trivial, I will create a List of 1 millions of random names (Strings) and then I will filter those names that are longer than 4 characters. From those names, then I will make them upper case, and return the result list, and finally make a basic comparison operation (max operation). In order that this performance test make sense I have to use the same benchmark library that GS Collection use, and also the input names list must be exactly the same, in all the cases.   


- Iterating module code 

```
     public Set<String> filterWordsLongerThan4AndUpperCaseExampleIterating(List<String> input){
		Set<String> result = new HashSet<String>(); 
		for(String word: input){
			if(word.length() > 4){
				result.add(word.toUpperCase());
			}
		}
		
		return result;
	}
```  


- Java 8 Streams Lazy code

```
   public Stream<String> filterWordsLongerThan4AndUpperCaseExample(Stream<String> input){		
		return input.filter(e -> e.length() > 4).map(String::toUpperCase);
	}
```  

- Gs Collection Streams Lazy code

```
    public LazyIterable<String> filterWordsLongerThan4AndUpperCaseGsCollectionTest(LazyIterable<String> lazyIterable){
		 return lazyIterable.select(e -> e.length() > 4).collect(String::toUpperCase);
	}
```
- final comparison operation

```
public static class forTestComparator implements Comparator<String> {

        @Override
        public int compare(String val1, String val2) {
            return val1.compareTo(val2);
        }
    }
```

So all the approaches are doing the same. It's important in order to be comparison fair.

## Result
 
 ```
 Server: i7
 OS: OS x 10.8.3
 warmup: 5 iterations
 measure: 50 iterations
 
Benchmark
                                                                  Mode  Cnt   Score   Error  Units
filterWordsLongerThan4AndUpperCaseGsCollectionEagerTest          thrpt   50  21.720 ± 0.142  ops/s
filterWordsLongerThan4AndUpperCaseGsCollectionLazyTest           thrpt   50  23.806 ± 0.078  ops/s
filterWordsLongerThan4AndUpperCaseGsCollectionParallelTest       thrpt   50  50.217 ± 0.082  ops/s
filterWordsLongerThan4AndUpperCaseGsCollectionUnmodificableTest  thrpt   50  20.337 ± 0.088  ops/s
filterWordsLongerThan4AndUpperCaseIretaringTest                  thrpt   50  19.993 ± 0.072  ops/s
filterWordsLongerThan4AndUpperCaseJava8StreamsParallelTest       thrpt   50  43.960 ± 0.353  ops/s
filterWordsLongerThan4AndUpperCaseJava8StreamsTest               thrpt   50  23.102 ± 0.196  ops/s
 ```
 
 To sum up we can conclude that Java 8 Stream API improve the performance compare to iteration model approach. 
 
 ```
 Java 8 Stream         23.102 ops
 Java 8 Iterating      19.993 ops
 ```
 
 and also we can conclude that Gs Collection library has a better performance than Java 8 Stream API
 ```
 Java 8 Stream         23.102 ops
 GS Collection lazy    23.806 ops
 ```
 On the other hand both introduce a fancy way to make a code parallel (fork and join approach) and double the performance. Note that not all the problems are parallel. Sometimes you need to synchronize results and make it sequential in practice. In those cases the performance could be even worst if you make it parallel. 
 ```
Java 8 Stream parallel 43.960 ops
GS Collection parallel 50.217 ops
```

## Conclusion
 
 Java 8 Stream API make collection operation more readable (less verbose) and improve their performance. You will have tons of new operation that you can make over your stream as filter, sort, map, matching, reducing...collect, so it's something that you should keep in mind. Also sometimes you can make a function parallel in a very simple way (just write stream.parallel), improving the performance even more, in a multi-core environment.  
 
 On the other hand you have open source implementations as Gs Collection, that make some code optimization, improving the standard performance and also give you some extra data structures and function such as immutable list, eager behavior, multiset (bags), structures etc... Gs Collection guys did a great job!. Maybe nowadays, it's the fastest Stream implementation over all languages and technologies and less memory consumption. You should also keep in mind this library, in case you have a high performance requirements.  
 



