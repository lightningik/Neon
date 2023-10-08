package com.isaiahks.neon.client.config;

import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WTabPanel;
import net.fabricmc.fabric.api.util.TriState;
import net.minecraft.text.Text;

public class Config extends LightweightGuiDescription {

    @Override
    public TriState isDarkMode() {
        return TriState.TRUE;
    }

    public Config() {
        WTabPanel root = new WTabPanel();
        root.add(new BaseConfig(), builder -> builder.title(Text.literal("Home")));

        root.setSize(512, 256);

        setRootPanel(root);
        getRootPanel().validate(this);


    }
}
