plugins {
    alias(libs.plugins.ufanet.library)
    alias(libs.plugins.ufanet.hilt)
    alias(libs.plugins.ufanet.room)
}

android {
    namespace = "com.example.ufanet.data.local"
}

dependencies {
    implementation(projects.domain)
    implementation(libs.kotlinx.serialization.json)
    testImplementation(libs.junit)
}