FROM tomcat:8.0-alpine

LABEL maintainer=”rayvikas.27@gmail.com”

ADD target/notes.war /usr/local/tomcat/webapps/notes.war

EXPOSE 8080

CMD [“catalina.sh”, “run”]
