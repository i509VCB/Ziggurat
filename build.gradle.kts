plugins {
    java
    `java-library`
    `kotlin-dsl`
    `maven-publish`
    id("com.diffplug.spotless") version "5.8.2"
    id("fabric-loom") version "0.5-SNAPSHOT"
}

val archivesBaseName: String by rootProject
base.archivesBaseName = archivesBaseName

val mavenGroup: String by rootProject
group = mavenGroup

val baseVersion: String by rootProject
val minecraftVersion: String by rootProject
version = "$baseVersion+$minecraftVersion"

val yarnBuild: String by rootProject
val loaderVersion: String by rootProject
val fabricApiVersion: String by rootProject

allprojects {
    apply {
        plugin("java")
        plugin("java-library")
        plugin("maven-publish")
        plugin("com.diffplug.spotless")
        plugin("fabric-loom")
    }

    repositories {
        mavenCentral()
    }

    dependencies {
        minecraft("com.mojang:minecraft:$minecraftVersion")
        mappings("net.fabricmc:yarn:$minecraftVersion+build.$yarnBuild:v2")
        modImplementation("net.fabricmc:fabric-loader:$loaderVersion")
    }

    loom {
        shareCaches = true
    }

    tasks.compileJava.configure {
        val targetVersion = 8

        if (JavaVersion.current().isJava9Compatible) {
            options.release.set(targetVersion)
        }
    }

    spotless {
        java {
            importOrderFile(rootProject.file("codeformat/ziggurat.importorder"))
        }
    }
}

val apiProject = project(":api") {

}

val coreProject = project(":core") {
    dependencies {
        api(project(":api"))

        modImplementation("net.fabricmc.fabric-api:fabric-api:$fabricApiVersion")
    }
}

repositories {
    mavenCentral()
}
