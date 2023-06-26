plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("com.android.library")
    id("org.jetbrains.compose")
}

kotlin {
    android()
    jvm("desktop")

    sourceSets {
        val ktor_version = "2.3.1"
        val serializationVersion = "1.5.1"
        val kotlinVersion = extra["kotlin.version"] as String

        val commonMain by getting {
            dependencies {
                api(compose.runtime)
                api(compose.foundation)
                api(compose.material)
                api(compose.materialIconsExtended)
                implementation("io.ktor:ktor-client-core:$ktor_version")
                implementation("io.ktor:ktor-client-content-negotiation:$ktor_version")
                implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$serializationVersion")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
            }
        }
        val androidMain by getting {
            dependencies {
                val lifecycle_version = "2.6.1"
                implementation("com.github.mik3y:usb-serial-for-android:3.5.1")
                api("androidx.appcompat:appcompat:1.6.1")
                api("androidx.core:core-ktx:1.10.1")
                implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")
                implementation("androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycle_version")
                implementation("io.ktor:ktor-client-android:$ktor_version")
            }
        }
        val desktopMain by getting {
            dependencies {
                implementation("com.fazecast:jSerialComm:2.9.3")
                implementation("io.ktor:ktor-client-java:$ktor_version")
                implementation("org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion")
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}
