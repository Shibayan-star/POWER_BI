plugins {
    id("java")
    id("jacoco")
    id("org.springframework.boot") version "3.2.0"
    id("io.spring.dependency-management") version "1.1.4"
    id("com.google.cloud.tools.jib") version "3.4.0"
    id("org.springdoc.openapi-gradle-plugin") version "1.8.0"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    maven {
        url = uri("https://pkgs.dev.azure.com/swdc/3bbacd58-97dc-41a6-9034-469c28e3148b/_packaging/daimler-swf-java/maven/v1")
        name = "daimler-swf-java"
        authentication {
            create<BasicAuthentication>("basic")
        }
        credentials {
            username = "swdc"
            password = project.properties["swdc_password"] as String
        }
    }
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-aop")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-web")

    implementation("org.jetbrains.kotlin:kotlin-stdlib") // Include Kotlin standard library

    implementation("org.projectlombok:lombok:1.18.22")
    implementation("com.sparkjava:spark-core:2.9.3")
    implementation("org.jsoup:jsoup:1.14.3")
    implementation("org.json:json:20210307")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")

    implementation("com.azure:azure-identity:1.4.0")
}

tasks.test {
    useJUnitPlatform()
}