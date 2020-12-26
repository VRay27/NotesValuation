FROM tomcat:8.0-alpine

LABEL maintainer=”rayvikas.27@gmail.com”

ADD notes.war /usr/local/tomcat/webapps/

EXPOSE 8080

CMD [“catalina.sh”, “run”]
