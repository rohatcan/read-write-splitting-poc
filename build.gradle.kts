import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
   id("org.springframework.boot") version "2.7.18-SNAPSHOT"
   id("io.spring.dependency-management") version "1.0.15.RELEASE"
   id("com.vaadin") version "23.3.26"
   kotlin("jvm") version "1.6.21"
   kotlin("plugin.spring") version "1.6.21"
   kotlin("plugin.jpa") version "1.6.21"
}

group = "com.rohat"
version = "0.0.1-SNAPSHOT"

java {
   sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
   mavenCentral()
   maven { url = uri("https://repo.spring.io/milestone") }
   maven { url = uri("https://repo.spring.io/snapshot") }
}

extra["vaadinVersion"] = "23.3.26"

dependencies {
   implementation("org.springframework.boot:spring-boot-starter-data-jpa")
   implementation("org.springframework.boot:spring-boot-starter-web")
   implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

   implementation("com.vaadin:vaadin-spring-boot-starter")
   implementation("com.github.mvysny.karibudsl:karibu-dsl:1.1.2")

   implementation("org.jetbrains.kotlin:kotlin-reflect")
   runtimeOnly("com.mysql:mysql-connector-j")

   testImplementation("org.springframework.boot:spring-boot-starter-test")
}

dependencyManagement {
   imports {
      mavenBom("com.vaadin:vaadin-bom:${property("vaadinVersion")}")
   }
}

tasks.withType<KotlinCompile> {
   kotlinOptions {
      freeCompilerArgs += "-Xjsr305=strict"
      jvmTarget = "17"
   }
}

tasks.withType<Test> {
   useJUnitPlatform()
}
