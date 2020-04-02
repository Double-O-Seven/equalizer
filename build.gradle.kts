/*
 * Copyright (C) 2020 Adrian-Philipp Leuenberger
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import groovy.lang.Closure

plugins {
    kotlin("jvm") apply false
    `java-library`
    `maven-publish`
    signing
    id("com.palantir.git-version") version "0.12.2"
}

val junitVersion = "5.6.1"

val gitVersion: Closure<String> by extra

allprojects {
    group = "ch.leadrian.equalizer"

    version = gitVersion()

    repositories {
        jcenter()
    }
}

subprojects {

    pluginManager.withPlugin("java-library") {

        java {
            withSourcesJar()
            withJavadocJar()
        }

        dependencies {
            testImplementation(group = "org.junit.jupiter", name = "junit-jupiter-api", version = junitVersion)
            testImplementation(group = "org.junit.jupiter", name = "junit-jupiter-params", version = junitVersion)
            testImplementation(group = "org.assertj", name = "assertj-core", version = "3.15.0")

            testRuntimeOnly(group = "org.junit.jupiter", name = "junit-jupiter-engine", version = junitVersion)
        }

        tasks.test {
            useJUnitPlatform()
        }
    }

    pluginManager.withPlugin("maven-publish") {
        apply(plugin = "signing")

        val mavenJava by publishing.publications.creating(MavenPublication::class) {
            components.findByName("java")?.let { from(it) }
            components.findByName("javaPlatform")?.let { from(it) }
            pom {
                name.set("Equalizer (${this@subprojects.name})")
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

        publishing {
            repositories {
                maven {
                    val snapshotsRepoUrl = uri("https://oss.sonatype.org/content/repositories/snapshots/")
                    val releasesRepoUrl = uri("https://oss.sonatype.org/service/local/staging/deploy/maven2/")
                    url = when {
                        version.toString().endsWith("SNAPSHOT") -> snapshotsRepoUrl
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
            sign(mavenJava)
        }
    }
}
