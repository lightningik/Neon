package com.isaiahks.neon.guis.config

import com.google.gson.annotations.Expose
import com.isaiahks.neon.Neon
import io.github.moulberry.moulconfig.Config
import io.github.moulberry.moulconfig.annotations.Category
import com.isaiahks.neon.guis.config.FirstConfig

class NeonConfig : Config() {

    override fun getTitle(): String {
        return ("Neon 1.0-BETA")
    }



    @Expose
    @Category(name = "First Category", desc = "This is the first category.")
    var firstCategory: FirstConfig = FirstConfig()

    @Expose
    @Category(name = "Second Category", desc = "This is another category.")
    var secondCategory: FirstConfig = FirstConfig()
}