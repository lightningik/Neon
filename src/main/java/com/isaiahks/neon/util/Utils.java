package com.isaiahks.neon.util;

import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;

public class Utils {

    public static Integer addMessage( String message) {
        Minecraft.getInstance().player.sendSystemMessage(Component.literal("[Neon] " + message));

        return 0;
    }


}
