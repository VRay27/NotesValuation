FROM tomcat:8.0

LABEL maintainer=”vikas”

ADD target/notes.war /usr/local/tomcat/webapps/notes.war

EXPOSE 8081


