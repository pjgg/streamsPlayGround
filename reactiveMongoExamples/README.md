# Streams Play Ground

This is a simple multi-pom project to have fun and test java Stream API, completable futures and other new java 8 features. 

## reactive mongoExamples

There are new paradigms (new ways to think, feel and act) in the way that the different actors or components interact one each other in IT. The first time that hear about 'reactive' was two years ago, by TypeSafe company. Basically they talk about an event drive architecture where you have pubishers and consumers. This give you more flexibility (scalable), and at the same time is fault tolerant. All of this concept (that I explain very poorly) are nice, but we have to test it!. 

We are going to integrate <a href="http://mongodb.github.io/mongo-java-driver-reactivestreams/">
MongoDB Reactive Streams Java Driver</a>  in our test app, and then run the same test that we made with <a href="https://github.com/pjgg/streamsPlayGround/tree/master/reactiveMongoExamples"> Spring Mongo data. </a>

I will store 100 person in a mongo database and then I will retrieve those records by a simple 'getAll' query. Then I will make some filter operation over the results and check which way gives a better performance (Streams VS List). 

To test the performance I will use JMH micro benchmark framework. 

## Result
 
 ```
 Server: i7
 OS: OS x 10.8.3
 warmup: 10 iterations
 measure: 50 iterations
 

Benchmark       
                                                    Mode   Cnt     Score    Error  Units                                                                               
findAllListAndFilterAndUpperCaseReactiveStreams    thrpt   50  2198.694 ± 143.219  ops/s
findAllStreamAndFilterAndUpperCaseReactiveStreams  thrpt   50  2310.603 ±  55.712  ops/s

 ```

In other words

 ```
 Spring Data Mongo  List         2048.712 ops
 Mongon Data Mongo  Streams      2247.563 ops
 ```
 ```
 Mongo Reactive     List         2198.694 ops
 Mongo Reactive     Streams      2310.603 ops
 ```
 
## Conclusion

Spring Data it's quite powerful, and also the performance it's not bad if you think in all the stuff it's give you. How ever if you require a high performance aoolication maybe you should consider to move on to a Reactive Streams paradigm, improving your performance in at least 30% (think that we didn't play with parallel Streams).  

 



