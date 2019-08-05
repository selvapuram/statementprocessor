# statementprocessor
The repository is used to maintain the code base for rabobank statementprocessor coding challenge

## Key Notes on the Implementation
1. A Spring boot application with four rest APIs are developed (token, ping, validate, download)
2. ping is used to test the ping after service is up
3. token is used to generate and auth token to proceed further api
4. validate is the core api which is secured by auth token and validates the customer statement record based on the input file type and displays in json.
5. download is the core api which is secured by auth token and validates and downloads the report in XLS
6. Spring Batch is used to read the CSV or XML File, processes and saves it in db
7. Spring AOP is used to log the method entry and exit in the process packages
8. Spring Security is used to secure the APIs with dynamic uuid and stored in customer table.
9. Spring Data JPA is used for database operations
10. H2 db is the runtime scoped database used for this challenge
11. junit and jacoco coverage has been added.
12. The input files taken for processing are kept in the classpath inside(src/main/resources)
13. Externalization with application properties, swagger properties and messages properties
14. spring fox swagger is used in this application for rest api visualization
15. Spring Events are also created for Batch Started and Batch Completed

## Tech Stack
* Maven
* SpringBoot
* Java 8

## Building, Testing and Running statementprocessor

You will need:
* [statementprocessor source code](https://github.com/selvapuram/statementprocessor)
* [Java JDK](http://java.sun.com/javase/downloads/index.jsp)
* Apache Maven
    * [https://maven.apache.org](http://maven.apache.org)
    * [Maven on Windows](https://maven.apache.org/guides/getting-started/windows-prerequisites.html)
* A Unix/Linux shell environment OR the Windows command line

From the top level directory in the statementprocessor application you can build, test and run statementprocessor service using the <tt>./run</tt> shell script , or using the <tt>compile.bat and run.bat</tt> script from the Windows command line.

If you are working from the Windows command line you must also install a Java JDK, and [set the JAVA_HOME environment variable](http:confluence.atlassian.com/display/DOC/Setting+the+JAVA\_HOME+Variable+in+Windows) (please ensure it points to the JDK, and not the JRE) and the MVN\_HOME environment variable. You may need to reboot your machine after setting these environment variables. 

Ensure that you set your MAVEN_HOME environment variable, for example:

```MAVEN_HOME=E:\Downloads\apache-maven-3.5.4-bin\apache-maven-3.5.4\```

NOTE: 
1. You can use Maven directly
2. Before running the applications, all logs are created under the /usr/local/rabobank/logs
3. application.properties has the db details and port number

and for any permission related item, Please use 
chmod command to give the permission in linux or mac machines in the terminal.


### Building
To build the application from source clone or unzip the application and from navigate to the root directory.
```
mvn clean install -DskipTests or compile.bat
```

### Testing
To run all tests, use:
```
mvn clean install or compile.bat
```



### Running
To run statementprocessor from the command line (assuming you have been able to build from the source code successfully)
```
run.bat or java -jar  target\statementprocessor-0.0.1-SNAPSHOT.jar
```

### URL To Execute Application
Application:  http://localhost:8501/statementprocessor/swagger-ui.html

H2: http://localhost:8501/statementprocessor/h2-console/login.jsp

Use token controller to login with pre defined user and password

username: USER
password: USER

username: ADMIN
password: ADMIN

enter the token in the auth header 'x-auth-token' to proceed with api

## Building, Testing and Running statementprocessor from STS(Spring Tool Suite)
statementprocessor service' source comes with Maven configuration files which are recognized by [Eclipse](http://www.eclipse.org/) if the Eclipse Maven plugin (m2e) is installed.

At the command line, go to a directory **not** under your Eclipse workspace directory and check out the source:

```
git clone https://github.com/selvapuram/statementprocessor.git
```
As statementprocessor comes with some jars that need to be installed in Maven, run `./run` (or `run.bat` on Windows) to install these first. (This only needs to be done once on your system.)
Then in Eclipse, invoke the `Import...` command and select `Existing Maven Projects`. 

![Import a Maven project](images/Eclipse/eclipse-1.png)

Choose the root directory of your clone of the repository.

![Select maven projects to import](images/Eclipse/eclipse-2.png)

To run and debug statementprocessor from STS(Spring Tool Suite), spring boot dashboard has everything to run and debug.



### Testing in Eclipse

You can run the server tests directly from Eclipse. right click on the source folder ![](src/test/java), select `Run As` -> `Junit Test`. This should open a new tab with the junit launcher running the statementprocessor service tests.

### Test coverage in Eclipse

It is possible to analyze test coverage in Eclipse with the `EclEmma Java Code Coverage` plugin. It will add a `Coverage as…` menu similar to the `Run as…` and `Debug as…` menus which will then display the covered and missed lines in the source editor.
