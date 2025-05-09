

plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"


repositories {
    mavenCentral()
}

dependencies {
    implementation ("org.springframework.boot:spring-boot-starter-data-jdbc")
    implementation("org.springframework:spring-jdbc:6.1.13")
    runtimeOnly ("org.postgresql:postgresql")
    implementation("org.postgresql:postgresql:42.7.4")
    implementation ("com.fasterxml.jackson.dataformat:jackson-dataformat-csv:2.15.2")
    implementation ("org.springframework:spring-context:6.0.11")
    implementation ("org.slf4j:slf4j-api:2.0.7")
    implementation ("org.slf4j:slf4j-simple:2.0.7")
    testImplementation ("junit:junit:4.13.2")
    implementation ("com.fasterxml.jackson.dataformat:jackson-dataformat-csv:2.13.0")
    implementation("org.springframework:spring-aop:5.2.9.RELEASE")
    implementation("org.springframework:spring-aspects:5.2.9.RELEASE")

}

tasks.test {
    useJUnitPlatform()
}


