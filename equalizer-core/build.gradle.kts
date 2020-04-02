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

plugins {
    `java-library`
    `maven-publish`
    jacoco
}

val codacyCoverageReport: Configuration by configurations.creating

val immutablesVersion = "2.8.3"

dependencies {
    testAnnotationProcessor(group = "org.immutables", name = "value", version = immutablesVersion)
    testImplementation(group = "org.immutables", name = "value", version = immutablesVersion, classifier = "annotations")

    codacyCoverageReport(group = "com.codacy", name = "codacy-coverage-reporter", version = "7.1.0")
}

jacoco {
    toolVersion = "0.8.4"
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