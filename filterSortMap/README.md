# Streams Play Ground

This is a simple multi-pom project to have fun and test java Stream API, completable futures and other new java 8 features. 

## filterSortMap

This is a functional example about filters, sort, and maps. 

- Talk about filters, it's talk about predicates.
- Talk about sort, it's talk about comparators.
- Talk about map, it's talk about functions.
 
 Once you have those concepts clear, then the only things that you should do is decouple predicates, comparators and functions from your business logic. So you will have domain objects, 
 DTOs, functions, predicates, comparators and managers (business logic) as a controller.  
 
 This project try to give an example of all of this concepts. 
 
 ## Conclusions
 
The basic syntax of a lambda expression is

 ``` 
either
(parameters) -> expression
or
(parameters) -> { statements; }
or
() -> expression
 ```
 
example

 ``` 
(int a, int b) ->    a * b                           // takes two integers and returns their multiplication
(a, b)          ->   a - b                           // takes two numbers and returns their difference
() -> 99                                             // takes no values and returns 99
(String a) -> System.out.println(a)                  // takes a string, prints its value to the console, and returns nothing
a -> 2 * a                                       // takes a number and returns the result of doubling it
c -> { //some complex statements }               // takes a collection and do some procesing
 ```
 
A lambda expression always receive a functionalInterface as a parameter. 
A Single Abstract Method interfaces(functionalInterface) is an interface with one abstract method and N implemented methods. You can extends functions that it's a functional Interface with 'apply' method as an abstract method. 

source: http://howtodoinjava.com/2014/03/25/complete-lambda-expressions-tutorial-in-java/
 



