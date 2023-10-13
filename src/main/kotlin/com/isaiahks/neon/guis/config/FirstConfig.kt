package com.isaiahks.neon.guis.config

import com.google.gson.annotations.Expose
import io.github.moulberry.moulconfig.annotations.ConfigEditorBoolean

import io.github.moulberry.moulconfig.annotations.ConfigOption




class FirstConfig {
    @Expose
    @ConfigOption(name = "First Toggle", desc = "Enable this toggle to activate a feature.")
    @ConfigEditorBoolean
    var firstToggle = false

    @Expose
    @ConfigOption(name = "Second Toggle", desc = "Enable this toggle to activate a different feature.")
    @ConfigEditorBoolean
    var secondToggle = false
}