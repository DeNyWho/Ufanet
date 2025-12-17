plugins {
    alias(libs.plugins.ufanet.library)
    alias(libs.plugins.ufanet.hilt)
}

android {
    namespace = "com.example.ufanet.data.source"
}

dependencies {
    implementation(projects.domain)
    implementation(projects.data.local)
    implementation(projects.data.network)

    implementation(libs.kotlinx.serialization.json)

    testImplementation(libs.junit)
}