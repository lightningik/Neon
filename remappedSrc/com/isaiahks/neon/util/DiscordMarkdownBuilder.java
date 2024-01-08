package com.isaiahks.neon.util;

// ALL CREDITS GO TO NEU FOR THIS
public class DiscordMarkdownBuilder {
    private final StringBuilder builder;

    public DiscordMarkdownBuilder() {
        this.builder = new StringBuilder();
        this.builder.append("```md\n");
    }

    public DiscordMarkdownBuilder category(String name) {
        builder.append("# ").append(name).append("\n");
        return this;
    }

    public DiscordMarkdownBuilder append(String key, Object value) {
        if (!key.isEmpty()) {
            builder.append("[").append(key).append("]");
        }
        builder.append("[").append(value).append("]").append("\n");
        return this;
    }

    @Override
    public String toString() {
        return builder.append("```").toString();
    }
}
