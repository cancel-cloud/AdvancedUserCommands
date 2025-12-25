package de.cancelcloud.advancedusercommands.command

import com.mojang.brigadier.Command
import com.mojang.brigadier.context.CommandContext
import de.cancelcloud.advancedusercommands.clipboard.ClipboardHelper
import de.cancelcloud.advancedusercommands.data.ItemDataExtractor
import de.cancelcloud.advancedusercommands.data.SNBTFormatter
import de.cancelcloud.advancedusercommands.state.PendingCopyManager
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource
import net.minecraft.text.Text
import net.minecraft.text.Style
import net.minecraft.util.Formatting

class CopyNBTCommand : Command<FabricClientCommandSource> {
    companion object {
        const val LARGE_STRING_THRESHOLD = 5000
    }

    override fun run(context: CommandContext<FabricClientCommandSource>): Int {
        val player = context.source.player ?: return 0
        val mainHandStack = player.mainHandStack

        // Check if main hand is empty
        if (mainHandStack.isEmpty) {
            context.source.sendFeedback(
                Text.literal("❌ Hold an item in your main hand.")
                    .styled { it.withColor(Formatting.RED) }
            )
            return 0
        }

        // Extract data components
        val extractedData = ItemDataExtractor.extract(mainHandStack)

        // Format to SNBT string with all components
        val snbtString = SNBTFormatter.format(
            itemId = extractedData.itemId,
            componentsNbt = extractedData.componentsNbt
        )

        // Check string length
        if (snbtString.length > LARGE_STRING_THRESHOLD) {
            // Store pending copy
            PendingCopyManager.setPending(snbtString)

            // Create clickable confirmation message using record constructors
            val confirmClickEvent = net.minecraft.text.ClickEvent.RunCommand("/copynbt confirm")
            val confirmHoverEvent = net.minecraft.text.HoverEvent.ShowText(Text.literal("Confirm copy"))

            val confirmText = Text.literal("[✅]")
                .styled { style ->
                    style.withColor(Formatting.GREEN)
                        .withClickEvent(confirmClickEvent)
                        .withHoverEvent(confirmHoverEvent)
                }

            val cancelClickEvent = net.minecraft.text.ClickEvent.RunCommand("/copynbt cancel")
            val cancelHoverEvent = net.minecraft.text.HoverEvent.ShowText(Text.literal("Cancel copy"))

            val cancelText = Text.literal("[❌]")
                .styled { style ->
                    style.withColor(Formatting.RED)
                        .withClickEvent(cancelClickEvent)
                        .withHoverEvent(cancelHoverEvent)
                }

            val warningMessage = Text.literal("⚠ Large NBT (${snbtString.length} chars). Copy to clipboard? ")
                .styled { it.withColor(Formatting.YELLOW) }
                .append(confirmText)
                .append(Text.literal(" "))
                .append(cancelText)

            context.source.sendFeedback(warningMessage)
        } else {
            // Copy immediately
            ClipboardHelper.setClipboard(snbtString)

            context.source.sendFeedback(
                Text.literal("✅ Item NBT copied to clipboard.")
                    .styled { it.withColor(Formatting.GREEN) }
            )
        }

        return 1
    }
}
