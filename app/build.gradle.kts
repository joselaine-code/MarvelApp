import org.gradle.api.JavaVersion
import java.io.FileInputStream
import java.util.Properties

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    id("io.gitlab.arturbosch.detekt")
    kotlin("kapt")
}

val keyStoreFile = rootProject.file("keystore.properties")
val keyStoreProperties = Properties().apply {
    load(FileInputStream(keyStoreFile))
}

android {
    namespace = "com.joselaine.marvelapp"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.joselaine.marvelapp"
        minSdk = 21
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true

        buildConfigField("String", "PUBLIC_KEY", keyStoreProperties.getProperty("PUBLIC_KEY"))
        buildConfigField("String", "PRIVATE_KEY", keyStoreProperties.getProperty("PRIVATE_KEY"))
        buildConfigField("String", "BASE_URL", "\"https://gateway.marvel.com/v1/public/\"")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.2"
    }
    packaging {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.8.0")
    implementation(enforcedPlatform("org.jetbrains.kotlin:kotlin-bom:1.8.0"))
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")
    implementation("androidx.activity:activity-compose:1.5.1")
    implementation(enforcedPlatform("androidx.compose:compose-bom:2022.10.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.material:material")
    testImplementation("junit:junit:4.13.2")
    testImplementation("io.mockk:mockk:1.12.0")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.1")
    testImplementation("junit:junit:4.12")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
    androidTestImplementation("io.mockk:mockk:1.12.0")
    androidTestImplementation(enforcedPlatform("androidx.compose:compose-bom:2022.10.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    // HILT
    implementation("com.google.dagger:hilt-android:2.46.1")
    kapt("com.google.dagger:hilt-android-testing:2.46.1")
    kapt("com.google.dagger:hilt-compiler:2.46.1")

    implementation("androidx.navigation:navigation-compose:2.6.0")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")

    implementation("androidx.paging:paging-runtime-ktx:3.1.1")
    implementation("androidx.paging:paging-compose:3.2.0-beta01")

    implementation("com.google.accompanist:accompanist-systemuicontroller:0.27.0")

    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    implementation("io.coil-kt:coil-compose:2.4.0")

    api(enforcedPlatform("com.squareup.okhttp3:okhttp-bom:4.9.0"))
    api("com.squareup.okhttp3:okhttp")
    api("com.squareup.okhttp3:logging-interceptor")

    val room_version = "2.5.1"
    implementation("androidx.room:room-ktx:$room_version")
    implementation("androidx.room:room-runtime:$room_version")
    implementation("androidx.room:room-paging:$room_version")
    kapt("androidx.room:room-compiler:$room_version")
}

tasks.named("preBuild") {
    dependsOn("detekt")
}

detekt {
    toolVersion = "1.23.1"
    val inputDirFiles = rootProject.subprojects.map { module ->
        "${module.projectDir}/src/main/java"
    }
    source.setFrom(files(inputDirFiles))
    config.setFrom(files("$rootDir/config/detekt/detekt.yml"))
    autoCorrect = true
}