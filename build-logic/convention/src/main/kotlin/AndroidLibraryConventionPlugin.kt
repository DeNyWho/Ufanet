import com.android.build.api.dsl.LibraryExtension
import com.android.build.api.variant.LibraryAndroidComponentsExtension
import com.example.ufanet.buildlogic.convention.configureKotlinAndroid
import com.example.ufanet.buildlogic.convention.disableUnnecessaryAndroidTests
import com.example.ufanet.buildlogic.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.kotlin

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
                apply("org.jetbrains.kotlin.plugin.serialization")
            }

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
                testOptions.targetSdk = 36
                defaultConfig.testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                testOptions.animationsDisabled = true
                resourcePrefix =
                    path.split("""\W""".toRegex()).drop(1).distinct().joinToString(separator = "_")
                        .lowercase() + "_"
            }

            extensions.configure<LibraryAndroidComponentsExtension> {
                disableUnnecessaryAndroidTests(target)
            }

            dependencies {
                add("androidTestImplementation", kotlin("test"))
                add("testImplementation", kotlin("test"))

                add("implementation", libs.findLibrary("kotlinx.collections.immutable").get())
                add("implementation", libs.findLibrary("androidx.tracing.ktx").get())
            }
        }
    }
}