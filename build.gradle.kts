import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.1.1"
    id("io.spring.dependency-management") version "1.1.0"
    kotlin("jvm") version "1.8.22"
    kotlin("plugin.spring") version "1.8.22"
    kotlin("plugin.jpa") version "1.7.22"
    idea
}

group = "com.hjm"
version = "0.0.1-SNAPSHOT"

val kotestVersion = "5.6.2"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    testImplementation("org.springframework.boot:spring-boot-starter-test")

    // log
    implementation("org.springframework.boot:spring-boot-starter-log4j2")

    // db
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    implementation("com.mysql:mysql-connector-j")

    // aop
    implementation("org.springframework.boot:spring-boot-starter-aop")

    // json
    implementation("org.json:json:20230618")

    // kotest
    testImplementation("io.kotest:kotest-runner-junit5-jvm:$kotestVersion")
    testImplementation("io.kotest:kotest-assertions-core:$kotestVersion")
    testImplementation ("io.kotest:kotest-property:$kotestVersion")
    testImplementation("com.ninja-squad:springmockk:3.0.1")

    // QueryDSL
    implementation("com.querydsl:querydsl-jpa:5.0.0:jakarta")

    implementation("org.modelmapper:modelmapper:2.4.4")
}

configurations.forEach {
    it.exclude(group = "org.springframework.boot", module = "spring-boot-starter-logging")
    it.exclude(group = "org.apache.logging.log4j", module = "log4j-to-slf4j")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "17"
    }
}

tasks.withType<Test>().configureEach {
    // useJUnitPlatform()
    if (javaVersion.isCompatibleWith(JavaVersion.VERSION_17)) {
        // See https://kotest.io/docs/next/extensions/system_extensions.html#system-environment.
        jvmArgs("--add-opens=java.base/java.util=ALL-UNNAMED")
    }
}

// QClass를 Intellij가 사용할 수 있도록 경로에 추가합니다
// ./gradlew clean compileKotlin - QClass를 생성 명령어
idea {
    module {
        val kaptMain = file("build/generated/source/kapt/main")
        sourceDirs.add(kaptMain)
        generatedSourceDirs.add(kaptMain)
    }
}

// Spring Boot 2.5.0 이상부터 gradle build 시 .jar 파일이 2개 생기는 이슈
tasks.getByName<Jar>("jar") {
    enabled = false
}