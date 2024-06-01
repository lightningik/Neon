package com.isaiahks.neon.guis.config

import io.github.notenoughupdates.moulconfig.annotations.ConfigEditorBoolean
import io.github.notenoughupdates.moulconfig.annotations.ConfigOption

class TestCategory {
    @ConfigOption(name="Test Option", desc="Test Toggle")
    @ConfigEditorBoolean
    var TestToggle: Boolean = false
}