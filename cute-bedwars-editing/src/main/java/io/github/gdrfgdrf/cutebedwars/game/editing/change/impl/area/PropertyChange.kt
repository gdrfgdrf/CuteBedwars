package io.github.gdrfgdrf.cutebedwars.game.editing.change.impl.area

import io.github.gdrfgdrf.cutebedwars.abstracts.editing.change.AbstractChange
import io.github.gdrfgdrf.cutebedwars.abstracts.game.management.area.IAreaContext
import io.github.gdrfgdrf.cutebedwars.abstracts.locale.ITranslationAgent
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.IBooleanConditions
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.logInfo
import io.github.gdrfgdrf.cutebedwars.game.editing.change.annotation.ChangeMetadataMethod
import io.github.gdrfgdrf.cutebedwars.game.editing.change.data.ChangeData
import io.github.gdrfgdrf.cutebedwars.game.editing.change.data.ChangeMetadata
import io.github.gdrfgdrf.cutebedwars.game.editing.exception.ApplyException
import io.github.gdrfgdrf.cutebedwars.languages.collect.EditorLanguage
import io.github.gdrfgdrf.cutebedwars.abstracts.locale.localizationScope
import org.bukkit.command.CommandSender

class PropertyChange(
    private val key: String,
    private val value: Any?,
) : AbstractChange<IAreaContext>() {
    constructor(changeData: ChangeData) : this(changeData[0], changeData[1]) {
        if (changeData.length() == 3) {
            previousValue = changeData[2]
        }
    }

    private var previousValue: Any? = null

    override fun validate(sender: CommandSender): Boolean {
        if (value == null) {
            return false
        }
        if (key != "name" &&
            key != "default-template-id"
        ) {
            return false
        }

        if (key == "default-template-id") {
            return IBooleanConditions.instance().onlyNumber(value)
        }
        return true
    }

    override fun apply(t: IAreaContext, sender: CommandSender) {
        if (!validate(sender)) {
            throw ApplyException("area property change applies only to keys \"name\", \"default-template-id\", \"world-name\", \"lobby-world-name\"")
        }

        "Applying $key: $value to area, area's id: ${t.manager.area.id}".logInfo()

        val area = t.manager.area
        when (key) {
            "name" -> {
                previousValue = area.name
                area.name = value.toString()
            }

            "default-template-id" -> {
                previousValue = area.defaultTemplateId
                area.defaultTemplateId = value.toString().toLong()
            }
        }
    }

    override fun makeUndo(): AbstractChange<IAreaContext> {
        val propertyChange = PropertyChange(key, previousValue)
        propertyChange.previousValue = value
        return propertyChange
    }

    override fun args(): Array<Any?> = arrayOf(key, value, previousValue)

    override fun name(): String {
        return "change $key from $previousValue to $value"
    }

    override fun localizedName(): (CommandSender) -> ITranslationAgent {
        return { sender ->
            localizationScope(sender) {
                message(EditorLanguage.AREA_PROPERTY_CHANGE_NAME)
                    .format0(key, previousValue ?: "null", value ?: "null")
            }
        }
    }

    companion object {
        @JvmStatic
        @ChangeMetadataMethod
        fun metadata(): ChangeMetadata {
            return ChangeMetadata(
                "area-property-change",
                IAreaContext::class.java,
                2..2,
                3..3,
                EditorLanguage::AREA_PROPERTY_CHANGE
            )
        }
    }
}