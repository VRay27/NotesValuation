FROM openjdk:8
ADD target/notes.jar notes.jar
ENTRYPOINT ["java","-jar","/notes.jar"]
