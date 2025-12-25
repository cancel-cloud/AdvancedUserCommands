package de.cancelcloud.advancedusercommands.screen

import de.cancelcloud.advancedusercommands.config.ComponentCategories
import de.cancelcloud.advancedusercommands.config.CopyMode
import de.cancelcloud.advancedusercommands.config.CopyNBTConfig
import me.shedaniel.clothconfig2.api.ConfigBuilder
import me.shedaniel.clothconfig2.api.ConfigCategory
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder
import net.minecraft.client.gui.screen.Screen
import net.minecraft.text.Text

object ConfigScreen {
    fun create(parent: Screen?): Screen {
        val config = CopyNBTConfig.getInstance()
        val builder = ConfigBuilder.create()
            .setParentScreen(parent)
            .setTitle(Text.literal("Copy-NBT Settings"))
            .setSavingRunnable {
                config.save()
            }

        val entryBuilder = builder.entryBuilder()

        // General category
        val general: ConfigCategory = builder.getOrCreateCategory(Text.literal("General"))

        // Copy Mode dropdown
        var currentMode = config.mode
        general.addEntry(
            entryBuilder.startEnumSelector(
                Text.literal("Copy Mode"),
                CopyMode::class.java,
                config.mode
            )
                .setDefaultValue(CopyMode.ALL)
                .setTooltip(
                    Text.literal("Choose which components to copy:\n\n")
                        .append(Text.literal("§e" + CopyMode.ALL.getDisplayName() + "§r: " + CopyMode.ALL.getTooltip() + "\n\n"))
                        .append(Text.literal("§e" + CopyMode.ESSENTIAL_CUSTOM.getDisplayName() + "§r: " + CopyMode.ESSENTIAL_CUSTOM.getTooltip() + "\n\n"))
                        .append(Text.literal("§e" + CopyMode.CUSTOM.getDisplayName() + "§r: " + CopyMode.CUSTOM.getTooltip()))
                )
                .setSaveConsumer { newMode ->
                    // If switching TO custom mode, initialize with current mode's components
                    if (newMode == CopyMode.CUSTOM && currentMode != CopyMode.CUSTOM) {
                        config.enabledComponents.clear()
                        when (currentMode) {
                            CopyMode.ALL -> {
                                // Enable all components
                                config.enabledComponents.addAll(ComponentCategories.getAllComponentIds())
                            }
                            CopyMode.ESSENTIAL_CUSTOM -> {
                                // Enable only essential components
                                config.enabledComponents.addAll(ComponentCategories.ESSENTIAL_COMPONENTS)
                            }
                            else -> {}
                        }
                    }
                    currentMode = newMode
                    config.mode = newMode
                }
                .setEnumNameProvider { mode -> Text.literal((mode as CopyMode).getDisplayName()) }
                .build()
        )

        // Include empty values toggle
        general.addEntry(
            entryBuilder.startBooleanToggle(
                Text.literal("Include Empty Values"),
                config.includeEmptyValues
            )
                .setDefaultValue(false)
                .setTooltip(
                    Text.literal("Include empty arrays/objects in the copied NBT\n\n")
                        .append(Text.literal("§7Examples: lore:[], enchantments:{}\n"))
                        .append(Text.literal("§7Default: OFF (cleaner output)"))
                )
                .setSaveConsumer { config.includeEmptyValues = it }
                .build()
        )

        /* Add note about Custom mode
        general.addEntry(
            entryBuilder.startTextDescription(
                Text.literal("§7Component categories below are only active in Custom mode.")
            ).build()
        )
         */

        // Custom mode component selection
        // Always show categories, but they only apply in CUSTOM mode
        // Ensure components are initialized
        if (config.enabledComponents.isEmpty()) {
            config.initializeCustomComponents()
        }

        // Add categories for component selection
        for ((categoryName, components) in ComponentCategories.ALL_CATEGORIES) {
            val category: ConfigCategory = builder.getOrCreateCategory(Text.literal(categoryName))

            for ((componentId, displayName) in components) {
                category.addEntry(
                    entryBuilder.startBooleanToggle(
                        Text.literal(displayName),
                        config.enabledComponents.contains(componentId)
                    )
                        .setDefaultValue(componentId in ComponentCategories.ESSENTIAL_COMPONENTS)
                        .setTooltip(
                            Text.literal("Include $componentId component\n§7Only applies when Copy Mode is set to 'Custom Selection'")
                        )
                        .setSaveConsumer { enabled ->
                            if (enabled) {
                                config.enabledComponents.add(componentId)
                            } else {
                                config.enabledComponents.remove(componentId)
                            }
                        }
                        .build()
                )
            }
        }

        return builder.build()
    }
}
