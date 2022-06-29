plugins {
    id("groovy")
}

dependencies {
    //Groovy DSL
    implementation(gradleApi())
    //Gradle DSL
    implementation(localGroovy())
}

