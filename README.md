SpringWebServicesForFun
=======================

Messing around with Spring Web Services

mvn clean install
mvn clean install && mvn dependency:sources && mvn dependency:resolve -Dclassifier=javadoc
mvn war:war
mvn jetty:run
mvn clean install -Dmaven.test.skip=true


TODO: custom bindings when creating Java code from wsdl.
TODO: using Jetty instead of Tomcat
TODO: logging Spring information
TODO: integration tests (client and server)

For debugging: export MAVEN_OPTS="-Xdebug -Xrunjdwp:transport=dt_socket,address=8000,server=y,suspend=y"
