# Streams Play Ground

This is a simple multi-pom project to have fun and test java Stream API, completable futures and other new java 8 features. 

## reactive mongoExamples

There are new paradigms (new ways to think, feel and act) in the way that the different actors or components interact one each other in IT. The first time that hear about 'reactive' was two years ago, by TypeSafe company. Basically they talk about an event drive architecture where you have pubishers and consumers. This give you more flexibility (scalable), and at the same time is fault tolerant. All of this concept (that I explain very poorly) are nice, but we have to test it!. 

We are going to integrate <a href="http://mongodb.github.io/mongo-java-driver-reactivestreams/">
MongoDB Reactive Streams Java Driver</a>  in our test app, and then run the same test that we made with <a href="https://github.com/pjgg/streamsPlayGround/tree/master/mongoExamples"> Spring Mongo data. </a>

I will store 100 person in a mongo database and then I will retrieve those records by a simple 'getAll' query. Then I will make some filter operation over the results and check which way gives a better performance (Streams VS List). 

To test the performance I will use JMH micro benchmark framework. 

## Result
 
 ```
 Server: i7
 OS: OS x 10.8.3
 warmup: 20 iterations
 measure: 50 iterations
 

Benchmark       
                                                    Mode   Cnt     Score    Error  Units                                                                               
findAllListAndFilterAndUpperCaseReactiveStreams    thrpt   50  2355.191 ± 55.454  ops/s
findAllStreamAndFilterAndUpperCaseReactiveStreams  thrpt   50  2578.176 ±  47.759  ops/s

 ```

In other words

 ```
 Spring Data Mongo  List         2085.862 ops
 Spring Data Mongo  Streams      2393.740 ops
 ```
 ```
 Mongo Reactive     List         2355.191 ops
 Mongo Reactive     Streams      2578.176 ops
 ```
 
## Conclusion

Spring Data it's quite powerful, and also the performance it's not bad if you think in all the stuff it's give you. However if you require a high performance application maybe you should consider to move on to a Reactive Streams paradigm, improving your performance in at least 30% (think that we didn't play with parallel Streams).  

 



