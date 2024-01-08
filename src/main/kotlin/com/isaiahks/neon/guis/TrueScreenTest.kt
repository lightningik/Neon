package com.isaiahks.neon.guis


import com.isaiahks.neon.util.Utils
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiGraphics
import net.minecraft.client.gui.layouts.LinearLayout
import net.minecraft.client.gui.screens.Screen
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation
import net.minecraft.sounds.SoundEvent


class TrueScreenTest : Screen(Component.empty())  {
    companion object {
        private val GUIBg = ResourceLocation("neon:guibg.png")
    }


    override fun rebuildWidgets() {
        super.rebuildWidgets()
    }
    override fun init() {
        val screenWidth: Int = (this.width - 431) / 2
        val screenHeight: Int = (this.height - 202) / 2


        Utils.addMessage("Recieved")
        super.init()
    }

    override fun render(context: GuiGraphics, i: Int, j: Int, f: Float) {
        val matrices = context.matrixStack


        val screenWidth: Int = (this.width - 431) / 2
        val screenHeight: Int = (this.height - 202) / 2

        context.blit(GUIBg, screenWidth, screenHeight, 431, 202)
    }



    override fun mouseClicked(mouseX: Double, mouseY: Double, button: Int): Boolean {
        if (this.getChildAt(mouseX, mouseY).isEmpty) {
            focused = null
            return false
        }
        return super.mouseClicked(mouseX, mouseY, button)
    }

    override fun onClose() {
        Minecraft.getInstance().setScreen(null)
    }


}