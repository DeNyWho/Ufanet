plugins {
    alias(libs.plugins.ufanet.library.compose)
    alias(libs.plugins.ufanet.library)
    alias(libs.plugins.ufanet.hilt)
}

android {
    namespace = "com.example.ufanet.core.uikit"
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
            isReturnDefaultValues = true
        }
    }
}

dependencies {
    implementation(projects.domain)

    api(libs.androidx.compose.foundation)
    api(libs.androidx.compose.foundation.layout)
    api(libs.androidx.compose.material.iconsExtended)
    api(libs.androidx.compose.material3)
    api(libs.androidx.compose.runtime)
    api(libs.androidx.compose.ui.util)
    implementation(libs.paging.runtime)
    implementation(libs.paging.compose)

    api(libs.shimmer.compose)

    api(libs.coil.kt.compose)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
}