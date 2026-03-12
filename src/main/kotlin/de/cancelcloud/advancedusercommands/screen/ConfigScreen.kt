package de.cancelcloud.advancedusercommands.screen

import de.cancelcloud.advancedusercommands.config.ComponentCategories
import de.cancelcloud.advancedusercommands.config.CopyMode
import de.cancelcloud.advancedusercommands.config.CopyNBTConfig
import me.shedaniel.clothconfig2.api.AbstractConfigListEntry
import me.shedaniel.clothconfig2.api.ConfigBuilder
import me.shedaniel.clothconfig2.api.ConfigCategory
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder
import me.shedaniel.clothconfig2.gui.entries.BooleanListEntry
import me.shedaniel.clothconfig2.gui.entries.EnumListEntry
import me.shedaniel.clothconfig2.gui.entries.SelectionListEntry
import me.shedaniel.clothconfig2.gui.entries.TextListEntry
import net.minecraft.client.gui.DrawContext
import net.minecraft.client.gui.screen.Screen
import net.minecraft.text.Text
import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.atomic.AtomicBoolean

object ConfigScreen {
    fun create(parent: Screen?): Screen {
        val config = CopyNBTConfig.getInstance()
        if (config.enabledComponents.isEmpty()) {
            config.initializeCustomComponents()
        }

        val builder = ConfigBuilder.create()
            .setParentScreen(parent)
            .setTitle(Text.literal("Copy-NBT Settings"))
            .setSavingRunnable {
                config.save()
            }

        val entryBuilder = builder.entryBuilder()

        // General category
        val general: ConfigCategory = builder.getOrCreateCategory(Text.literal("General"))

        general.addEntry(
            entryBuilder.startTextDescription(
                Text.literal(
                    "Pick a preset first. Each category below starts collapsed; expanding it lets you override components. " +
                        "Once you flip a toggle, the mode automatically changes to 'Custom Selection'."
                )
            ).build()
        )

        val componentEntries = mutableMapOf<String, BooleanListEntry>()
        val groupEntries = mutableListOf<Pair<Set<String>, BooleanListEntry>>()

        val copyModeEntry: EnumListEntry<CopyMode> = entryBuilder.startEnumSelector(
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
                config.mode = newMode
                syncEntriesForMode(newMode, componentEntries, groupEntries, config)
            }
            .setEnumNameProvider { mode -> Text.literal((mode as CopyMode).getDisplayName()) }
            .build()

        general.addEntry(copyModeEntry)

        var observedMode = copyModeEntry.value
        val selectionSummaryEntry = object : TextListEntry(
            Text.literal("Selection Overview"),
            Text.literal(buildSelectionSummary(observedMode, config))
        ) {
            override fun render(
                context: DrawContext,
                index: Int,
                y: Int,
                x: Int,
                entryWidth: Int,
                entryHeight: Int,
                mouseX: Int,
                mouseY: Int,
                hovered: Boolean,
                tickDelta: Float
            ) {
                val currentMode = copyModeEntry.value
                if (currentMode != observedMode) {
                    observedMode = currentMode
                    syncEntriesForMode(currentMode, componentEntries, groupEntries, config)
                }
                refreshSelectionSummary(this, currentMode, config)
                super.render(context, index, y, x, entryWidth, entryHeight, mouseX, mouseY, hovered, tickDelta)
            }
        }

        general.addEntry(selectionSummaryEntry)

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

        for ((categoryName, components) in ComponentCategories.ALL_CATEGORIES) {
            val subEntries = mutableListOf<AbstractConfigListEntry<*>>()
            val componentIds = components.keys.toSet()

            subEntries += entryBuilder.startBooleanToggle(
                    Text.literal("Toggle entire $categoryName group"),
                    componentIds.all { config.enabledComponents.contains(it) }
                )
                    .setTooltip(
                        Text.literal(
                        "Quickly enable or disable every component in this group.\n" +
                            "Useful when creating a preset before fine tuning."
                    )
                    )
                    .setSaveConsumer { enable ->
                    ensureCustomMode(copyModeEntry, config) {
                        syncEntriesForMode(it, componentEntries, groupEntries, config)
                    }
                    if (enable) {
                        config.enabledComponents.addAll(componentIds)
                    } else {
                        config.enabledComponents.removeAll(componentIds)
                    }
                    syncEntriesForMode(copyModeEntry.value, componentEntries, groupEntries, config)
                }
                .build().also { groupEntries += componentIds to it }

            for ((componentId, displayName) in components) {
                val componentEntry = entryBuilder.startBooleanToggle(
                    Text.literal(displayName),
                    config.enabledComponents.contains(componentId)
                )
                    .setDefaultValue(componentId in ComponentCategories.ESSENTIAL_COMPONENTS)
                    .setTooltip(
                        Text.literal(
                            "Include '$displayName' ($componentId) when copying.\n" +
                                "§7Requires Copy Mode = Custom Selection."
                        )
                    )
                    .setSaveConsumer { enabled ->
                        ensureCustomMode(copyModeEntry, config) {
                            syncEntriesForMode(it, componentEntries, groupEntries, config)
                        }
                        if (enabled) {
                            config.enabledComponents.add(componentId)
                        } else {
                            config.enabledComponents.remove(componentId)
                        }
                        syncEntriesForMode(copyModeEntry.value, componentEntries, groupEntries, config)
                    }
                    .build()
                componentEntries[componentId] = componentEntry
                subEntries += componentEntry
            }

            val subCategory = entryBuilder.startSubCategory(Text.literal(categoryName), subEntries)
                .setExpanded(false)
                .build()

            general.addEntry(subCategory)
        }

        syncEntriesForMode(copyModeEntry.value, componentEntries, groupEntries, config)

        return builder.build()
    }

