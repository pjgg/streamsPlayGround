# Streams Play Ground

This is a simple multi-pom project to have fun and test java Stream API, completable futures and other new java 8 features. 

## CompletableFutures mongoExamples

Spring data mongo doesn't returns completableFutures (if you are not using dynamic repositories). So, in order to play with this Java8 new data type you have to make the conversion by your self. In theory we should not gain anything in performance, because you make the same queries as usual and then you make the conversion. But let's test it. 

To test the performance I will use JMH micro benchmark framework. 

## Result
 
 ```
 Server: i7
 OS: OS x 10.8.3
 warmup: 20 iterations
 measure: 50 iterations
 

Benchmark                                    Mode  Cnt     Score    Error  Units
findAllAndFilterAndUpperCaseList            thrpt   50  2072.485 ± 34.161  ops/s
findAllStreamsAndFilterAndUpperCaseStreams  thrpt   50  2379.922 ± 32.185  ops/s

 ```

In other words

 ```
 Spring Data Mongo  List         2085.862 ops
 Spring Data Mongo  Streams      2393.740 ops
 ```
 
 ```
 Spring Data Mongo List CompletableFutures    2072.485 ops
 Spring Data Mongo Streams CompletableFutures  2379.922 ops 
 ``` 
 
 ```
 Mongo Reactive     List         2355.191 ops
 Mongo Reactive     Streams      2578.176 ops
 ```
 
 
## Conclusion

So actually the result are quite good, because you can see that the performance it's almost the same, in other words play with completableFutures over Spring Data Mongo doesn't add over head. 
 



