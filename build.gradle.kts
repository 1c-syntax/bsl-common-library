import java.util.*

plugins {
    `java-library`
    `maven-publish`
    jacoco
    id("org.sonarqube") version "3.4.0.2513"
    id("org.cadixdev.licenser") version "0.6.1"
    id("com.github.gradle-git-version-calculator") version "1.1.0"
    id("io.freefair.lombok") version "6.5.0.3"
    id("io.freefair.javadoc-links") version "6.5.0.3"
    id("io.freefair.javadoc-utf-8") version "6.5.0.3"
}

repositories {
    mavenLocal()
    mavenCentral()
    maven(url = "https://jitpack.io")
}

group = "io.github.1c-syntax"
version = gitVersionCalculator.calculateVersion("v")

dependencies {

    implementation("org.apache.commons", "commons-collections4", "4.4")
    implementation("com.github.1c-syntax", "utils", "0.4.0")

    // логирование
    implementation("org.slf4j", "slf4j-api", "1.7.30")

    // stat analysis
    compileOnly("com.google.code.findbugs", "jsr305", "3.0.2")

    // тестирование
    testImplementation("org.junit.jupiter", "junit-jupiter-api", "5.6.1")
    testRuntimeOnly("org.junit.jupiter", "junit-jupiter-engine", "5.6.1")
    testImplementation("org.assertj", "assertj-core", "3.18.1")
    testImplementation("com.ginsberg", "junit5-system-exit", "1.0.0")

    // логирование
    // https://mvnrepository.com/artifact/org.slf4j/slf4j-log4j12
    testImplementation("org.slf4j", "slf4j-log4j12", "1.7.30")
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
        events("passed", "skipped", "failed", "standard_error")
    }

    reports {
        html.required.set(true)
    }
}

tasks.check {
    dependsOn(tasks.jacocoTestReport)
}

tasks.jacocoTestReport {
    reports {
        xml.required.set(true)
        xml.outputLocation.set(File("$buildDir/reports/jacoco/test/jacoco.xml"))
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
    header(rootProject.file("license/HEADER.txt"))
    newLine(false)
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
    dependsOn(":updateLicenses")
}

tasks.withType<Javadoc> {
    (options as StandardJavadocDocletOptions)
            .addStringOption("Xdoclint:none", "-quiet")
}