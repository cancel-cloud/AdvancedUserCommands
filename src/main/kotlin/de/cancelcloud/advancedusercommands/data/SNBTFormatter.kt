package de.cancelcloud.advancedusercommands.data

import net.minecraft.nbt.NbtCompound

object SNBTFormatter {
    fun format(
        itemId: String,
        componentsNbt: NbtCompound?
    ): String {
        // If no components, return just the item ID
        if (componentsNbt == null || componentsNbt.isEmpty) {
            return itemId
        }

        // Convert the NBT compound to SNBT string
        // Use toString() to get the SNBT representation
        val snbtString = componentsNbt.toString()

        // Format: item_id{...}
        // The toString() method returns the compound in SNBT format like {key:value,...}
        return "$itemId$snbtString"
    }
}
