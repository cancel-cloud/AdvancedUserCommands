package de.cancelcloud.advancedusercommands

import de.cancelcloud.advancedusercommands.command.CancelCommand
import de.cancelcloud.advancedusercommands.command.ConfirmCommand
import de.cancelcloud.advancedusercommands.command.CopyNBTCommand
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback

class AdvancedUserCommandsMod : ClientModInitializer {
    override fun onInitializeClient() {
        // Register all client commands
        ClientCommandRegistrationCallback.EVENT.register { dispatcher, _ ->
            // Register /copynbt with subcommands
            val copyNbtNode = ClientCommandManager.literal("copynbt")
                .executes(CopyNBTCommand())
                .build()

            // Add subcommands
            copyNbtNode.addChild(
                ClientCommandManager.literal("confirm")
                    .executes(ConfirmCommand())
                    .build()
            )

            copyNbtNode.addChild(
                ClientCommandManager.literal("cancel")
                    .executes(CancelCommand())
                    .build()
            )

            dispatcher.root.addChild(copyNbtNode)
        }

        // Log successful initialization
        println("[Advanced User Commands] Mod initialized")
    }
}
