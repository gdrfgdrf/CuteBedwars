package io.github.gdrfgdrf.cutebedwars.game.editing.change.impl.game

import io.github.gdrfgdrf.cutebedwars.abstracts.game.management.game.IGameContext
import io.github.gdrfgdrf.cutebedwars.beans.pojo.common.Coordinate
import io.github.gdrfgdrf.cutebedwars.beans.pojo.common.Status
import io.github.gdrfgdrf.cutebedwars.abstracts.editing.change.AbstractChange
import io.github.gdrfgdrf.cutebedwars.game.editing.change.annotation.Change
import io.github.gdrfgdrf.cutebedwars.game.editing.change.data.ChangeData
import io.github.gdrfgdrf.cutebedwars.game.editing.exception.ApplyException
import io.github.gdrfgdrf.cutebedwars.utils.extension.logInfo

@Change("game-property-change")
class PropertyChange(
    private val key: String,
    private val value: Any?,
    name: String = "change $key to $value"
) : AbstractChange<IGameContext>(name) {
    constructor(changeData: ChangeData): this(changeData[0], changeData[1])

    private var previousValue: Any? = null

    override fun apply(t: IGameContext) {
        if (key != "status" &&
            key != "min-player" &&
            key != "max-player" &&
            key != "spectator-spawnpoint-coordinate") {
            throw ApplyException("property change applies only to keys \"status\", \"min-player\", \"max-player\", \"spectator-spawnpoint-coordinate\"")
        }

        "Applying $key: $value to game, game's id: ${t.game().id}, area's id: ${t.game().areaId}".logInfo()

        val game = t.game()
        when (key) {
            "status" -> game.status = game.convert(Status::class.java, value)
            "min-player" -> game.minPlayer = game.convert(java.lang.Integer::class.java, value)
            "max-player" -> game.maxPlayer = game.convert(java.lang.Integer::class.java, value)
            "spectator-spawnpoint-coordinate" -> game.spectatorSpawnpointCoordinate =
                game.convert(Coordinate::class.java, value)
        }
    }

    override fun makeUndo(): AbstractChange<IGameContext> {
        val propertyChange = PropertyChange(key, previousValue, "change back $key from $value to $previousValue")
        propertyChange.previousValue = value
        return propertyChange
    }
}