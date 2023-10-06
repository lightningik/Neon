package com.isaiahks.neon.client;

import com.isaiahks.neon.client.config.Config;
import com.isaiahks.neon.client.gui.TestGUI;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;

import io.github.cottonmc.cotton.gui.client.CottonClientScreen;
import io.github.cottonmc.cotton.gui.client.CottonHud;
import io.github.cottonmc.cotton.gui.client.CottonInventoryScreen;
import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.impl.modmenu.ConfigGui;
import io.github.cottonmc.cotton.gui.widget.WLabel;

import java.util.function.Function;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.argument;
import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class NeonClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        ClientCommandRegistrationCallback.EVENT.register((dispatcher, commandRegistryAccess) -> dispatcher.register(
                literal("neon")
                        .executes(openScreen(client -> new Config()))
                        .then(literal("gui").executes(openScreen(client -> new TestGUI())))

        ));
    }

    private static Command<FabricClientCommandSource> openScreen(Function<MinecraftClient, LightweightGuiDescription> screenFactory) {
        return openScreen(ScreenTexts.EMPTY, screenFactory);
    }

    private static Command<FabricClientCommandSource> openScreen(Text title, Function<MinecraftClient, LightweightGuiDescription> screenFactory) {
        return context -> {
            var client = context.getSource().getClient();
            client.send(() -> client.setScreen(new CottonClientScreen(title, screenFactory.apply(client))));
            return Command.SINGLE_SUCCESS;
        };
    }



}
