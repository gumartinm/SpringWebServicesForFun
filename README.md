SpringWebServicesForFun
=======================

Messing around with Spring Web Services

mvn clean install
mvn clean install && mvn dependency:sources && mvn dependency:resolve -Dclassifier=javadoc
mvn war:war
mvn jetty:run
mvn clean install -Dmaven.test.skip=true


TODO: maven-jaxb2-plugin in order to reuse generated Java code from other projects (cxf-xjc-plugin doesn't implement such feature
without generating episodes by myself)
TODO: using Jetty instead of Tomcat
TODO: logging Spring information

For debugging: export MAVEN_OPTS="-Xdebug -Xrunjdwp:transport=dt_socket,address=8000,server=y,suspend=y"
