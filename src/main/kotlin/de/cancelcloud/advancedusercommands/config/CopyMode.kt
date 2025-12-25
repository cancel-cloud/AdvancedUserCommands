package de.cancelcloud.advancedusercommands.config

enum class CopyMode {
    ALL,                // Copy all components
    ESSENTIAL_CUSTOM,   // Copy only essential + custom data
    CUSTOM;             // User-defined selection

    fun getDisplayName(): String = when (this) {
        ALL -> "All Components"
        ESSENTIAL_CUSTOM -> "Essential + Custom"
        CUSTOM -> "Custom Selection"
    }

    fun getTooltip(): String = when (this) {
        ALL -> "Copy all item data components (current behavior)"
        ESSENTIAL_CUSTOM -> "Copy only custom data, enchantments, damage, name, and lore"
        CUSTOM -> "Choose exactly which components to copy"
    }
}
