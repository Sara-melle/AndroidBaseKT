@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.com.android.application)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    kotlin("kapt")
}

android {
    namespace = "com.ajwlhzh.crossexpressapp"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.ajwlhzh.crossexpressapp"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions{
        jvmTarget = "1.8"
    }


    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile::class.java){
    kotlinOptions {
        jvmTarget = "1.8"
    }
}


dependencies {

    implementation(libs.bundles.androidx)
    implementation(libs.cardview)
    implementation(libs.recyclerview)
    implementation(libs.constraintlayout)
    implementation(libs.flexbox)
    implementation(libs.bundles.okhttp)
    implementation(libs.bundles.retrofit)
    implementation(libs.mmkv)
    implementation(libs.kotlin.reflect)
    implementation(libs.paging)

    implementation(project(":service:service_main"))
    implementation(project(":features:feature_main"))
    implementation(project(":features:feature_common:common_base"))
    implementation(project(":core:core_router"))
    implementation(project(":core:network"))
    implementation(project(":core:core_tool"))
}