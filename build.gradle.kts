plugins {
    kotlin("jvm") version "1.4.32"
    id("maven-publish")
    id("org.jetbrains.kotlinx.binary-compatibility-validator") version "0.5.0"
}

group = "me.patrykanuszczyk"
version = "0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

tasks.withType<Test> {
    systemProperty("file.encoding", "UTF-8")

    useJUnitPlatform()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib")

    testImplementation("org.junit.jupiter:junit-jupiter:5.4.2")
}

publishing {
    publications {
        //noinspection GroovyAssignabilityCheck
        create<MavenPublication>("maven") {
            //noinspection GroovyAssignabilityCheck
            from(components["java"])
        }
    }
}