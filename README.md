# MockServer

This is a simple Java application that can return custom server response. This is useful to test integration with 3rd party API/service. You can upload a list of responses to the MockServer and it will
return the responses in the order they were uploaded.


# Requirements
MockServer requires the following to run:

  * [Java 8][java]
  * [Apache Maven 3.3.9][maven]

  [java]: http://www.oracle.com/technetwork/java/javase/overview/index.html
  [maven]: https://maven.apache.org/


# Compile and Run

    mvn package
    java -jar target/mock-server-0.0.1.jar server mock-server.yml

# Example
Following example uploads a file with name "sample" and adds timeout value of 2000ms

    curl -i -F file=@sample  http://localhost:8080/exchanges/upload?timeout=2000

The following makes a request to and it will return the value in the file sample.

    curl http://localhost:8080/exchanges/request/UrlOfYourChoice

If you uploaded 3 files, then the first request to 
    /exchanges/request/{some url} 
will return the first file as response, second request will return the second file as response. For the fourth request then the server will back to returning
the first response.

