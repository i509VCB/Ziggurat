rootProject.name = "Ziggurat"

include("api")
include("core")

pluginManagement {
	repositories {
		maven("https://maven.fabricmc.net/") {
			name = "Fabric"
		}

		gradlePluginPortal()
		jcenter()
	}
}
