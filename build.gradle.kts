import java.nio.charset.StandardCharsets

plugins {
	java
	`java-library`
	`kotlin-dsl`
	`maven-publish`
	id("com.diffplug.spotless") version "5.8.2"
	id("fabric-loom") version "0.5-SNAPSHOT"
	id("org.spongepowered.gradle.sort") version "0.11.5" // So we can sort the fields in "Flags"
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
		plugin("org.spongepowered.gradle.sort")
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
			//indentWithTabs() // Replace: Spotless is really bad at this
			endWithNewline()

			encoding = StandardCharsets.UTF_8
		}

		format("json") {
			target("**/*.json")

			trimTrailingWhitespace()
			indentWithTabs()
			endWithNewline()
		}

		format("misc") {
			target(
					"build.gradle.kts",
					"settings.gradle.kts",
					"**/*.md",
					".gitignore",
					"**/*gradle.properties"
			)

			trimTrailingWhitespace()
			indentWithTabs()
			endWithNewline()
		}
	}
}

val apiProject = project(":api") {
	tasks.sortClassFields {
		add(sourceSets.main.name, "me.i509.ziggurat.api.flag.Flags")
	}
}

val coreProject = project(":core") {
	dependencies {
		api(apiProject)

		setOf(
				"fabric-api-base",
				"fabric-command-api-v1",
				"fabric-events-interaction-v0",
				"fabric-lifecycle-events-v1"
		).forEach { module ->
			modImplementation(fabricApi.module(module, fabricApiVersion))?.let {
				include(it)
			}
		}
	}
}
