plugins {
    alias(libs.plugins.ufanet.library)
    alias(libs.plugins.ufanet.hilt)
}

android {
    namespace = "com.example.ufanet.data.network"
    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(projects.domain)

    implementation(libs.kotlinx.serialization.json)
    implementation(libs.retrofit.kotlinx.serialization)
    implementation(libs.gson)

    implementation(libs.retrofit)
    implementation(libs.okhttp)
    implementation(libs.okhttp.interceptor)
    implementation(libs.slf4j.simple)

    testImplementation(libs.junit)
}