    private fun buildSelectionSummary(mode: CopyMode, config: CopyNBTConfig): String {
        val lines = ComponentCategories.ALL_CATEGORIES.entries.joinToString(separator = "\n") { (categoryName, components) ->
            val count = components.keys.count { shouldIncludeInMode(it, mode, config) }
            "$categoryName • $count/${components.size} components selected"
        }
        val suffix = if (mode == CopyMode.CUSTOM) {
            "\nCustom Selection is active—expand any category below to fine-tune components."
        } else {
            "\nChanging any category will switch the preset to Custom Selection."
        }
        return "Selection Overview:\n$lines$suffix"
    }

    private fun refreshSelectionSummary(entry: TextListEntry, mode: CopyMode, config: CopyNBTConfig) {
        try {
            summaryTextField.set(entry, Text.literal(buildSelectionSummary(mode, config)))
            savedWidthField.setInt(entry, -1)
        } catch (ignored: Exception) {
            // Ignore reflection failures; summary will remain static.
        }
    }

    private fun shouldIncludeInMode(componentId: String, mode: CopyMode, config: CopyNBTConfig): Boolean {
        return when (mode) {
            CopyMode.ALL -> true
            CopyMode.ESSENTIAL_CUSTOM -> componentId in ComponentCategories.ESSENTIAL_COMPONENTS
            CopyMode.CUSTOM -> componentId in config.enabledComponents
        }
    }

    private fun ensureCustomMode(entry: EnumListEntry<CopyMode>, config: CopyNBTConfig, onModeChange: (CopyMode) -> Unit) {
        if (config.mode == CopyMode.CUSTOM) return
        config.mode = CopyMode.CUSTOM
        forceCopyModeEntry(entry, CopyMode.CUSTOM)
        onModeChange(CopyMode.CUSTOM)
    }

    private fun forceCopyModeEntry(entry: EnumListEntry<CopyMode>, mode: CopyMode) {
        try {
            val values = selectionValuesField.get(entry) as List<*>
            val targetIndex = values.indexOf(mode)
            if (targetIndex < 0) return
            val index = selectionIndexField.get(entry) as AtomicInteger
            if (index.get() != targetIndex) {
                index.set(targetIndex)
            }
        } catch (ignored: Exception) {
            // Ignore reflection failures; UI will resync on reopen.
        }
    }

    private val summaryTextField by lazy {
        TextListEntry::class.java.getDeclaredField("text").apply { isAccessible = true }
    }

    private val savedWidthField by lazy {
        TextListEntry::class.java.getDeclaredField("savedWidth").apply { isAccessible = true }
    }

    private val booleanValueField by lazy {
        BooleanListEntry::class.java.getDeclaredField("bool").apply { isAccessible = true }
    }

    private val selectionIndexField by lazy {
        SelectionListEntry::class.java.getDeclaredField("index").apply { isAccessible = true }
    }

    private val selectionValuesField by lazy {
        SelectionListEntry::class.java.getDeclaredField("values").apply { isAccessible = true }
    }

    private fun syncEntriesForMode(
        mode: CopyMode,
        componentEntries: Map<String, BooleanListEntry>,
        groupEntries: List<Pair<Set<String>, BooleanListEntry>>,
        config: CopyNBTConfig
    ) {
        componentEntries.forEach { (componentId, entry) ->
            val desired = shouldIncludeInMode(componentId, mode, config)
            setBooleanEntry(entry, desired)
        }

        groupEntries.forEach { (componentIds, entry) ->
            val desired = componentIds.all { shouldIncludeInMode(it, mode, config) }
            setBooleanEntry(entry, desired)
        }
    }

    private fun setBooleanEntry(entry: BooleanListEntry, value: Boolean) {
        try {
            val bool = booleanValueField.get(entry) as AtomicBoolean
            bool.set(value)
        } catch (ignored: Exception) {
            // Ignore reflection failure; entry will update when reopened.
        }
    }
}
