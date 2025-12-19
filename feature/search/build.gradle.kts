plugins {
    alias(libs.plugins.ufanet.feature)
    alias(libs.plugins.ufanet.library.compose)
}

android {
    namespace = "com.example.ufanet.feature.search"
}

dependencies {
    implementation(libs.paging.runtime)
    implementation(libs.paging.compose)
    testImplementation(libs.hilt.android.testing)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
}