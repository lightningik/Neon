package com.isaiahks.neon.guis

import com.isaiahks.neon.commands.stats
import com.isaiahks.neon.commands.stats.clipboardAndSendMessage
import com.isaiahks.neon.guis.config.NeonConfig
import com.isaiahks.neon.util.DiscordMarkdownBuilder
import com.mojang.brigadier.CommandDispatcher
import io.github.moulberry.moulconfig.common.IItemStack
import io.github.moulberry.moulconfig.gui.GuiContext
import io.github.moulberry.moulconfig.gui.GuiScreenElementWrapper
import io.github.moulberry.moulconfig.gui.MoulConfigEditor
import io.github.moulberry.moulconfig.observer.ObservableList
import io.github.moulberry.moulconfig.processor.MoulConfigProcessor
import io.github.moulberry.moulconfig.xml.Bind
import io.github.moulberry.moulconfig.xml.XMLUniverse
import io.github.notenoughupdates.moulconfig.gui.GuiComponentWrapper
import io.github.notenoughupdates.moulconfig.platform.ModernItemStack
import io.github.notenoughupdates.moulconfig.test.FabricMain
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource
import net.minecraft.client.MinecraftClient
import net.minecraft.item.ItemStack
import net.minecraft.registry.Registries
import net.minecraft.util.Identifier

object MoulConfigTest {
    fun MoulConfigTest(): Int {
        MinecraftClient.getInstance().send {
            val xmlUniverse = XMLUniverse.getDefaultUniverse()
            val scene = xmlUniverse.load(
                ObjectBound(), MinecraftClient.getInstance().resourceManager.open(
                    Identifier("moulconfig:test.xml")
                )
            )
            MinecraftClient.getInstance().setScreen(GuiComponentWrapper(GuiContext(scene)))
        }
        return 0
    }

    class ObjectBound {
        @field:Bind("sliderLol")
        var slider: Float = 0F

        @field:Bind
        var data: ObservableList<IItemStack> = ObservableList(mutableListOf())

        @field:Bind
        var search: String = ""

        @Bind
        fun click() {
            data.add(ModernItemStack.of(ItemStack(Registries.ITEM.entrySet.random().value)))
        }
    }

    fun neoncfg() = ClientCommandManager.literal("neon")
        .executes {
            MoulConfigTest()
        }
        .then(
            ClientCommandManager.literal("cfg")
                .executes {
                    lateinit var processor: MoulConfigProcessor<NeonConfig>
                    val editor by lazy { MoulConfigEditor(processor) }
                    GuiScreenElementWrapper(editor)
                }
        )



    fun registerConfig(dispatcher: CommandDispatcher<FabricClientCommandSource>) {
        val stats = dispatcher.register(neoncfg())
    }
}