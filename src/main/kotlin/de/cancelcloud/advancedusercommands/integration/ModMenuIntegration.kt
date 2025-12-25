package de.cancelcloud.advancedusercommands.integration

import com.terraformersmc.modmenu.api.ConfigScreenFactory
import com.terraformersmc.modmenu.api.ModMenuApi
import de.cancelcloud.advancedusercommands.screen.ConfigScreen

class ModMenuIntegration : ModMenuApi {
    override fun getModConfigScreenFactory(): ConfigScreenFactory<*> {
        return ConfigScreenFactory { parent -> ConfigScreen.create(parent) }
    }
}
