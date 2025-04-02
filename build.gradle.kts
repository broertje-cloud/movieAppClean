plugins {
    kotlin("multiplatform") version "1.9.0" apply false
    id("com.android.application") version "8.1.0" apply false
    id("com.android.library") version "8.1.0" apply false
    kotlin("android") version "1.9.0" apply false
    kotlin("plugin.serialization") version "1.9.0" apply false
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}
