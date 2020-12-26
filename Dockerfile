FROM tomcat:8.0

LABEL maintainer=”rayvikas.27@gmail.com”

ADD target/notes.war /usr/local/tomcat/webapps/notes.war

EXPOSE 8081

CMD [“catalina.sh”, “run”]
