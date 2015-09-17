# Streams Play Ground

This is a simple multi-pom project to have fun and test java Stream API, completable futures and other new java 8 features. 

## mongoExamples

Continue with Java 8 features, I would like to test SpringData 1.8, more in detail how fast are make operations between List VS Streams. 

Nowadays there are at least one method in Spring MongoTemaplate 1.8 allow you to play with Streams 


```
<T> CloseableIterator<T>	stream(Query query, Class<T> entityType)
``` 

However this method returns an Iterator. To swap this dataType into a stream we will have to use Spring StreamUtils that make the propper conversion into a Stream. 

There are other ways to play with Streams in Spring as using dynamic repositories however I will not test this way. 

Example <a href="https://github.com/spring-projects/spring-data-examples/blob/master/jpa/java8/src/main/java/example/springdata/jpa/java8/CustomerRepository.java">
external example link</a>.</p>

I will store 100 person in a mongo database and then I will retrieve those records by a simple 'getAll' query. Then I will make some filter operation over the results and check which way gives a better performance (Streams VS List). 

To test the performance I will use JMH micro benchmark framework. 

## Result
 
 ```
 Server: i7
 OS: OS x 10.8.3
 warmup: 20 iterations
 measure: 50 iterations
 
 Benchmark                                                                                                             
 
                                            Mode  Cnt     Score    Error  Units
findAllAndFilterAndUpperCaseIterating       thrpt   50  2085.862 ± 8.870  ops/s
findAllStreamsAndFilterAndUpperCaseStreams  thrpt   50  2393.740 ± 34.195  ops/s

 ```

In other words

 ```
 Spring Data Mongo  List         2085.862 ops
 Spring Data Mongo  Streams      2393.740 ops
 ```
So it's slightly better Streams than List, when you read and operate mongo records. 

Maybe the next question should be… and what about mongo reactive? improve the Streams results… you could check it by your self in the following  <a href="https://github.com/pjgg/streamsPlayGround/tree/master/reactiveMongoExamples"> test</a>.
 



