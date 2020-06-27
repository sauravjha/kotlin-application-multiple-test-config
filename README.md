# kotlin-application-multiple-test-config
This is a code is to illustrate the kotlin code that uses spark and uses Gradle to build the code

## How to get it running.

### Prerequisite 
```
Needs to have docker installed
```

### Step 1.
```
 ./batect run

```
### Step 2 (It might take a while for the first time)

```
Once you see the logs as given bellow
> Task :run
[Thread-0] INFO org.eclipse.jetty.util.log - Logging initialized @843ms
[Thread-0] INFO spark.embeddedserver.jetty.EmbeddedJettyServer - == Spark has ignited ...
[Thread-0] INFO spark.embeddedserver.jetty.EmbeddedJettyServer - >> Listening on 0.0.0.0:4567
[Thread-0] INFO org.eclipse.jetty.server.Server - jetty-9.3.6.v20151106
[Thread-0] INFO org.eclipse.jetty.server.ServerConnector - Started ServerConnector@7322c076{HTTP/1.1,[http/1.1]}{0.0.0.0:4567}
[Thread-0] INFO org.eclipse.jetty.server.Server - Started @1299ms

```

### Step 3

```
http://localhost:4567/hello
```

### How to run unit Test using gradle

````
./gradlew test       
```

### How to run integration Test using gradle

````
./gradlew integTest       
```

### How to run journey Test using gradle

````
./gradlew journeyTest       
```


