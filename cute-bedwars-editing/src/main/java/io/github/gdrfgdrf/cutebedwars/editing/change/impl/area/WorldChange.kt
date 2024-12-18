package io.github.gdrfgdrf.cutebedwars.editing.change.impl.area

import io.github.gdrfgdrf.cutebedwars.abstracts.editing.change.AbstractChange
import io.github.gdrfgdrf.cutebedwars.abstracts.game.management.area.IAreaContext
import io.github.gdrfgdrf.cutebedwars.abstracts.locale.ITranslationAgent
import io.github.gdrfgdrf.cutebedwars.abstracts.locale.localizationScope
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.logInfo
import io.github.gdrfgdrf.cutebedwars.editing.change.annotation.ChangeMetadataMethod
import io.github.gdrfgdrf.cutebedwars.editing.change.data.ChangeData
import io.github.gdrfgdrf.cutebedwars.editing.change.data.ChangeMetadata
import io.github.gdrfgdrf.cutebedwars.editing.exception.ApplyException
import io.github.gdrfgdrf.cutebedwars.languages.collect.EditorLanguage
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class WorldChange(
    private val key: String,
    private var worldName: String?
) : AbstractChange<IAreaContext>() {
    constructor(changeData: ChangeData): this(
        changeData[0],
        changeData.getStringOrBlank(1)
    ) {
        if (changeData.length() == 3) {
            previousWorldName = changeData.getStringOrBlank(2)
        }
    }

    private var previousWorldName: String? = null

    override fun preload(sender: CommandSender): Boolean {
        if (sender !is Player) {
            return true
        }
        if (worldName.isNullOrBlank()) {
            worldName = sender.world.name
        }
        return true
    }

    override fun validate(sender: CommandSender): Boolean {
        if (sender !is Player) {
            return false
        }
        if (worldName == null) {
            return false
        }
        if (key != "world-name" &&
            key != "lobby-world-name") {
            return false
        }

        Bukkit.getWorld(worldName) ?: return false

        return true
    }

    override fun makeUndo(): AbstractChange<IAreaContext> {
        val worldChange = WorldChange(key, previousWorldName)
        worldChange.previousWorldName = worldName
        return worldChange
    }

    override fun args(): Array<Any?> {
        return arrayOf(key, worldName, previousWorldName)
    }

    override fun name(): String {
        return "change world $key from $previousWorldName to $worldName"
    }

    override fun localizedName(): (CommandSender) -> ITranslationAgent {
        return { sender ->
            localizationScope(sender) {
                message(EditorLanguage.AREA_WORLD_CHANGE_NAME)
                    .format0(key, previousWorldName ?: "null", worldName ?: "null")
            }
        }
    }

    override fun apply(t: IAreaContext, sender: CommandSender) {
        if (!validate(sender)) {
            throw ApplyException("fail to validate the world change")
        }

        "Applying world $key: $worldName to area, area's id: ${t.manager.area.id}".logInfo()

        val area = t.manager.area
        when (key) {
            "world-name" -> {
                previousWorldName = area.worldName
                area.worldName = worldName
            }
            "lobby-world-name" -> {
                previousWorldName = area.lobbyWorldName
                area.lobbyWorldName = worldName
            }
        }
    }

    companion object {
        @JvmStatic
        @ChangeMetadataMethod
        fun metadata(): ChangeMetadata {
            return ChangeMetadata(
                "area-world-change",
                IAreaContext::class.java,
                1..2,
                3..3,
                EditorLanguage::AREA_WORLD_CHANGE
            )
        }

    }
}