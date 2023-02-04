import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}

kotlin {
    jvm {}
    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation(compose.desktop.linux_arm64)
                implementation(project(":common"))
            }
        }
    }
}

compose.desktop {
    application {
        mainClass = "com.example.order.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb, TargetFormat.AppImage)
            packageName = "com.example.order"
            packageVersion = "1.0.0"
        }
    }
}
