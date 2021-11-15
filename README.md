# psychic-journey
A journey into the World of RESTful apps and Spring.

## To build the application
$ ./mvnw clean package

## Spring Initializr uses maven wrapper so type this:
$ ./mvnw clean spring-boot:run

## Alternatively using your installed maven version type this:
$ mvn clean spring-boot:run

or use this command after run the fat (runnable) jar:
$ java -jar target/gs-rest-service-0.1.0.jar

The side effect of NOT including hypermedia in our representations is 
that clients MUST hard code URIs to navigate the API. This leads to 
the same brittle nature that predated the rise of e-commerce on the web. 
It’s a signal that our JSON output needs a little help.
