plugins {
    `maven-publish`
    signing
    jacoco
}

val codacyCoverageReport: Configuration by configurations.creating

val immutablesVersion = "2.7.4"

dependencies {
    testAnnotationProcessor(group = "org.immutables", name = "value", version = immutablesVersion)
    testImplementation(group = "org.immutables", name = "value", version = immutablesVersion, classifier = "annotations")

    codacyCoverageReport(group = "com.codacy", name = "codacy-coverage-reporter", version = "6.0.2")
}

val sourcesJar by tasks.creating(Jar::class) {
    dependsOn(tasks.classes)
    archiveClassifier.set("sources")
    from(sourceSets.main.get().allSource)
}

val javadocJar by tasks.creating(Jar::class) {
    dependsOn(tasks.javadoc)
    archiveClassifier.set("javadoc")
    from(tasks.javadoc.get().destinationDir)
}

tasks {
    jacocoTestReport {
        dependsOn(test)
        reports {
            xml.isEnabled = true
        }
    }
}

task<JavaExec>("codacyCoverageReport") {
    dependsOn(tasks.jacocoTestReport)
    main = "com.codacy.CodacyCoverageReporter"
    classpath = codacyCoverageReport
    args(
            "report",
            "-l",
            "Java",
            "-r",
            "$buildDir/reports/jacoco/test/jacocoTestReport.xml"
    )
}

jacoco {
    toolVersion = "0.8.4"
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])

            artifact(sourcesJar)
            artifact(javadocJar)

            pom {
                name.set("Equalizer")
                description.set("Fluent builders for correct equals() and hashCode() implementations")
                url.set("https://github.com/Double-O-Seven/equalizer")
                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }
                developers {
                    developer {
                        id.set("Double-O-Seven")
                        name.set("Adrian-Philipp Leuenberger")
                        email.set("thewishwithin@gmail.com")
                    }
                }
                scm {
                    connection.set("scm:git:git://github.com/Double-O-Seven/equalizer.git")
                    developerConnection.set("scm:git:ssh://github.com/Double-O-Seven/equalizer.git")
                    url.set("https://github.com/Double-O-Seven/equalizer")
                }
            }
        }
    }
    repositories {
        maven {
            val snapshotsRepoUrl = uri("https://oss.sonatype.org/content/repositories/snapshots/")
            val releasesRepoUrl = uri("https://oss.sonatype.org/service/local/staging/deploy/maven2/")
            url = when {
                version.toString().endsWith("-SNAPSHOT") -> snapshotsRepoUrl
                else -> releasesRepoUrl
            }
            credentials {
                val ossrhUsername: String? by extra
                val ossrhPassword: String? by extra
                username = ossrhUsername
                password = ossrhPassword
            }
        }
    }
}

signing {
    sign(publishing.publications["mavenJava"])
}