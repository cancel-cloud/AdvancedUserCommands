package de.cancelcloud.advancedusercommands.command

import com.mojang.brigadier.Command
import com.mojang.brigadier.context.CommandContext
import de.cancelcloud.advancedusercommands.state.PendingCopyManager
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource
import net.minecraft.text.Text
import net.minecraft.util.Formatting

class CancelCommand : Command<FabricClientCommandSource> {
    override fun run(context: CommandContext<FabricClientCommandSource>): Int {
        val hadPending = PendingCopyManager.hasPending()
        PendingCopyManager.clear()

        if (hadPending) {
            context.source.sendFeedback(
                Text.literal("❌ Copy canceled.")
                    .styled { it.withColor(Formatting.RED) }
            )
        } else {
            context.source.sendFeedback(
                Text.literal("❌ No pending copy to cancel.")
                    .styled { it.withColor(Formatting.RED) }
            )
        }

        return 1
    }
}
