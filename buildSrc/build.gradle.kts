import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
    // kotlin("jvm") version "1.5.31"
    // id("org.jetbrains.kotlin.android") version "1.6.10" apply false
}

// val compileKotlin: KotlinCompile by tasks
// val compileTestKotlin: KotlinCompile by tasks
// compileKotlin.kotlinOptions {
//     jvmTarget = "1.8"
// }
// compileTestKotlin.kotlinOptions {
//     jvmTarget = "1.8"
// }

repositories {
    google()
    mavenCentral()
}
dependencies {
//    compileOnly("com.android.tools.build:gradle:7.2.0")
//     implementation("com.android.tools.build:gradle-api:7.2.0")
//     implementation(kotlin("stdlib"))
    gradleApi()
}