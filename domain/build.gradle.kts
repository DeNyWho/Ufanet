plugins {
    alias(libs.plugins.ufanet.library.compose)
    alias(libs.plugins.ufanet.library)
    alias(libs.plugins.ufanet.hilt)
}

android {
    namespace = "com.example.ufanet.domain"
}

dependencies {
    implementation(libs.paging.runtime)
    implementation(libs.kotlinx.serialization.json)
}