package io.github.gdrfgdrf.cutebedwars.editing.change.impl.game

import io.github.gdrfgdrf.cutebedwars.abstracts.editing.change.AbstractChange
import io.github.gdrfgdrf.cutebedwars.abstracts.game.management.game.IGameContext
import io.github.gdrfgdrf.cutebedwars.abstracts.locale.ITranslationAgent
import io.github.gdrfgdrf.cutebedwars.abstracts.locale.localizationScope
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.logInfo
import io.github.gdrfgdrf.cutebedwars.beans.pojo.common.Coordinate
import io.github.gdrfgdrf.cutebedwars.editing.change.annotation.ChangeMetadataMethod
import io.github.gdrfgdrf.cutebedwars.editing.change.data.ChangeData
import io.github.gdrfgdrf.cutebedwars.editing.change.data.ChangeMetadata
import io.github.gdrfgdrf.cutebedwars.editing.exception.ApplyException
import io.github.gdrfgdrf.cutebedwars.languages.collect.EditorLanguage
import org.bukkit.command.CommandSender

class CoordinateChange(
    val key: String,
    val x: String?,
    val y: String?,
    val z: String?
) : AbstractChange<IGameContext>() {
    private var previousCoordinateX: String? = null
    private var previousCoordinateY: String? = null
    private var previousCoordinateZ: String? = null

    constructor(changeData: ChangeData): this(
        changeData[0],
        changeData.getStringOrBlank(1),
        changeData.getStringOrBlank(2),
        changeData.getStringOrBlank(3)
    ) {
        if (changeData.length() == 7) {
            previousCoordinateX = changeData.getStringOrBlank(4)
            previousCoordinateY = changeData.getStringOrBlank(5)
            previousCoordinateZ = changeData.getStringOrBlank(6)
        }
    }

    override fun validate(sender: CommandSender): Boolean {
        if (key.isBlank()) {
            return false
        }
        if (key != "spectator-spawnpoint-coordinate") {
            return false
        }
        runCatching {
            Coordinate.tryParse(x, y, z)
        }.onFailure {
            return false
        }

        return true
    }

    override fun makeUndo(): AbstractChange<IGameContext> {
        val coordinateChange = CoordinateChange(
            key,
            previousCoordinateX,
            previousCoordinateY,
            previousCoordinateZ
        )
        coordinateChange.previousCoordinateX = x
        coordinateChange.previousCoordinateY = y
        coordinateChange.previousCoordinateZ = z
        return coordinateChange
    }

    override fun args(): Array<Any?> {
        val coordinate = Coordinate.tryParse(x, y, z)
        val x = coordinate?.x ?: ""
        val y = coordinate?.y ?: ""
        val z = coordinate?.z ?: ""

        return arrayOf(
            key,
            x,
            y,
            z,
            previousCoordinateX,
            previousCoordinateY,
            previousCoordinateZ
        )
    }

    override fun name(): String {
        val coordinate = Coordinate.tryParse(x, y ,z)
        val previousCoordinate = Coordinate.tryParse(previousCoordinateX, previousCoordinateY, previousCoordinateZ)
        return "change coordinate $key from $previousCoordinate to $coordinate"
    }

    override fun localizedName(): (CommandSender) -> ITranslationAgent {
        return { sender ->
            val coordinate = Coordinate.tryParse(x, y ,z)
            val previousCoordinate = Coordinate.tryParse(previousCoordinateX, previousCoordinateY, previousCoordinateZ)
            localizationScope(sender) {
                message(EditorLanguage.GAME_COORDINATE_CHANGE_NAME)
                    .format0(key, previousCoordinate ?: "null", coordinate ?: "null")
            }
        }
    }

    override fun apply(t: IGameContext, sender: CommandSender) {
        if (!validate(sender)) {
            throw ApplyException("cannot validate key or coordinate")
        }
        val coordinate = Coordinate.tryParse(x, y ,z)
        "Applying coordinate $key: $coordinate to game, game's id: ${t.game.id}, area's id: ${t.game.areaId}".logInfo()

        val game = t.game
        when (key) {
            "spectator-spawnpoint-coordinate" -> {
                previousCoordinateX = game.spectatorSpawnpointCoordinate?.x.toString()
                previousCoordinateY = game.spectatorSpawnpointCoordinate?.y.toString()
                previousCoordinateZ = game.spectatorSpawnpointCoordinate?.z.toString()
                game.spectatorSpawnpointCoordinate = coordinate
            }
        }
    }

    companion object {
        @JvmStatic
        @ChangeMetadataMethod
        fun metadata(): ChangeMetadata {
            return ChangeMetadata(
                "game-coordinate-change",
                IGameContext::class.java,
                4..4,
                7..7,
                EditorLanguage::GAME_COORDINATE_CHANGE
            )
        }
    }
}