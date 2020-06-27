import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
    application
    kotlin("jvm") version "1.3.72"
}

version = "1.0.2"
group = "org.sample"

application {
    mainClass.set("org.sample.MainKt")
}

repositories {
    mavenCentral()
    jcenter()
}

tasks.withType<Test> {
    useJUnitPlatform()
    testLogging {
        lifecycle {
            events = mutableSetOf(TestLogEvent.FAILED, TestLogEvent.PASSED, TestLogEvent.SKIPPED)
            exceptionFormat = TestExceptionFormat.FULL
            showExceptions = true
            showCauses = true
            showStackTraces = true
            showStandardStreams = true
        }
        info.events = lifecycle.events
        info.exceptionFormat = lifecycle.exceptionFormat
    }

    val failedTests = mutableListOf<TestDescriptor>()
    val skippedTests = mutableListOf<TestDescriptor>()
    addTestListener(object : TestListener {
        override fun beforeSuite(suite: TestDescriptor) {}
        override fun beforeTest(testDescriptor: TestDescriptor) {}
        override fun afterTest(testDescriptor: TestDescriptor, result: TestResult) {
            when (result.resultType) {
                TestResult.ResultType.FAILURE -> failedTests.add(testDescriptor)
                TestResult.ResultType.SKIPPED -> skippedTests.add(testDescriptor)
                else -> Unit
            }
        }

        override fun afterSuite(suite: TestDescriptor, result: TestResult) {
            if (suite.parent == null) { // root suite
                logger.lifecycle("----")
                logger.lifecycle("Test result: ${result.resultType}")
                logger.lifecycle(
                        "Test summary: ${result.testCount} tests, " +
                                "${result.successfulTestCount} succeeded, " +
                                "${result.failedTestCount} failed, " +
                                "${result.skippedTestCount} skipped")
                failedTests.takeIf { it.isNotEmpty() }?.prefixedSummary("\tFailed Tests")
                skippedTests.takeIf { it.isNotEmpty() }?.prefixedSummary("\tSkipped Tests:")
            }
        }

        private infix fun List<TestDescriptor>.prefixedSummary(subject: String) {
            logger.lifecycle(subject)
            forEach { test -> logger.lifecycle("\t\t${test.displayName()}") }
        }

        private fun TestDescriptor.displayName() = parent?.let { "${it.name} - $name" } ?: "$name"
    })
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("com.sparkjava:spark-core:2.5.4")
    implementation("org.slf4j:slf4j-simple:1.7.30")

    testImplementation("com.squareup.okhttp:okhttp:2.5.0")
    testImplementation("io.kotest:kotest-runner-junit5-jvm:4.0.5")
    testImplementation("io.kotest:kotest-assertions-core-jvm:4.0.5") // for kotest core jvm assertions
    testImplementation("io.kotest:kotest-property-jvm:4.0.5")
}


sourceSets {
    create("integTest") {
        kotlin {
            compileClasspath += main.get().output + configurations.testRuntimeClasspath
            runtimeClasspath += output + compileClasspath
        }
    }
}

val integTest = task<Test>("integTest") {
    description = "Runs the integTest tests"
    group = "verification"
    testClassesDirs = sourceSets["integTest"].output.classesDirs
    classpath = sourceSets["integTest"].runtimeClasspath
    mustRunAfter(tasks["test"])
}

tasks.check {
    dependsOn(integTest)
}

sourceSets {
    create("journeyTest") {
        kotlin {
            compileClasspath += main.get().output + configurations.testRuntimeClasspath
            runtimeClasspath += output + compileClasspath
        }
    }
}

val journeyTest = task<Test>("journeyTest") {
    description = "Runs the JourneyTest tests"
    group = "verification"
    testClassesDirs = sourceSets["journeyTest"].output.classesDirs
    classpath = sourceSets["journeyTest"].runtimeClasspath
    mustRunAfter(tasks["integTest"])
}

tasks.check {
    dependsOn(journeyTest)
}
