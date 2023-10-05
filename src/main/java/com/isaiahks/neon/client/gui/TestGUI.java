package com.isaiahks.neon.client.gui;

import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.client.ScreenDrawing;
import io.github.cottonmc.cotton.gui.widget.*;
import io.github.cottonmc.cotton.gui.widget.data.Axis;
import io.github.cottonmc.cotton.gui.widget.data.Insets;
import io.github.cottonmc.cotton.gui.widget.data.Texture;
import io.github.cottonmc.cotton.gui.widget.icon.TextureIcon;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.util.TriState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.function.IntConsumer;

public class TestGUI extends LightweightGuiDescription {
    private static final Identifier GUI = new Identifier("neon", "guibg");

    public TestGUI() {
        WGridPanel root = new WGridPanel();
        setRootPanel(root);
        root.setSize(512, 256);
        root.setInsets(Insets.ROOT_PANEL);


        root.validate(this);
    }

    @Override
    public TriState isDarkMode() {
        return TriState.TRUE;
    }





}
