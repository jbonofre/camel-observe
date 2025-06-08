# Apache Camel :: Observe

This repository is a simple example to illustrate:
1. How to create simple Camel routes
2. How to get JMX attributes for these routes (route, camel context, processors, ...)
3. How to run these routes in Apache Karaf or camel-spring-boot, and how to connect via JMX

## Build

To build the routes and the runtimes, just do:

```
mvn clean install
```

## Run

### Spring Boot

To run spring-boot route runtime:

```
java -jar runtimes/spring-boot/target/spring-boot-xxx.jar
```

## JMX

NB: spring-boot runtime only expose JMX locally. To expose remotely, you have to add the "regular" system properties:

```
-Dcom.sun.management.jmxremote.port=9999 \
-Dcom.sun.management.jmxremote.authenticate=false \
-Dcom.sun.management.jmxremote.ssl=false \
```

### Interesting attributes

#### Camel Contexts (`org.apache.camel:type=context`)

* `StartTimestamp`
* `ResetTimestamp`
* `StartedRoutes`
* `State`
* `Uptime`

### Camel Routes (`org.apache.camel:type=routes`)

* `EndpointUri`
* `ExchangesCompleted`
* `ExchangesFailed` (should be low)
* `ExchangesInflight`
* `FailuresHandled`
* `FirstExchangeCompletedTimestamp`
* `FirstExchangeFailureTimestamp`
* `LastExchangeCompletedTimestamp`
* `LastExchangeFailureTimestemp`
* `LastProcessingTime`
* `ManagementName`
* `MaxProcessingTime`
* `MeanProcessingTime`
* `OldestInflightDuration`
* `Redeliveries`
* `RouteGroup`
* `StartTimestamp`
* `State`
* `Uptime`

### Camel Thread Pools (`org.apache.camel:type=threadpools`)

* `ActiveCount`
* `CorePoolSize`
* `KeepAliveTime`
* `LargestPoolSize`
* `PoolSize`
* `TaskCount`
* `TaskQueueSize`

### Endpoints+Producers/Consumers/Processors (`org.apache.camel:type=[endpoints|producers|consumers|processors]`)

It's possible to get details about each step in a route with these ObjectNames, including attributes:

* `ExchangesCompleted`
* `ExchangesFailed` (should be low)
* `ExchangesInflight`
* `Index`
* `FailuresHandled`
* `FirstExchangeCompletedTimestamp`
* `FirstExchangeFailureTimestamp`
* `LastExchangeCompletedTimestamp`
* `LastExchangeFailureTimestemp`
* `LastProcessingTime`
* `ManagementName`
* `MaxProcessingTime`
* `MeanProcessingTime`
* `OldestInflightDuration`
* `Redeliveries`
* `RouteGroup`
* `StartTimestamp`
* `State`