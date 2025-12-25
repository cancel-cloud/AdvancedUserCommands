package de.cancelcloud.advancedusercommands.clipboard

import net.minecraft.client.MinecraftClient

object ClipboardHelper {
    fun setClipboard(text: String) {
        MinecraftClient.getInstance().keyboard.setClipboard(text)
    }
}
