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

I am going to avoid set up a Scala environment, because it's not my target measure Scala performance(I agree with GSC guys). So that I am going to validate are the other cases. In stead of a serial count example, that it's trivial, I will create a List of 1 millions of random names (Strings) and then I will filter those names that are longer than 4 characters. From those names, then I will make them upper case, and return the result list, and finally make a basic comparison operation (max operation). In order that this performance test make sense I have to use the same benchmark library that GS Collection uses, and also the input names list must be exactly the same, in all the cases.   


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


- Java 8 Streams Lazy

```
   public Stream<String> filterWordsLongerThan4AndUpperCaseExample(Stream<String> input){		
		return input.filter(e -> e.length() > 4).map(String::toUpperCase);
	}
```  

- Gs Collection Streams Lazy

```
    public LazyIterable<String> filterWordsLongerThan4AndUpperCaseGsCollectionTest(LazyIterable<String> lazyIterable){
		 return lazyIterable.select(e -> e.length() > 4).collect(String::toUpperCase);
	}
```


