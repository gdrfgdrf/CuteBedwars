package io.github.gdrfgdrf.cutebedwars.game.editing.change.impl.game

import io.github.gdrfgdrf.cutebedwars.abstracts.editing.change.AbstractChange
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IItems
import io.github.gdrfgdrf.cutebedwars.abstracts.game.management.game.IGameContext
import io.github.gdrfgdrf.cutebedwars.abstracts.locale.ITranslationAgent
import io.github.gdrfgdrf.cutebedwars.abstracts.locale.localizationScope
import io.github.gdrfgdrf.cutebedwars.beans.pojo.common.Coordinate
import io.github.gdrfgdrf.cutebedwars.beans.pojo.common.Region
import io.github.gdrfgdrf.cutebedwars.game.editing.change.annotation.ChangeMetadataMethod
import io.github.gdrfgdrf.cutebedwars.game.editing.change.data.ChangeData
import io.github.gdrfgdrf.cutebedwars.game.editing.change.data.ChangeMetadata
import io.github.gdrfgdrf.cutebedwars.languages.collect.EditorLanguage
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class RegionChange(val pos1: String?, val pos2: String?) : AbstractChange<IGameContext>() {
    constructor(changeData: ChangeData) : this(changeData[0], changeData[1]) {
        if (changeData.length() == 4) {
            previousValuePos1 = changeData[2]
            previousValuePos2 = changeData[3]
        }
    }

    override fun preload(sender: CommandSender) {
        if (sender !is Player) {
            throw IllegalArgumentException("only player can do this")
        }

        val item = IItems.valueOf("SELECTION_TOOL").item
        item.tryGive(sender)
    }

    override fun validate(): Boolean {
        // allow changing pos1 and pos2 to null, but only both of them are null or blank
        if (pos1.isNullOrBlank() && pos2.isNullOrBlank()) {
            return true
        }

        runCatching {
            Coordinate.parse(pos1)
            Coordinate.parse(pos2)
        }.onFailure {
            return false
        }
        return true
    }

    private var previousValuePos1: String? = null
    private var previousValuePos2: String? = null

    override fun makeUndo(): AbstractChange<IGameContext> {
        val regionChange = RegionChange(previousValuePos1, previousValuePos2)
        regionChange.previousValuePos1 = pos1
        regionChange.previousValuePos2 = pos2
        return regionChange
    }

    override fun args(): Array<Any?> {
        return arrayOf(pos1, pos2)
    }

    override fun name(): String {
        return "change \"from $previousValuePos1 -> $previousValuePos2\" to \"from $pos1 -> $pos2\""
    }

    override fun localizedName(): (CommandSender) -> ITranslationAgent {
        return { sender ->
            localizationScope(sender) {
                message(EditorLanguage.GAME_REGION_CHANGE_NAME)
                    .format0(previousValuePos1 ?: "null", previousValuePos2 ?: "null", pos1 ?: "null", pos2 ?: "null")
            }
        }
    }

    override fun apply(t: IGameContext) {
        if (!validate()) {
            throw IllegalArgumentException("cannot parse coordinates")
        }

        val game = t.game
        val region = Region()
        region.firstCoordinate = Coordinate.parse(pos1)
        region.secondCoordinate = Coordinate.parse(pos2)
        game.region = region
    }

    companion object {
        @JvmStatic
        @ChangeMetadataMethod
        fun metadata(): ChangeMetadata {
            return ChangeMetadata(
                "game-region-change",
                IGameContext::class.java,
                2..2,
                3,
                EditorLanguage::GAME_REGION_CHANGE
            )
        }
    }
}