Что нужно для сборки проекта:

Android: gradle android:build

Linux:
 - В desktop/build.gradle.kts указать implementation(compose.desktop.currentOs) (если указано другое).
 - gradle desktop:run

Raspberry:
 - В desktop/build.gradle.kts указать implementation(compose.desktop.linux_arm64) (если указано другое).
 - gradle desktop:packageUberJarForCurrentOS
 
