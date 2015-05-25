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
TODO: test catalog.cat support for Apache CXF (searching in classpath) See: http://labs.bsb.com/2011/01/usage-of-an-xml-catalog-in-the-xmlbeans-cxf-integration/
and http://cxf.apache.org/cxf-xjc-plugin.html
TODO: integration tests (client and server)

For debugging: export MAVEN_OPTS="-Xdebug -Xrunjdwp:transport=dt_socket,address=8000,server=y,suspend=y"
