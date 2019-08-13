[![ko-fi](https://www.ko-fi.com/img/githubbutton_sm.svg)](https://ko-fi.com/S6S0YXPX)

# JPA on Standalone Java Application

This application demonstrates the use of JPA in a standalone Java application.

## Getting Started

This project is a standalone Java application that reads a server log file, parse and save to a MySQL table access_log.

It also executes a named query that groups and filter the accesslog table via IP and threshold. It logs the grouped and filtered records in console and another MySQL table named block_ip.

The project is created using maven and use an entity manager to manage the entities. It compiles a project with all the dependencies and create a jar named parser-jar-with-dependencies.jar in the target folder.

*Note that you must set your timezone to UTC.

### Prerequisites

 * Java 8
 * Maven

### Installing

Clone the repository and let it download all the dependencies.

## Running the tests

Before running the command the database must be reset first. Execute "sql/1 - schema.sql".

You must be inside the target folder before you execute the following commands.

```sh
java -jar parser-jar-with-dependencies.jar --startDate=2017-01-01.15:00:00 --duration=hourly --threshold=200 --accesslog=D:\Downloads\exercise\parser\src\main\resources\access.log
>The output will have 192.168.11.231 which has 200 or more requests between 2017-01-01.15:00:00 and 2017-01-01.15:59:59

java -jar parser-jar-with-dependencies.jar --startDate=2017-01-01.00:00:00 --duration=daily --threshold=500 --accesslog=D:\Downloads\exercise\\parser\src\main\resources\access.log
>The output will have 192.168.102.136 which has 500 or more requests between 2017-01-01.00:00:00 and 2017-01-01.23:59:59 
```

The SQL use to create the schema and test the queries are stored in exercise/parser/sql folder.

Where

* startDate - the lower data boundary when a line was created on the log file
* duration - either hourly (1hour) or daily (24hours)
* threshold - the minimum ip count given the date range
* accesslog - path to log file

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Authors

* **Edward P. Legaspi <czetsuya@gmail.com>** - *Software Architect* - [czetsuya](https://github.com/czetsuya)

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
