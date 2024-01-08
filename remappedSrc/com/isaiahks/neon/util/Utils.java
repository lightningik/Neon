package com.isaiahks.neon.util;

import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.text.Text;

public class Utils {

    public static Integer addMessage( String message, FabricClientCommandSource source) {
        source.sendFeedback(Text.literal("[Neon] " + message));
        return 0;
    }


}
