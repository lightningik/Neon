package com.isaiahks.neon


import com.isaiahks.neon.commands.stats
import com.isaiahks.neon.guis.TrueScreenTest
import com.isaiahks.neon.guis.config.NeonConfig
import com.mojang.brigadier.CommandDispatcher
import io.github.notenoughupdates.moulconfig.managed.ManagedConfig
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource
import net.minecraft.client.Minecraft
import net.minecraft.commands.CommandBuildContext
import java.io.File

object NeonClient : ClientModInitializer {



    override fun onInitializeClient() {
        val config = ManagedConfig.create(File("config/neon/config.json"), NeonConfig::class.java)
        ClientCommandRegistrationCallback.EVENT.register(ClientCommandRegistrationCallback { dispatcher: CommandDispatcher<FabricClientCommandSource>, commandRegistryAccess: CommandBuildContext? ->
            dispatcher.register(
                ClientCommandManager.literal("neon")
                    .then(
                        ClientCommandManager.literal("gui")
                            .executes {
                                Minecraft.getInstance().run {
                                    Minecraft.getInstance().setScreen(TrueScreenTest())
                                }
                                0
                            }
                        )
                    .then(
                        ClientCommandManager.literal("config")
                            .executes {
                                Minecraft.getInstance().run {
                                    config.openConfigGui()
                                }
                                0
                            }

                    )
                    )
            stats.registerStats(dispatcher)


        })
        System.setProperty("java.awt.headless", "false")
    }


}


