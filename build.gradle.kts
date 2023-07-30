// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {

    val gradleVersion = "8.1.0"
    val kotlinGradlePluginVersion = "1.6.21"

    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:$gradleVersion")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinGradlePluginVersion")
    }
}

plugins {
    id ("com.android.application") version "8.1.0" apply false
    id ("com.android.library") version "8.1.0" apply false
    id ("org.jetbrains.kotlin.android") version "1.8.10" apply false
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0" apply false

}
