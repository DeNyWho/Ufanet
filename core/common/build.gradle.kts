plugins {
    alias(libs.plugins.ufanet.library)
    alias(libs.plugins.ufanet.hilt)
}

android {
    namespace = "com.example.ufanet.core.common"
}

dependencies {
    implementation(libs.kotlinx.serialization.json)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
}