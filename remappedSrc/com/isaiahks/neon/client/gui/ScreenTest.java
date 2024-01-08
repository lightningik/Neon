package com.isaiahks.neon.client.gui;

import com.mojang.authlib.minecraft.client.MinecraftClient;
import com.mojang.brigadier.Message;
import io.wispforest.owo.ui.base.BaseUIModelScreen;
import io.wispforest.owo.ui.component.Components;
import io.wispforest.owo.ui.container.Containers;
import io.wispforest.owo.ui.container.FlowLayout;
import io.wispforest.owo.ui.core.*;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ScreenTest extends BaseUIModelScreen<FlowLayout> {

    public ScreenTest() {
        super(FlowLayout.class, DataSource.asset(new Identifier("neon", "gui")));
    }

    @Override
    protected void build(FlowLayout rootComponent) {
        client.player.sendMessage(Text.literal("Recieved"));
        // TODO

    }

}
