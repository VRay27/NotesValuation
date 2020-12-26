FROM tomcat:8.0

LABEL maintainer=”VIKAS RAY”

ADD target/notes.war /usr/local/tomcat/webapps/notes.war

EXPOSE 8081

CMD [“catalina.sh”, “run”]
