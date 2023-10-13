package com.isaiahks.neon


import com.isaiahks.neon.client.gui.TestGUI
import com.isaiahks.neon.client.hud.BlockHud
import com.isaiahks.neon.commands.stats
import com.isaiahks.neon.guis.MoulConfigTest
import com.mojang.brigadier.Command
import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.context.CommandContext
import io.github.cottonmc.cotton.gui.client.CottonClientScreen
import io.github.cottonmc.cotton.gui.client.CottonHud
import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource
import net.minecraft.client.MinecraftClient
import net.minecraft.command.CommandRegistryAccess
import net.minecraft.screen.ScreenTexts
import net.minecraft.text.Text
import java.util.function.Function

object NeonClient : ClientModInitializer {
    override fun onInitializeClient() {
        ClientCommandRegistrationCallback.EVENT.register(ClientCommandRegistrationCallback { dispatcher: CommandDispatcher<FabricClientCommandSource>, commandRegistryAccess: CommandRegistryAccess? ->
            dispatcher.register(
                ClientCommandManager.literal("neon")
                    .then(
                        ClientCommandManager.literal("gui")
                            .executes(openScreen { TestGUI() })
                    )

            )
            stats.registerStats(dispatcher)
            MoulConfigTest.registerConfig(dispatcher)
        })
        CottonHud.add(BlockHud(), 10, -20, 10, 10)

    }


    private fun openScreen(screenFactory: Function<MinecraftClient, LightweightGuiDescription>): Command<FabricClientCommandSource> {
            return openScreen(ScreenTexts.EMPTY, screenFactory)
        }

    private fun openScreen(
            title: Text,
            screenFactory: Function<MinecraftClient, LightweightGuiDescription>
        ): Command<FabricClientCommandSource> {
            return Command { context: CommandContext<FabricClientCommandSource> ->
                val client = context.source.client
                client.send(Runnable {
                    client.setScreen(
                        CottonClientScreen(
                            title,
                            screenFactory.apply(client)
                        )
                    )
                })
                Command.SINGLE_SUCCESS
            }
        }
    }

