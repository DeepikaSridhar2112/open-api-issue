plugins {
    id("java")
    id("org.openapi.generator").version("5.4.0")
}

group = "org.sample"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}


java {
    sourceCompatibility = JavaVersion.VERSION_17
}
dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

tasks.openApiGenerate {
    generatorName.set("java")

    inputSpec.set("$rootDir/src/main/resources/openapi/openapi.yml")
    outputDir.set("$buildDir/generated")
    apiPackage.set("com.example.api")
    modelPackage.set("com.example.model")
    configOptions.set(
        mapOf(
            "delegatePattern" to "true",
            "generateApiTests" to "true",
            "generateModelTests" to "true",
            "useTags" to "true"
        )
    )
    globalProperties.apply {
        put("apis", "")
        put("models", "")
        put("modelDocs", "false")
    }
}