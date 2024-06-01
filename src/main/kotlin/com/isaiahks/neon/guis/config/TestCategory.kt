package com.isaiahks.neon.guis.config

import com.google.gson.annotations.Expose
import io.github.notenoughupdates.moulconfig.annotations.ConfigEditorBoolean
import io.github.notenoughupdates.moulconfig.annotations.ConfigOption

class TestCategory {
    @JvmField
    @Expose
    @ConfigOption(name="Test Option", desc="Test Toggle")
    @ConfigEditorBoolean
    var testToggle: Boolean = false
}