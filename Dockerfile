FROM tomcat 
WORKDIR webapps 
COPY target/notes.war .
RUN rm -rf ROOT && mv notes.war ROOT.war
ENTRYPOINT ["sh", "/usr/local/tomcat/bin/startup.sh"]
