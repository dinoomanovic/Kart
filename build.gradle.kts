// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.2.1" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    id("com.google.dagger.hilt.android") version "2.49" apply false
    id("org.jetbrains.kotlin.plugin.serialization") version "1.5.0"

}

extra["composeVersion"] = "1.1.1"
extra["roomVersion"] = "2.4.2"
extra["espressoVersion"] = "3.4.0"
extra["testCoreVersion"] = "1.4.0"
extra["uiAutomatorVersion"] = "2.2.0"
extra["daggerHiltVersion"] = "2.40.5"
extra["coroutineVersion"] = "1.6.0-native-mt"
extra["timberVersion"] = "5.0.1"

buildscript {

    repositories {
        google()
        mavenCentral()
    }
    dependencies {

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}
