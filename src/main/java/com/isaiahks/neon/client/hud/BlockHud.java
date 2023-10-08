package com.isaiahks.neon.client.hud;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;

import io.github.cottonmc.cotton.gui.client.ScreenDrawing;
import io.github.cottonmc.cotton.gui.widget.WWidget;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Environment(EnvType.CLIENT)
public class BlockHud extends WWidget {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void paint(DrawContext context, int x, int y, int mouseX, int mouseY) {
        ScreenDrawing.coloredRect(context, x, y, width, height, 0xFF_00FF00);
        ScreenDrawing.drawString(context, "Test Text", x, y, 0);
    }

    @Override
    public void tick() {
        LOGGER.debug("tick!");
    }
}
