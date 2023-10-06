package com.isaiahks.neon.client.config;

import io.github.cottonmc.cotton.gui.GuiDescription;
import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.client.ScreenDrawing;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WWidget;
import io.github.cottonmc.cotton.gui.widget.data.Insets;
import net.fabricmc.fabric.api.util.TriState;
import net.minecraft.client.gui.DrawContext;

public class BaseConfig extends WWidget {

    @Override
    public void paint(DrawContext draw, int x, int y, int mouseX, int mouseY) {
        ScreenDrawing.coloredRect(draw, x, y, 512, 256, 0);

    }


    public TriState isDarkMode() {
        return TriState.TRUE;
    }

}
