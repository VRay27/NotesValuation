FROM tomcat:8.0

LABEL maintainer=”rayvikas.27@gmail.com”

ADD target/notes.war /usr/local/tomcat/webapps/notes.war

EXPOSE 8080

CMD [cd CAATALIN_HOME/bin]
CMD [+x startup.sh]
CMD [./startup.sh]


CMD [“catalina.sh”, “run”]
