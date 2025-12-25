plugins {
    kotlin("jvm") version "2.2.21"
    id("fabric-loom") version "1.11-SNAPSHOT"
}

group = "de.cancelcloud"
version = project.property("mod_version") as String
base {
    archivesName.set(project.property("archives_base_name") as String)
}

repositories {
    mavenCentral()
    maven("https://maven.fabricmc.net/")
    maven("https://maven.terraformersmc.com/releases/")
    maven("https://maven.shedaniel.me/")
}

dependencies {
    // Minecraft and Fabric
    minecraft("com.mojang:minecraft:${project.property("minecraft_version")}")
    mappings("net.fabricmc:yarn:${project.property("yarn_mappings")}:v2")
    modImplementation("net.fabricmc:fabric-loader:${project.property("loader_version")}")

    // Fabric API
    modImplementation("net.fabricmc.fabric-api:fabric-api:${project.property("fabric_api_version")}")

    // Fabric Language Kotlin
    modImplementation("net.fabricmc:fabric-language-kotlin:${project.property("fabric_language_kotlin_version")}")

    // ModMenu (optional, for mod list visibility)
    modImplementation("com.terraformersmc:modmenu:${project.property("modmenu_version")}")

    // Cloth Config (for settings UI)
    modImplementation("me.shedaniel.cloth:cloth-config-fabric:${project.property("cloth_config_version")}")
}

kotlin {
    jvmToolchain(21)
}

tasks.processResources {
    inputs.property("version", project.version)

    filesMatching("fabric.mod.json") {
        expand("version" to project.version)
    }
}

tasks.jar {
    from("LICENSE") {
        rename { "${it}_${project.property("archives_base_name")}" }
    }
}
