import org.gradle.kotlin.dsl.`kotlin-dsl`

plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
}

group = "com.example.buildPlugin"
version = "0.0.1"

repositories {
    mavenCentral()
}

gradlePlugin {
    plugins.register("buildPlugin") {
        id = "buildPlugin"
        implementationClass = "com.example.buildPlugin.BuildPlugin"
    }
}