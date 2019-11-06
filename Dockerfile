# Pull base image
FROM tomcat:8.5.43-jdk11-openjdk


RUN rm -rf /usr/local/tomcat/webapps/*
# Copy to images tomcat path
COPY target/*.war /usr/local/tomcat/webapps/ROOT.war

CMD ["catalina.sh", "run"]
