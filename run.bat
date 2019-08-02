call start cmd /c "call java -jar  target\recruitment-0.0.1-SNAPSHOT.jar"
timeout 30
call start cmd /c "start chrome "http://localhost:8501/recruitment/swagger-ui.html#" "http://localhost:8501/recruitment/h2-console/login.jsp""
