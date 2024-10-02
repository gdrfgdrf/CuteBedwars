package io.github.gdrfgdrf.cutebedwars.game.editing.change.impl.area

import io.github.gdrfgdrf.cutebedwars.abstracts.editing.change.AbstractChange
import io.github.gdrfgdrf.cutebedwars.abstracts.game.management.area.IAreaContext
import io.github.gdrfgdrf.cutebedwars.beans.Convertible
import io.github.gdrfgdrf.cutebedwars.beans.pojo.area.Area
import io.github.gdrfgdrf.cutebedwars.beans.pojo.common.Status
import io.github.gdrfgdrf.cutebedwars.game.editing.change.annotation.Change
import io.github.gdrfgdrf.cutebedwars.game.editing.change.data.ChangeData
import io.github.gdrfgdrf.cutebedwars.game.editing.exception.ApplyException
import io.github.gdrfgdrf.cutebedwars.utils.extension.logInfo

@Change(
    "area-property-change",
    "io.github.gdrfgdrf.cutebedwars.abstracts.game.management.area.IAreaContext",
    2,
    2
)
class PropertyChange(
    private val key: String,
    private val value: Any?,
    name: String = "change $key to $value"
) : AbstractChange<IAreaContext>(name) {
    constructor(changeData: ChangeData) : this(changeData[0], changeData[1])

    private var previousValue: Any? = null

    override fun validate(): Boolean {
        return !(key != "name" &&
                key != "default-template-id" &&
                key != "world-name" &&
                key != "lobby-world-name")
    }

    override fun apply(t: IAreaContext) {
        if (!validate()) {
            throw ApplyException("area property change applies only to keys \"name\", \"default-template-id\", \"world-name\", \"lobby-world-name\"")
        }

        "Applying $key: $value to area, area's id: ${t.manager().area().id}".logInfo()

        val area = t.manager().area()
        val convertible = Convertible.of(Area::class.java)
        when (key) {
            "name" -> area.name = convertible.invoke(java.lang.String::class.java, value)
            "default-template-id" -> area.defaultTemplateId = convertible.invoke(java.lang.Long::class.java, value)
            "world-name" -> area.worldName = convertible.invoke(java.lang.String::class.java, value)
            "lobby-world-name" -> area.lobbyWorldName = convertible.invoke(java.lang.String::class.java, value)
        }
    }

    override fun makeUndo(): AbstractChange<IAreaContext> {
        val propertyChange = PropertyChange(key, previousValue, "change back $key from $value to $previousValue")
        propertyChange.previousValue = value
        return propertyChange
    }
}