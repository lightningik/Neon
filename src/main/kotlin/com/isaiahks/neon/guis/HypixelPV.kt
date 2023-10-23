package com.isaiahks.neon.guis

import io.github.moulberry.moulconfig.gui.GuiComponent
import io.github.moulberry.moulconfig.xml.Bind
import io.github.moulberry.moulconfig.xml.XMLUniverse
import net.minecraft.client.MinecraftClient
import net.minecraft.client.gui.screen.Screen
import net.minecraft.util.Identifier

class HypixelPV(
    val xmlUniverse: XMLUniverse
) {


    fun open(): GuiComponent {
        return xmlUniverse.load(this, MinecraftClient.getInstance().resourceManager.open(
            Identifier("moulconfig:test.xml")
        )
        )
    }
}