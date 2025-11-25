import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    id("com.android.application") version "8.13.1"
    id("org.jetbrains.kotlin.android") version "2.2.21"
    id("org.jetbrains.kotlin.plugin.compose") version "2.2.21"
    id("com.google.devtools.ksp") version "2.3.0"
    id("app.cash.sqldelight") version "2.2.1"
    kotlin("plugin.serialization") version "2.2.0"
    kotlin("plugin.allopen") version "2.2.21"
}

android {
    namespace = "org.labcluster.crm"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "org.labcluster.crm"
        minSdk = 31
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            isDebuggable = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        debug {
            isDebuggable = true
        }
    }

    buildFeatures {
        compose = true
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlin {
        compilerOptions {
            jvmTarget = JvmTarget.fromTarget("11")
            freeCompilerArgs.add("-opt-in=androidx.compose.material3.ExperimentalMaterial3ExpressiveApi")
            freeCompilerArgs.add("-opt-in=androidx.compose.material3.ExperimentalMaterial3Api")
            freeCompilerArgs.add("-opt-in=kotlin.time.ExperimentalTime")
            freeCompilerArgs.add("-opt-in=kotlin.uuid.ExperimentalUuidApi")
            freeCompilerArgs.add("-opt-in=kotlinx.serialization.ExperimentalSerializationApi")
            freeCompilerArgs.add("-opt-in=kotlinx.coroutines.DelicateCoroutinesApi")
        }
    }
}

allOpen {
    annotation("org.labcluster.crm.Open")
}

//noinspection UseTomlInstead
dependencies {
    implementation("androidx.core:core-ktx:1.17.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.10.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.10.2")
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.7.1")
    implementation("app.cash.sqldelight:android-driver:2.2.1")

    //Compose
    implementation(platform("androidx.compose:compose-bom:2025.11.01"))
    implementation("androidx.activity:activity-compose:1.12.0")
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.foundation:foundation:1.9.5")
    implementation("androidx.compose.foundation:foundation-layout:1.9.5")

    //Navigation 3
    implementation("androidx.navigation3:navigation3-runtime:1.0.0")
    implementation("androidx.navigation3:navigation3-ui:1.0.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-navigation3:2.10.0")
    implementation("androidx.compose.material3.adaptive:adaptive:1.3.0-alpha04")

    //Serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:1.9.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.9.0")

    //Material 3
    implementation("androidx.compose.material3:material3:1.5.0-alpha09")
    implementation("androidx.compose.material:material-icons-extended")
    implementation("androidx.compose.ui:ui:1.9.5")

    //SQLDelight
    implementation("app.cash.sqldelight:android-driver:2.2.1")
    implementation("androidx.room:room-ktx:2.8.4")
    debugImplementation("app.cash.sqldelight:sqlite-driver:2.2.1")
    implementation("app.cash.sqldelight:coroutines-extensions:2.2.1")

    //Shared
    implementation("org.labcluster.crm:shared:1.0.22-SNAPSHOT")

    //Tests
    androidTestImplementation(platform("androidx.compose:compose-bom:2025.11.01"))
    androidTestImplementation("androidx.test.ext:junit:1.3.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.7.0")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    testImplementation("junit:junit:4.13.2")
}

afterEvaluate {
    tasks.named("assembleDebug") { dependsOn("testDebugUnitTest") }
    tasks.named("assembleRelease") { dependsOn("testReleaseUnitTest") }
}