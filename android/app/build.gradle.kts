plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

// The app shows the same page as the repo's index.html, copied in at build time.
val copyChartPage by tasks.registering(Copy::class) {
    from(rootProject.file("../index.html"))
    into(layout.buildDirectory.dir("generated/chartAssets"))
}

val familyKey = file("family-chart.keystore")

android {
    namespace = "com.mteck.sparklechart"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.mteck.sparklechart"
        minSdk = 26
        targetSdk = 34
        versionCode = 3
        versionName = "1.2"
    }

    // One family-only key, so a new APK installs over the old one and keeps the saved token.
    signingConfigs {
        create("family") {
            storeFile = familyKey
            storePassword = "sparklechart"
            keyAlias = "family"
            keyPassword = "sparklechart"
        }
    }
    buildTypes {
        debug { if (familyKey.exists()) signingConfig = signingConfigs.getByName("family") }
        release {
            isMinifyEnabled = false
            if (familyKey.exists()) signingConfig = signingConfigs.getByName("family")
        }
    }
    sourceSets["main"].assets.srcDir(layout.buildDirectory.dir("generated/chartAssets"))
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions { jvmTarget = "17" }
}

tasks.named("preBuild") { dependsOn(copyChartPage) }

dependencies {
    implementation("androidx.webkit:webkit:1.12.1")
}
