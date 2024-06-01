package com.isaiahks.neon.commands

import com.isaiahks.neon.util.Utils
import com.isaiahks.neon.util.WebhookBuilder
import com.mojang.brigadier.arguments.StringArgumentType
import com.mojang.brigadier.arguments.StringArgumentType.string
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.argument
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource
import java.awt.Color

object suggest {

    fun sendSuggestion(suggestion: String, player: String, source: FabricClientCommandSource): Int {
        val builder = WebhookBuilder("https://discord.com/api/webhooks/1193401259143737434/65F8vYkdba10Y2BdJ9gvxpmoNLu-ZwEZGnCx29Hp5OJ8X7tKzLXSMtfd022ahInbOhaR")

        builder.addEmbed(
            WebhookBuilder.EmbedObject()
            .setAuthor(player, null, "https://minotar.net/avatar/$player.png")
            .setDescription(suggestion)
                .setColor(Color.magenta.brighter())
        )
        builder.execute()

        return Utils.addMessage("Successfully sent Suggestion")
    }

    fun suggestLiteral() = literal("neonsuggest")
        .then(
            argument("suggestion", string())
                .executes { it ->
                    val result: String = StringArgumentType.getString(it, "suggestion")
                    it.sendSuggestion(result, it.source.player.name.string, it.source)
                }

        )

}