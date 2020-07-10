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
    `java-library` apply false
    jacoco
}

val codacyCoverageReport: Configuration by configurations.creating

dependencies {
    codacyCoverageReport(group = "com.codacy", name = "codacy-coverage-reporter", version = "7.1.0")
}

tasks {
    jacocoTestReport {
        val projectsWithTests = subprojects.filter { it.pluginManager.hasPlugin("jacoco") }
        projectsWithTests.forEach { dependsOn(it.tasks.test) }
        executionData.setFrom(projectsWithTests.map { file("${it.buildDir}/jacoco/test.exec") })
        additionalSourceDirs.setFrom(projectsWithTests.map { it.sourceSets.main.get().allSource.sourceDirectories })
        sourceDirectories.setFrom(projectsWithTests.map { it.sourceSets.main.get().allSource.sourceDirectories })
        classDirectories.setFrom(projectsWithTests.map { it.sourceSets.main.get().output })
        reports {
            xml.isEnabled = true
        }
    }

    register<JavaExec>("codacyCoverageReport") {
        dependsOn(tasks.jacocoTestReport)
        main = "com.codacy.CodacyCoverageReporter"
        classpath = codacyCoverageReport
        args(
                "report",
                "-l",
                "Java",
                "-r",
                "${tasks.jacocoTestReport.get().reports.xml.destination}"
        )
    }
}

subprojects {
    pluginManager.withPlugin("jacoco") {
        tasks {
            jacocoTestReport {
                dependsOn(test)
                reports {
                    xml.isEnabled = true
                }
            }
        }
    }
}