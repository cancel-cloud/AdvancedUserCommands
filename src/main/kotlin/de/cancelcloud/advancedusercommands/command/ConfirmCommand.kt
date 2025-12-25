package de.cancelcloud.advancedusercommands.command

import com.mojang.brigadier.Command
import com.mojang.brigadier.context.CommandContext
import de.cancelcloud.advancedusercommands.clipboard.ClipboardHelper
import de.cancelcloud.advancedusercommands.state.PendingCopyManager
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource
import net.minecraft.text.Text
import net.minecraft.util.Formatting

class ConfirmCommand : Command<FabricClientCommandSource> {
    companion object {
        const val EXPIRY_SECONDS = 30L
    }

    override fun run(context: CommandContext<FabricClientCommandSource>): Int {
        val pending = PendingCopyManager.getPending()

        if (pending == null) {
            context.source.sendFeedback(
                Text.literal("❌ No pending copy request.")
                    .styled { it.withColor(Formatting.RED) }
            )
            return 0
        }

        val ageSeconds = PendingCopyManager.getAgeSeconds()

        if (ageSeconds > EXPIRY_SECONDS) {
            PendingCopyManager.clear()
            context.source.sendFeedback(
                Text.literal("❌ Request expired. Run /copynbt again.")
                    .styled { it.withColor(Formatting.RED) }
            )
            return 0
        }

        // Copy to clipboard
        ClipboardHelper.setClipboard(pending.content)
        PendingCopyManager.clear()

        context.source.sendFeedback(
            Text.literal("✅ Item NBT copied to clipboard.")
                .styled { it.withColor(Formatting.GREEN) }
        )

        return 1
    }
}
