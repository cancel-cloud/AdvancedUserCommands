package de.cancelcloud.advancedusercommands.config

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.io.File

data class CopyNBTConfig(
    var mode: CopyMode = CopyMode.ALL,
    var includeEmptyValues: Boolean = false,
    var enabledComponents: MutableSet<String> = mutableSetOf()
) {
    companion object {
        private val GSON: Gson = GsonBuilder().setPrettyPrinting().create()
        private val CONFIG_FILE = File("config/advanced-user-commands.json")

        // Singleton instance
        private var instance: CopyNBTConfig? = null

        fun getInstance(): CopyNBTConfig {
            if (instance == null) {
                instance = load()
            }
            return instance!!
        }

        fun load(): CopyNBTConfig {
            return try {
                if (CONFIG_FILE.exists()) {
                    val json = CONFIG_FILE.readText()
                    GSON.fromJson(json, CopyNBTConfig::class.java) ?: CopyNBTConfig()
                } else {
                    CopyNBTConfig().also { it.save() }
                }
            } catch (e: Exception) {
                println("[Advanced User Commands] Failed to load config: ${e.message}")
                CopyNBTConfig()
            }
        }

        fun save(config: CopyNBTConfig) {
            try {
                CONFIG_FILE.parentFile?.mkdirs()
                val json = GSON.toJson(config)
                CONFIG_FILE.writeText(json)
            } catch (e: Exception) {
                println("[Advanced User Commands] Failed to save config: ${e.message}")
            }
        }
    }

    fun save() {
        save(this)
    }

    // Initialize enabled components based on mode when first switching to CUSTOM
    fun initializeCustomComponents() {
        if (enabledComponents.isEmpty()) {
            // Default to ESSENTIAL_CUSTOM components when first switching to CUSTOM mode
            enabledComponents.addAll(ComponentCategories.ESSENTIAL_COMPONENTS)
        }
    }
}
