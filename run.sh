#!/bin/bash
source java -jar  target\recruitment-0.0.1-SNAPSHOT.jar
open "http://localhost:8501/recruitment/swagger-ui.html#"
open "http://localhost:8501/recruitment/h2-console/login.jsp"
