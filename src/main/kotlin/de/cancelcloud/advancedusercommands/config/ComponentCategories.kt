package de.cancelcloud.advancedusercommands.config

object ComponentCategories {
    // Essential components for ESSENTIAL_CUSTOM mode
    val ESSENTIAL_COMPONENTS = setOf(
        "custom_data",
        "enchantments",
        "stored_enchantments",
        "custom_name",
        "lore",
        "damage",
        "unbreakable"
    )

    // Display-related components
    val DISPLAY = mapOf(
        "custom_name" to "Custom Name",
        "lore" to "Lore",
        "item_model" to "Item Model",
        "rarity" to "Rarity",
        "tooltip_display" to "Tooltip Display",
        "dyed_color" to "Dyed Color",
        "item_name" to "Item Name"
    )

    // Gameplay-related components
    val GAMEPLAY = mapOf(
        "damage" to "Damage",
        "max_damage" to "Max Damage",
        "enchantments" to "Enchantments",
        "stored_enchantments" to "Stored Enchantments (Books)",
        "attribute_modifiers" to "Attribute Modifiers",
        "unbreakable" to "Unbreakable",
        "can_break" to "Can Break",
        "can_place_on" to "Can Place On",
        "tool" to "Tool Properties",
        "weapon" to "Weapon Properties",
        "enchantable" to "Enchantable",
        "repairable" to "Repairable"
    )

    // Custom/Plugin data
    val CUSTOM = mapOf(
        "custom_data" to "Custom Data (Plugins)"
    )

    // Metadata components
    val METADATA = mapOf(
        "repair_cost" to "Repair Cost",
        "max_stack_size" to "Max Stack Size",
        "hide_tooltip" to "Hide Tooltip",
        "hide_additional_tooltip" to "Hide Additional Tooltip"
    )

    // Container-related components
    val CONTAINER = mapOf(
        "container" to "Container Contents",
        "bundle_contents" to "Bundle Contents",
        "charged_projectiles" to "Charged Projectiles",
        "lodestone_tracker" to "Lodestone Tracker"
    )

    // Book-related components
    val BOOK = mapOf(
        "written_book_content" to "Written Book Content",
        "writable_book_content" to "Writable Book Content",
        "pages" to "Pages"
    )

    // Sound/Effect components
    val EFFECTS = mapOf(
        "break_sound" to "Break Sound",
        "use_sound" to "Use Sound",
        "place_sound" to "Place Sound",
        "note_block_sound" to "Note Block Sound"
    )

    // All categories combined
    val ALL_CATEGORIES = mapOf(
        "Display" to DISPLAY,
        "Gameplay" to GAMEPLAY,
        "Custom Data" to CUSTOM,
        "Metadata" to METADATA,
        "Container" to CONTAINER,
        "Book" to BOOK,
        "Effects" to EFFECTS
    )

    // Get all component IDs
    fun getAllComponentIds(): Set<String> {
        return ALL_CATEGORIES.values.flatMap { it.keys }.toSet()
    }

    // Get display name for a component ID
    fun getDisplayName(componentId: String): String {
        return ALL_CATEGORIES.values
            .flatMap { it.entries }
            .find { it.key == componentId }
            ?.value ?: componentId
    }

    // Check if component should be included based on mode and config
    fun shouldIncludeComponent(componentId: String, config: CopyNBTConfig): Boolean {
        return when (config.mode) {
            CopyMode.ALL -> true
            CopyMode.ESSENTIAL_CUSTOM -> componentId in ESSENTIAL_COMPONENTS
            CopyMode.CUSTOM -> componentId in config.enabledComponents
        }
    }
}
