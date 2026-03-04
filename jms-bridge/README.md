# Apache Camel :: JMS Bridge

## Build

To build the routes and the runtimes, just do:

```
mvn clean install
```

NB: in order to create a uber-jar (executable), the `application.properties` contains:

```
quarkus.package.jar.type=uber-jar
```

The jar is generated in the `target` folder, e.g. `target/jms-bridge-0.1-SNAPSHOT-runner.jar`.

## Run

To run the Camel Routes simply with:

```
java -jar target/*-runner.jar
```

## Distribute

You can simply copy the `jms-bridge-0.1-SNAPSHOT-runner.jar` in any machine. It's uber-jar, packaging everything ready to run.

## Camel Context & Routes

## Scale & HA

You can run multiple instances of the app:

```
java -jar jms-bridge-0.1-SNAPSHOT-runner.jar &
java -jar jms-bridge-0.1-SNAPSHOT-runner.jar &
java -jar jms-bridge-0.1-SNAPSHOT-runner.jar &
```

NB: you should copy the jar in different folder to avoid "conflict":

```
mkdir instance1
cp jms-bridge-0.1-SNAPSHOT-runner.jar instance1
cd instance1
java -jar jms-bridge-0.1-SNAPSHOT-runner.jar &
mkdir instance2
cp jms-bridge-0.1-SNAPSHOT-runner.jar instance2
cd instance2
java -jar jms-bridge-0.1-SNAPSHOT-runner.jar &
```

You can also increase the number of threads in the route by using `maxConcurrentConsumers` on the JMS consumer endpoint:

```
from("jms:queue:input?connectionFactory=#ibmMQConnectionFactory&concurrentConsumers=10&acknowledgementModeName=CLIENT_ACKNOWLEDGE")
```

If you have multiple instances of the same Camel route (see above), and you want to implement "HA" (like only one route is actually consuming, the other instances are in standby mode), you can use ActiveMQ `exclusiveConsumer` feature if you consumer from ActiveMQ.

For that, you have to configure the ActiveMQ Connection Factory:

```
factory.setExclusiveConsumer(true);
```

If you are not consuming from ActiveMQ, you can use Camel idempotent consumer EIP.
