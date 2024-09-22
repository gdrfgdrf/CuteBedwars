package io.github.gdrfgdrf.cutebedwars.game.editing.change.impl.area

import io.github.gdrfgdrf.cutebedwars.abstracts.game.management.area.IAreaContext
import io.github.gdrfgdrf.cutebedwars.beans.pojo.common.Coordinate
import io.github.gdrfgdrf.cutebedwars.beans.pojo.common.Status
import io.github.gdrfgdrf.cutebedwars.abstracts.game.editing.change.AbstractChange
import io.github.gdrfgdrf.cutebedwars.game.editing.change.annotation.Change
import io.github.gdrfgdrf.cutebedwars.game.editing.change.data.ChangeData
import io.github.gdrfgdrf.cutebedwars.game.editing.exception.ApplyException
import io.github.gdrfgdrf.cutebedwars.utils.extension.logInfo

@Change("area-property-change")
class PropertyChange(
    private val key: String,
    private val value: Any?,
    name: String = "change $key to $value"
) : AbstractChange<IAreaContext>(name) {
    constructor(changeData: ChangeData): this(changeData[0], changeData[1])

    private var previousValue: Any? = null

    override fun apply(t: IAreaContext) {
        if (key != "default-template-id" &&
            key != "status" &&
            key != "world-name" &&
            key != "lobby-world-name" &&
            key != "lobby-spawnpoint-coordinate") {
            throw ApplyException("property change applies only to keys \"default-template-id\", \"status\", \"world-name\", \"lobby-world-name\", \"lobby-spawnpoint-coordinate\"")
        }

        "Applying $key: $value to area, area's id: ${t.manager().area().id}".logInfo()

        val area = t.manager().area()
        when (key) {
            "default-template-id" -> area.defaultTemplateId = area.convert(java.lang.Long::class.java, value)
            "status" -> area.status = area.convert(Status::class.java, value)
            "world-name" -> area.worldName = area.convert(java.lang.String::class.java, value)
            "lobby-world-name" -> area.lobbyWorldName = area.convert(java.lang.String::class.java, value)
            "lobby-spawnpoint-coordinate" -> area.lobbySpawnpointCoordinate = area.convert(Coordinate::class.java, value)
        }
    }

    override fun makeUndo(): AbstractChange<IAreaContext> {
        val propertyChange = PropertyChange(key, previousValue, "change back $key from $value to $previousValue")
        propertyChange.previousValue = value
        return propertyChange
    }
}