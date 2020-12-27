FROM tomcat:8
COPY target/notes.war /usr/local/tomcat/webapps/notes.war
# Added to test webhook
