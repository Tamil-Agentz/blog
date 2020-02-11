# About

This demoes a simple web-application containing a Message Driven Bean deployed with Wildfly. It uses the embedded Artemis message broker inside of Wildfly.


# To post a message to the queue:

	http://localhost:8080/mdb-activemq-rar-demo-spring/author.html


# Configuring Wildfly

## Running Wildfly

	WILDFLY_HOME/bin/standalone.sh -c standalone-with-external-activemq-rar-deployment.xml

## Adding users

- Go to WILDFLY_HOME/bin/add-user
- Create a *Management User*
- Create an *Application User* with name **user**, password **user123**. It should have a role **guest**

## Deploying ActiveMQ RAR as archive
Simply copy the *activemq-rar-5.15.4.rar* into the *standalone/deployments* directory

## Apache ActiveMQ
### Starting

	bin/activemq start

### Admin Console
	
	http://localhost:8161/admin/
	
User: admin

Password: admin	

## Using external ActiveMQ
- https://developer.jboss.org/wiki/HowToUseOutOfProcessActiveMQWithWildFly
- http://www.mastertheboss.com/jboss-frameworks/ironjacamar/configuring-a-resource-adapter-for-activemq-on-jbosswildfly
- http://www.mastertheboss.com/jboss-frameworks/ironjacamar/configuring-a-resource-adapter-for-jboss-as7-openmq
- https://github.com/wildfly/quickstart/compare/master...jmesnil:helloworld-mdb-activemq-ra
- https://github.com/wildfly/quickstart/tree/master/helloworld-mdb

### Problems
**java.lang.ClassNotFoundException: org.slf4j.impl.Log4jLoggerAdapter**
**Solution:**
Add the below dependency:

``` xml
		<dependency>
	        <groupId>org.slf4j</groupId>
	        <artifactId>slf4j-log4j12</artifactId>
	        <version>${slf4j.version}</version>
	    </dependency>
```

**java.lang.ClassNotFoundException: org.slf4j.impl.Slf4jLogger**
**Solution:**
Add the below dependency:

``` xml
		<dependency>
		    <groupId>org.jboss.slf4j</groupId>
		    <artifactId>slf4j-jboss-logmanager</artifactId>
		    <version>1.0.4.GA</version>
		</dependency>
```
 
**java.lang.NoClassDefFoundError: org/jboss/logmanager/Level**
**Solution:**

``` xml
		<dependency>
			<groupId>org.jboss.logmanager</groupId>
		    <artifactId>jboss-logmanager</artifactId>
			<version>2.1.4.Final</version>
		</dependency>
```

**java.lang.ClassCastException: Cannot cast org.jboss.logmanager.Logger to org.jboss.logmanager.Logger**
**Solution:**
In the below subsystem,

``` xml
<subsystem xmlns="urn:jboss:domain:logging:5.0">
```

Put the below lines

``` xml
            <add-logging-api-dependencies value="false"/>
            <use-deployment-logging-config value="true"/>
```

