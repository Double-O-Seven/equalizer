plugins {
    `maven-publish`
    signing
    kotlin("jvm")
    id("org.jetbrains.dokka") version "0.10.0"
}

dependencies {
    api(project(":equalizer-core"))

    implementation(group = "org.jetbrains.kotlin", name = "kotlin-stdlib-jdk8")
}

java {
    withSourcesJar()
}

val javadocJar by tasks.creating(Jar::class) {
    from(tasks.dokka)
    archiveClassifier.set("javadoc")
}

val mavenJava by publishing.publications.creating(MavenPublication::class) {
    from(components["java"])
    artifact(javadocJar)
    pom {
        name.set("Equalizer Kotlin DSL")
        description.set("Kotlin DSL for Equalizer")
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