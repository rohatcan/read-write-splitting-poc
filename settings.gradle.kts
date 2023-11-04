pluginManagement {
   repositories {
      maven { url = uri("https://repo.spring.io/milestone") }
      maven { url = uri("https://repo.spring.io/snapshot") }
      gradlePluginPortal()
   }
}
rootProject.name = "read-write-splitting-poc"
