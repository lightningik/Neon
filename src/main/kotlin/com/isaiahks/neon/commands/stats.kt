package com.isaiahks.neon.commands

import com.isaiahks.neon.util.DiscordMarkdownBuilder
import com.mojang.blaze3d.platform.GLX
import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.context.CommandContext
import com.sun.jna.Platform
import com.sun.management.UnixOperatingSystemMXBean
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource
import net.fabricmc.loader.api.FabricLoader
import net.minecraft.client.MinecraftClient
import net.minecraft.text.Text
import net.minecraft.util.Formatting
import org.lwjgl.glfw.GLFW.glfwGetPrimaryMonitor
import org.lwjgl.glfw.GLFW.glfwGetVideoMode
import org.lwjgl.opengl.GL11
import java.awt.Toolkit
import java.awt.datatransfer.StringSelection
import java.lang.management.ManagementFactory
import javax.management.JMX
import javax.management.ObjectName


object stats {





    interface DiagnosticCommandMXBean {
        fun gcClassHistogram(array: Array<String>): String
    }

    private fun generateDataUsage(): String {
        val server = ManagementFactory.getPlatformMBeanServer()
        val objectName = ObjectName.getInstance("com.sun.management:type=DiagnosticCommand")
        val proxy = JMX.newMXBeanProxy(
                server,
                objectName,
                DiagnosticCommandMXBean::class.java
        )
        return proxy.gcClassHistogram(emptyArray()).replace("[", "[]")
    }


    private fun getMemorySize(): Long {
        try {
            return Runtime.getRuntime().totalMemory()
        } catch (e: java.lang.Exception) {
            try {
                return (ManagementFactory.getOperatingSystemMXBean() as UnixOperatingSystemMXBean).totalMemorySize
            } catch (ignored: java.lang.Exception) { /*IGNORE*/
            }
        }
        return -1
    }

    val ONE_MB = 1024L * 1024L
    private fun appendStats(builder: DiscordMarkdownBuilder) {
        val vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor())
        val maxMemory = Runtime.getRuntime().maxMemory()
        val totalMemory = Runtime.getRuntime().totalMemory()
        val freeMemory = Runtime.getRuntime().freeMemory()
        val currentMemory = totalMemory - freeMemory
        builder.category("System Stats")
        builder.append("OS", System.getProperty("os.name"))
        builder.append("CPU", GLX._getCpuInfo())
        builder.append(
                "Display",
                String.format("%dx%d (%s)", vidmode?.width(), vidmode?.height(), GL11.glGetString(GL11.GL_VENDOR))
        )
        builder.append("GPU", GL11.glGetString(GL11.GL_RENDERER))
        builder.append("GPU Driver", GL11.glGetString(GL11.GL_VERSION))
        if (getMemorySize() > 0)
            builder.append(
                    "Maximum Memory",
                    "${getMemorySize() / ONE_MB}MB"
            )
        builder.category("Java Stats")
        builder.append(
                "Java",
                "${System.getProperty("java.version")} ${if (MinecraftClient.getInstance().is64Bit) 64 else 32}bit",
        )
        builder.append(
                "Memory", String.format(
                "% 2d%% %03d/%03dMB",
                currentMemory * 100L / maxMemory,
                currentMemory / ONE_MB,
                maxMemory / ONE_MB
        )
        )
        builder.append(
                "Memory Allocated",
                String.format("% 2d%% %03dMB", totalMemory * 100L / maxMemory, totalMemory / ONE_MB)
        )
        builder.category("Game Stats")
        builder.append("FPS", MinecraftClient.getInstance().fpsDebugString.toString())
        builder.append("Loaded Mods", FabricLoader.getInstance().allMods.size)
        // builder.append("Optifine", if (FabricLoader.getInstance().) "TRUE" else "FALSE")
    }

    private fun appendModList(builder: DiscordMarkdownBuilder): DiscordMarkdownBuilder {
        builder.category("Mods Loaded")
        for (container in FabricLoader.getInstance().allMods) {
            val modID = container.metadata.id
            val name = Formatting.strip(container.metadata.name)
            val version = container.metadata.version.friendlyString
            if (name?.contains("fabric", true) == true)
            {
                continue
            } else {
                builder.append(modID, ("$name $version"))
            }

        }
        return builder
    }


    fun CommandContext<FabricClientCommandSource>.clipboardAndSendMessage(data: String?): Int {
        if (data == null) {
            source.sendError(Text.literal("Error occurred trying to perform command."))
            return 1
        }
        try {
            val clipboard = StringSelection(data)
            if (Platform.isWindows() || Platform.isLinux()) {
                Toolkit.getDefaultToolkit().systemClipboard.setContents(clipboard, null)
            } else {
                MinecraftClient.getInstance().keyboard.clipboard = data

            }

            source.sendFeedback(Text.literal("Dev info copied to clipboard."))
        } catch (ignored: Exception) {
            source.sendError(Text.literal("Could not copy to clipboard."))
            ignored.printStackTrace()
        }
        return 0
    }


    fun neonstats() = literal("neonstats")
        .executes { it ->
            it.clipboardAndSendMessage(
                DiscordMarkdownBuilder()
                    .also(::appendStats)
                    .also {
                        appendModList(it)
                    }
                    .toString()
            )
            return@executes 0
        }

    fun registerStats(dispatcher: CommandDispatcher<FabricClientCommandSource>) {
        val stats = dispatcher.register(neonstats())
    }




}
