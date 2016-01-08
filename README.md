SpringWebServicesForFun
=======================

SOAP

Messing around with Spring Web Services

mvn clean install
mvn clean install && mvn dependency:sources && mvn dependency:resolve -Dclassifier=javadoc
mvn war:war
mvn jetty:run
mvn clean install -Dmaven.test.skip=true



For debugging: export MAVEN_OPTS="-Xdebug -Xrunjdwp:transport=dt_socket,address=8000,server=y,suspend=y"


REST

Release:
mvn clean install

Documentation:
mvn clean install -Dmaven.test.skip=true -Pdocumentation

Javadoc and Sources:
mvn dependency:sources && mvn dependency:resolve -Dclassifier=javadoc
