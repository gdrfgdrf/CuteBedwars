package io.github.gdrfgdrf.cutebedwars.game.editing.change.impl.game

import io.github.gdrfgdrf.cutebedwars.abstracts.editing.change.AbstractChange
import io.github.gdrfgdrf.cutebedwars.abstracts.game.management.game.IGameContext
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
) : AbstractChange<IGameContext>() {
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
            key != "min-player" &&
            key != "max-player"
        ) {
            return false
        }

        if (key == "min-player" || key == "max-player") {
            return IBooleanConditions.instance().onlyNumber(value)
        }
        return true
    }

    override fun apply(t: IGameContext, sender: CommandSender) {
        if (!validate(sender)) {
            throw ApplyException("game property change applies only to keys \"name\", \"min-player\", \"max-player\"")
        }

        "Applying $key: $value to game, game's id: ${t.game.id}, area's id: ${t.game.areaId}".logInfo()

        val game = t.game
        when (key) {
            "name" -> {
                previousValue = game.name
                game.name = value.toString()
            }
            "min-player" -> {
                previousValue = game.minPlayer
                game.minPlayer = value.toString().toInt()
            }
            "max-player" -> {
                previousValue = game.maxPlayer
                game.maxPlayer = value.toString().toInt()
            }
        }
    }

    override fun makeUndo(): AbstractChange<IGameContext> {
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
                message(EditorLanguage.GAME_PROPERTY_CHANGE_NAME)
                    .format0(key, previousValue ?: "null", value ?: "null")
            }
        }
    }

    companion object {
        @JvmStatic
        @ChangeMetadataMethod
        fun metadata(): ChangeMetadata {
            return ChangeMetadata(
                "game-property-change",
                IGameContext::class.java,
                2..2,
                2..3,
                EditorLanguage::GAME_PROPERTY_CHANGE
            )
        }
    }
}