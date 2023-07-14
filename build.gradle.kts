// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {

    val gradleVersion = "8.0.2"
    val gradlePluginVersion = "1.6.21"

    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:$gradleVersion")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$gradlePluginVersion")
    }
}

plugins {
    id ("com.android.application") version "8.0.2" apply false
    id ("com.android.library") version "8.0.2" apply false
    id ("org.jetbrains.kotlin.android") version "1.8.10" apply false

}
