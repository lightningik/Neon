package com.isaiahks.neon.guis.config

import com.google.gson.annotations.Expose
import io.github.notenoughupdates.moulconfig.Config
import io.github.notenoughupdates.moulconfig.annotations.Category

class NeonConfig : Config() {
    override fun getTitle(): String {
        return "Neon Config"
    }

    @JvmField
    @Expose
    @Category(name="Test Category", desc="test")
    var testCategory: TestCategory = TestCategory()
}