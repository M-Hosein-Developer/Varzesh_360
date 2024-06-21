plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies{

    //Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(project(":domain"))


    //Coroutines
    implementation(libs.kotlinx.coroutines.android)
}