# kotlin-application-multiple-test-config
This is a code is to illustrate the kotlin code configured to have multiple test folder and gradle task to run each task.
1. Test framework used it io.kotest.
2. Three test folder; test, integTest, journeyTest.
3. Gradle configured using Kotlin DSL.
4. formatKotlin, lintKotlin, spotlessApply, spotlessCheck task is added to gradle task.
## How to get it running.

### Prerequisite 
```
Needs to have docker installed
```

### How to run unit Test using gradle

```
./gradlew test       
```

### How to run integration Test using gradle

```
./gradlew integTest       
```

### How to run journey Test using gradle

```
./gradlew journeyTest       
```


