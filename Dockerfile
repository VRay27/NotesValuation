FROM openjdk:8
ADD target/notes.war notes.war
ENTRYPOINT ["java","-war","/notes.war"]
