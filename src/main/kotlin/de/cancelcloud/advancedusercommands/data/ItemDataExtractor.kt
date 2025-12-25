package de.cancelcloud.advancedusercommands.data

import de.cancelcloud.advancedusercommands.config.ComponentCategories
import de.cancelcloud.advancedusercommands.config.CopyNBTConfig
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NbtCompound
import net.minecraft.nbt.NbtElement
import net.minecraft.nbt.NbtOps
import net.minecraft.registry.Registries
import net.minecraft.component.ComponentType

data class ExtractedItemData(
    val itemId: String,
    val componentsNbt: NbtCompound?
)

object ItemDataExtractor {
    fun extract(stack: ItemStack): ExtractedItemData {
        val config = CopyNBTConfig.getInstance()

        // Get item ID from registry
        val itemId = Registries.ITEM.getId(stack.item).toString()

        // Get all components from the stack
        val components = stack.components

        // Create an NBT compound to store all components
        val componentsNbt = NbtCompound()

        // Iterate through all component types in the registry
        for (componentType in Registries.DATA_COMPONENT_TYPE) {
            try {
                // Check if this component exists on the stack
                if (components.contains(componentType)) {
                    @Suppress("UNCHECKED_CAST")
                    val typedComponentType = componentType as ComponentType<Any>
                    val value = components.get(typedComponentType)

                    if (value != null) {
                        // Get the component type ID
                        val componentId = Registries.DATA_COMPONENT_TYPE.getId(componentType)

                        if (componentId != null) {
                            val componentPath = componentId.path

                            // Check if this component should be included based on config
                            if (!ComponentCategories.shouldIncludeComponent(componentPath, config)) {
                                continue
                            }

                            // Get the codec for this component type
                            val codec = typedComponentType.codec

                            if (codec != null) {
                                // Encode the component value to NBT
                                val nbtResult = codec.encodeStart(NbtOps.INSTANCE, value)

                                // Check if encoding was successful
                                if (nbtResult.isSuccess) {
                                    val nbtElement = nbtResult.result().get()

                                    // Check if we should skip empty values
                                    if (!config.includeEmptyValues && isEmptyValue(nbtElement)) {
                                        continue
                                    }

                                    // Use the path part of the identifier as the key
                                    componentsNbt.put(componentPath, nbtElement)
                                }
                            }
                        }
                    }
                }
            } catch (e: Exception) {
                // Skip components that can't be encoded
            }
        }

        // Return item ID and all components (null if empty)
        return ExtractedItemData(
            itemId = itemId,
            componentsNbt = if (componentsNbt.isEmpty) null else componentsNbt
        )
    }

    // Check if an NBT element is empty (empty array, empty compound, etc.)
    private fun isEmptyValue(element: NbtElement): Boolean {
        return when (element) {
            is NbtCompound -> element.isEmpty
            is net.minecraft.nbt.NbtList -> element.isEmpty()
            is net.minecraft.nbt.NbtString -> element.asString().isEmpty()
            else -> false
        }
    }
}
