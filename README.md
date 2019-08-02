# recruitmentservice
The repository is used to maintain the code base for heaven hr recruitment coding challenge

## Building, Testing and Running recruitmentservice

You will need:
* [recruitmentservice source code](https://github.com/selvapuram/recruitmentservice)
* [Java JDK](http://java.sun.com/javase/downloads/index.jsp)
* Apache Maven
    * [https://maven.apache.org](http://maven.apache.org)
    * [Maven on Windows](https://maven.apache.org/guides/getting-started/windows-prerequisites.html)
* A Unix/Linux shell environment OR the Windows command line

From the top level directory in the recruiment application you can build, test and run recruitment service using the <tt>./run</tt> shell script , or using the <tt>refine.bat</tt> script from the Windows command line.

If you are working from the Windows command line you must also install a Java JDK, and [set the JAVA_HOME environment variable](http:confluence.atlassian.com/display/DOC/Setting+the+JAVA\_HOME+Variable+in+Windows) (please ensure it points to the JDK, and not the JRE) and the MVN\_HOME environment variable. You may need to reboot your machine after setting these environment variables. 

Ensure that you set your MAVEN_HOME environment variable, for example:

```MAVEN_HOME=E:\Downloads\apache-maven-3.5.4-bin\apache-maven-3.5.4\```

NOTE: 
1.You can use Maven directly
2.Before running the applications, all logs are created under the /usr/local/heavenhr/logs
3.application.properties has the db details and port number

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
To run recruitment from the command line (assuming you have been able to build from the source code successfully)
```
run.bat or java -jar  target\recruitment-0.0.1-SNAPSHOT.jar
```

### URL To Execute Application
Application:  http://localhost:8501/recruitment/swagger-ui.html

H2: http://localhost:8501/recruitment/h2-console/login.jsp

Use token controller to login with pre defined user and password

username: USER
password: USER

username: ADMIN
password: ADMIN

enter the token in the auth header 'x-auth-token' to proceed with api

## Building, Testing and Running recruitment from STS(Spring Tool Suite)
recruiment service' source comes with Maven configuration files which are recognized by [Eclipse](http://www.eclipse.org/) if the Eclipse Maven plugin (m2e) is installed.

At the command line, go to a directory **not** under your Eclipse workspace directory and check out the source:

```
git clone https://github.com/selvapuram/recruitmentservice.git
```
As recruitmentservice comes with some jars that need to be installed in Maven, run `./run` (or `run.bat` on Windows) to install these first. (This only needs to be done once on your system.)
Then in Eclipse, invoke the `Import...` command and select `Existing Maven Projects`. 

![Import a Maven project](images/Eclipse/eclipse-1.png)

Choose the root directory of your clone of the repository.

![Select maven projects to import](images/Eclipse/eclipse-2.png)

To run and debug recruiment from STS(Spring Tool Suite), spring boot dashboard has everything to run and debug.



### Testing in Eclipse

You can run the server tests directly from Eclipse. right click on the source folder ![](src/test/java), select `Run As` -> `Junit Test`. This should open a new tab with the junit launcher running the recruitment service tests.

### Test coverage in Eclipse

It is possible to analyze test coverage in Eclipse with the `EclEmma Java Code Coverage` plugin. It will add a `Coverage as…` menu similar to the `Run as…` and `Debug as…` menus which will then display the covered and missed lines in the source editor.
