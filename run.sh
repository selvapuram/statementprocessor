#!/bin/bash
source java -jar  target\statementprocessor-0.0.1-SNAPSHOT.jar
open "http://localhost:8501/statementprocessor/swagger-ui.html#"
open "http://localhost:8501/statementprocessor/h2-console/login.jsp"
