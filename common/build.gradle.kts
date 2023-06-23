plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("org.jetbrains.compose")
}

kotlin {
    android()
    jvm("desktop")

    sourceSets {
        val commonMain by getting {
            dependencies {
                val ktor_version = "2.3.1"
                api(compose.runtime)
                api(compose.foundation)
                api(compose.material)
                api(compose.materialIconsExtended)
                implementation("io.ktor:ktor-client-core:$ktor_version")
                implementation("io.ktor:ktor-client-content-negotiation:$ktor_version")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")
            }
        }
        val androidMain by getting {
            dependencies {
                val lifecycle_version = "2.6.1"
                implementation("com.github.mik3y:usb-serial-for-android:3.5.1")
                api("androidx.appcompat:appcompat:1.6.1")
                api("androidx.core:core-ktx:1.9.0")
                implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")
                implementation("androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycle_version")
            }
        }
        val desktopMain by getting {
            dependencies {
                implementation("com.fazecast:jSerialComm:2.9.3")
                api(compose.desktop.common)
            }
        }
    }
}

android {
    compileSdk = 33
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 24
        targetSdk = 33
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}
