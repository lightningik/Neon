package com.isaiahks.neon


import com.isaiahks.neon.commands.stats
import com.isaiahks.neon.commands.suggest
import com.isaiahks.neon.guis.TrueScreenTest
import com.mojang.brigadier.CommandDispatcher
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource
import net.minecraft.client.Minecraft
import net.minecraft.commands.CommandBuildContext
import java.rmi.registry.Registry

object NeonClient : ClientModInitializer {



    override fun onInitializeClient() {
        ClientCommandRegistrationCallback.EVENT.register(ClientCommandRegistrationCallback { dispatcher: CommandDispatcher<FabricClientCommandSource>, commandRegistryAccess: CommandBuildContext? ->
            dispatcher.register(
                ClientCommandManager.literal("neon")
                    .then(
                        ClientCommandManager.literal("gui")
                            .executes {
                                Minecraft.getInstance().setScreen(TrueScreenTest())
                                0
                            }
                    )

                    )
            stats.registerStats(dispatcher)
            dispatcher.register(suggest.suggestLiteral())

        })
        System.setProperty("java.awt.headless", "false")
    }
}


