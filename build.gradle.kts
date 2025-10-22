import org.jreleaser.model.Active.*
import java.util.*

plugins {
    `java-library`
    `maven-publish`
    jacoco
    id("org.cadixdev.licenser") version "0.6.1"
    id("me.qoomon.git-versioning") version "6.4.4"
    id("io.freefair.lombok") version "9.0.0"
    id("io.freefair.javadoc-links") version "9.0.0"
    id("io.freefair.javadoc-utf-8") version "9.0.0"
    id("io.freefair.maven-central.validate-poms") version "9.0.0"
    id("com.github.ben-manes.versions") version "0.53.0"
    id("ru.vyarus.pom") version "3.0.0"
    id("org.jreleaser") version "1.20.0"
    id("org.sonarqube") version "7.0.0.6105"
}

repositories {
    mavenLocal()
    mavenCentral()
}

group = "io.github.1c-syntax"
gitVersioning.apply {
    refs {
        describeTagFirstParent = false
        tag("v(?<tagVersion>[0-9].*)") {
            version = "\${ref.tagVersion}\${dirty}"
        }

        branch("develop") {
            version = "\${describe.tag.version.major}." +
                    "\${describe.tag.version.minor.next}.0." +
                    "\${describe.distance}-SNAPSHOT\${dirty}"
        }

        branch(".+") {
            version = "\${ref}-\${commit.short}\${dirty}"
        }
    }

    rev {
        version = "\${commit.short}\${dirty}"
    }
}

dependencies {

    implementation("org.apache.commons", "commons-collections4", "4.4")
    implementation("io.github.1c-syntax", "utils", "0.6.3")

    // логирование
    implementation("org.slf4j", "slf4j-api", "2.0.16")

    // stat analysis
    compileOnly("com.github.spotbugs", "spotbugs-annotations", "4.8.6")

    // логирование
    testImplementation("org.slf4j", "slf4j-reload4j", "2.0.16")

    // тестирование
    testImplementation(platform("org.junit:junit-bom:6.0.0"))
    testImplementation("org.junit.jupiter", "junit-jupiter-api")
    testImplementation("org.junit.jupiter", "junit-jupiter-params")
    testImplementation("org.assertj", "assertj-core", "3.27.0")
    testImplementation("com.ginsberg", "junit5-system-exit", "2.0.2")
    testRuntimeOnly("org.junit.jupiter", "junit-jupiter-engine")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
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
        xml.outputLocation.set(File("${layout.buildDirectory.get()}/reports/jacoco/test/jacoco.xml"))
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
        property(
            "sonar.coverage.jacoco.xmlReportPaths",
            "${layout.buildDirectory.get()}/reports/jacoco/test/jacoco.xml"
        )
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
    repositories {
        maven {
            name = "staging"
            url = layout.buildDirectory.dir("staging-deploy").get().asFile.toURI()
        }
    }

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
                // Добавлено для Maven Central validation
                issueManagement {
                    system.set("GitHub Issues")
                    url.set("https://github.com/1c-syntax/bsl-common-library/issues")
                }
                // Добавлено для Maven Central validation
                ciManagement {
                    system.set("GitHub Actions")
                    url.set("https://github.com/1c-syntax/bsl-common-library/actions")
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

jreleaser {
    signing {
        active = ALWAYS
        armored = true
    }
    deploy {
        maven {
            mavenCentral {
                create("release-deploy") {
                    active = RELEASE
                    url = "https://central.sonatype.com/api/v1/publisher"
                    stagingRepository("build/staging-deploy")
                }
            }
            nexus2 {
                create("snapshot-deploy") {
                    active = SNAPSHOT
                    snapshotUrl = "https://central.sonatype.com/repository/maven-snapshots/"
                    applyMavenCentralRules = true
                    snapshotSupported = true
                    closeRepository = true
                    releaseRepository = true
                    stagingRepository("build/staging-deploy")
                }
            }
        }
    }
}
