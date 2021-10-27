import java.net.URI
import java.util.*

plugins {
    `java-library`
    `maven-publish`
    jacoco
    id("net.kyori.indra.license-header") version "1.3.1"
    id("com.github.gradle-git-version-calculator") version "1.1.0"
    id("io.freefair.lombok") version "6.2.0"
    id("io.freefair.javadoc-links") version "6.2.0"
    id("io.freefair.javadoc-utf-8") version "6.2.0"
    id("org.sonarqube") version "3.3"
}

group = "com.github.1c-syntax"
version = gitVersionCalculator.calculateVersion("v")

repositories {
    mavenCentral()
    maven { url = URI("https://jitpack.io") }
}

repositories {
    mavenCentral()
}

val junitVersion = "5.6.1"
dependencies {

    implementation("org.apache.commons", "commons-collections4", "4.4")

    // логирование
    implementation("org.slf4j", "slf4j-api", "1.7.30")

    // тестирование
    testImplementation("org.junit.jupiter", "junit-jupiter-api", junitVersion)
    testRuntimeOnly("org.junit.jupiter", "junit-jupiter-engine", junitVersion)
    testImplementation("org.assertj", "assertj-core", "3.18.1")
    testImplementation("com.ginsberg", "junit5-system-exit", "1.0.0")

    // логирование
    // https://mvnrepository.com/artifact/org.slf4j/slf4j-log4j12
    testImplementation("org.slf4j", "slf4j-log4j12", "1.7.30")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_11
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
    withSourcesJar()
    withJavadocJar()
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
    reports {
        html.isEnabled = true
    }
}

tasks.check {
    dependsOn(tasks.jacocoTestReport)
}

tasks.jacocoTestReport {
    reports {
        xml.isEnabled = true
        xml.destination = File("$buildDir/reports/jacoco/test/jacoco.xml")
    }
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
    options.compilerArgs.add("-Xlint:unchecked")
    options.compilerArgs.add("-parameters")
}

sonarqube {
    properties {
        property("sonar.sourceEncoding", "UTF-8")
        property("sonar.host.url", "https://sonarcloud.io")
        property("sonar.organization", "1c-syntax")
        property("sonar.projectKey", "1c-syntax_bsl-common-library")
        property("sonar.projectName", "BSL Common library")
        property("sonar.coverage.jacoco.xmlReportPaths", "$buildDir/reports/jacoco/test/jacoco.xml")
    }
}

artifacts {
    archives(tasks["jar"])
    archives(tasks["sourcesJar"])
    archives(tasks["javadocJar"])
}

license {
    header = rootProject.file("license/HEADER.txt")
    ext["year"] = "2021 - " + Calendar.getInstance().get(Calendar.YEAR)
    ext["name"] = "Tymko Oleg <olegtymko@yandex.ru>, Maximov Valery <maximovvalery@gmail.com>"
    ext["project"] = "BSL Common library"
    exclude("**/*.properties")
    exclude("**/*.orig")
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
            pom {
                description.set("Common library for 1c-syntax projects")
                url.set("https://github.com/1c-syntax/bsl-common-library")
                licenses {
                    license {
                        name.set("GNU LGPL 3")
                        url.set("https://www.gnu.org/licenses/lgpl-3.0.txt")
                        distribution.set("repo")
                    }
                }
                developers {
                    developer {
                        id.set("otymko")
                        name.set("Oleg Tymko")
                        email.set("olegtymko@yandex.ru")
                        url.set("https://github.com/otymko")
                        organization.set("1c-syntax")
                        organizationUrl.set("https://github.com/1c-syntax")
                    }
                    developer {
                        id.set("theshadowco")
                        name.set("Valery Maximov")
                        email.set("maximovvalery@gmail.com")
                        url.set("https://github.com/theshadowco")
                        organization.set("1c-syntax")
                        organizationUrl.set("https://github.com/1c-syntax")
                    }
                }
                scm {
                    connection.set("scm:git:git://github.com/1c-syntax/bsl-common-library.git")
                    developerConnection.set("scm:git:git@github.com:1c-syntax/bsl-common-library.git")
                    url.set("https://github.com/1c-syntax/bsl-common-library")
                }
            }
        }
    }
}

tasks.register("precommit") {
    description = "Run all precommit tasks"
    group = "Developer tools"
    dependsOn(":test")
    dependsOn(":licenseFormat")
}
