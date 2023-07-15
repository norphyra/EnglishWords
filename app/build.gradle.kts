import com.android.build.api.dsl.Packaging
import com.example.buildPlugin.Versions

typealias dep = com.example.buildPlugin.Dependencies
typealias conf = com.example.buildPlugin.Config

plugins {
    id ("com.android.application")
    id ("org.jetbrains.kotlin.android")
    id ("buildPlugin")

    kotlin("kapt")
}


android {
    compileSdk = conf.compileSdk

    namespace = "com.example.englishwordspetproject"

    defaultConfig {
        applicationId = "com.example.englishwordspetproject"
        minSdk = conf.minSdk
        targetSdk = conf.targetSdk
        versionCode = conf.versionCode
        versionName = conf.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose
    }
    fun Packaging.() {
        resources {
            excludes.add("META-INF/AL2.0")
            excludes.add("META-INF/LGPL2.1")
        }
    }
}

dependencies {

    dep.compose.apply {
        implementation(ui)
        implementation(graphics)
        implementation(preview)
        implementation(tooling)
        implementation(material3)
        implementation(navigation)
        implementation(icons)
    }

    dep.di.apply {
        implementation(dagger)
        kapt (daggerCompiler)
    }

    dep.retrofit.apply {
        implementation(retrofit2)
        implementation(converterGson)
    }

    dep.room.apply {
        implementation(runtime)
        implementation(ktx)
        kapt (compiler)
    }

    dep.test.apply {
        implementation(espresso)
        implementation(junit)
        implementation(extJunit)
        implementation(composeJUnit)
        implementation(composeManifest)
    }

    dep.other.apply {
        implementation(ktxCore)
        implementation(bomKotlin)
        implementation(bomCompose)
        implementation(composeActivity)
        implementation(ktxLifecycle)
        implementation(serialization)
        implementation(ktxViewModel)
        implementation(ktxFragment)
    }
}

