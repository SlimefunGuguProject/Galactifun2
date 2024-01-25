package io.github.addoncommunity.galactifun.core.managers

import io.github.addoncommunity.galactifun.api.objects.planet.PlanetaryWorld
import io.github.addoncommunity.galactifun.pluginInstance
import io.github.addoncommunity.galactifun.runOnNextTick
import org.bukkit.World
import org.bukkit.configuration.file.YamlConfiguration
import java.io.File

object WorldManager {

    private val spaceWorlds = mutableMapOf<World, PlanetaryWorld>()

    private val config: YamlConfiguration
    private val defaultConfig: YamlConfiguration

    init {
        val configFile = File(pluginInstance.dataFolder, "worlds.yml")
        config = YamlConfiguration()
        defaultConfig = YamlConfiguration()
        config.setDefaults(defaultConfig)

        // Load the config
        if (configFile.exists()) {
            try {
                config.load(configFile)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        pluginInstance.runOnNextTick {
            config.options().copyDefaults(true)
            config.save(configFile)
        }
    }

    fun registerWorld(world: PlanetaryWorld) {
        spaceWorlds[world.world] = world
    }
}