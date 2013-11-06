# AKQA's Technical Test #

# Introduction #
Command Line Application that uses a file to create simple schedule.

The following instruction have only been executed on Mac OS X.

## Execution ##

### Requisites ###
The following software is needed to execute this application:
* Java 1.6
* Apache Maven 3.0.4

### Running Application ###
Execute the following to get the application up and running (assumes you are in 
the root of the project):

    mvn compile
    cd target/classes
    java com.akqa.scheduler.Scheduler input.txt
    

### Running Unit Tests ###
This assumes you are in the root of the project:

    mvn test
    
