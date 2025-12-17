plugins {
    alias(libs.plugins.ufanet.application)
    alias(libs.plugins.ufanet.application.compose)
    alias(libs.plugins.ufanet.hilt)
}

android {
    namespace = "com.example.ufanet"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.ufanet"
        versionCode = 1
        versionName = "0.1.1"

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        debug {
            extra["enableCrashlytics"] = false
        }
        release {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    packaging {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

dependencies {
    implementation(projects.feature.search)

    implementation(projects.core.uikit)

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.material3.adaptive)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.lifecycle.runtimeCompose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.tracing.ktx)
    implementation(libs.kotlinx.coroutines.guava)

    ksp(libs.hilt.compiler)

    debugImplementation(libs.androidx.compose.ui.testManifest)

    kspTest(libs.hilt.compiler)

    testImplementation(libs.hilt.android.testing)

    androidTestImplementation(libs.androidx.test.espresso.core)
    androidTestImplementation(libs.androidx.navigation.testing)
    androidTestImplementation(libs.hilt.android.testing)
}

dependencyGuard {
    configuration("prodReleaseRuntimeClasspath")
}