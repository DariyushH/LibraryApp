

plugins {
    id ("java")
    id("org.springframework.boot") version "3.3.4"
    id("io.spring.dependency-management") version "1.1.6"
}

group = "org.example"
version = "1.0-SNAPSHOT"


repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.3.4")
    implementation("org.springframework.boot:spring-boot-starter-jdbc:3.3.4")
    implementation("org.springframework.boot:spring-boot-starter-web:3.3.4")
//    compileOnly("org.projectlombok:lombok")
    compileOnly ("org.projectlombok:lombok:1.18.20")
    annotationProcessor ("org.projectlombok:lombok:1.18.20")
    implementation("org.springframework.boot:spring-boot-starter-aop:3.3.4")

    implementation("jakarta.transaction:jakarta.transaction-api:2.0.1")
    runtimeOnly ("org.postgresql:postgresql")

    implementation ("org.hibernate:hibernate-jcache:6.2.9.Final")
    implementation ("org.ehcache:ehcache")
    implementation ("org.ehcache:ehcache-transactions")

    implementation("org.liquibase:liquibase-core:4.29.2")
    implementation ("org.springframework.boot:spring-boot-starter-data-mongodb")
    implementation("org.mongodb:mongo-java-driver:3.12.14")

    implementation ("org.springframework.boot:spring-boot-starter-security")
    implementation("io.jsonwebtoken:jjwt-api:0.12.6")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.12.6")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.12.6")
    implementation ("org.springframework.boot:spring-boot-starter-validation")

    testImplementation ("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.testcontainers:testcontainers:1.20.6")
    testImplementation("org.testcontainers:postgresql:1.20.6")
    testImplementation("org.testcontainers:junit-jupiter:1.19.3")
    testImplementation ("org.mockito:mockito-core:5.11.0")

}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<JavaCompile> {
    options.compilerArgs.add("-parameters")
}

tasks.compileJava {
    options.compilerArgs.add("-parameters")
}