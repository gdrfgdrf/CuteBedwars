package io.github.gdrfgdrf.cutebedwars.game.editing.change.impl.game

import io.github.gdrfgdrf.cutebedwars.abstracts.editing.change.AbstractChange
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IItems
import io.github.gdrfgdrf.cutebedwars.abstracts.game.management.game.IGameContext
import io.github.gdrfgdrf.cutebedwars.abstracts.items.item.ISpecialBuiltItem
import io.github.gdrfgdrf.cutebedwars.abstracts.locale.ITranslationAgent
import io.github.gdrfgdrf.cutebedwars.abstracts.locale.localizationScope
import io.github.gdrfgdrf.cutebedwars.abstracts.selection.ISelections
import io.github.gdrfgdrf.cutebedwars.beans.pojo.common.Coordinate
import io.github.gdrfgdrf.cutebedwars.beans.pojo.common.Region
import io.github.gdrfgdrf.cutebedwars.game.editing.change.annotation.ChangeMetadataMethod
import io.github.gdrfgdrf.cutebedwars.game.editing.change.data.ChangeData
import io.github.gdrfgdrf.cutebedwars.game.editing.change.data.ChangeMetadata
import io.github.gdrfgdrf.cutebedwars.languages.collect.EditorLanguage
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class RegionChange(var pos1: String?, var pos2: String?) : AbstractChange<IGameContext>() {
    private var previousValuePos1: String? = null
    private var previousValuePos2: String? = null

    constructor(changeData: ChangeData) : this(
        "${changeData.getStringOrBlank(0)} ${changeData.getStringOrBlank(1)} ${changeData.getStringOrBlank(2)}",
        "${changeData.getStringOrBlank(3)} ${changeData.getStringOrBlank(4)} ${changeData.getStringOrBlank(5)}"
    ) {
        if (changeData.length() == 12) {
            previousValuePos1 = changeData[6]
            previousValuePos2 = changeData[7]
        }
    }

    override fun preload(sender: CommandSender): Boolean {
        if (sender !is Player) {
            throw IllegalArgumentException("only player can do this")
        }
        if (!pos1.isNullOrBlank() || !pos2.isNullOrBlank()) {
            return true
        }

        val item = IItems.valueOf("SELECTION_TOOL").special()
        if (item.has(sender) && ISelections.instance().has(sender)) {
            return true
        }

        item.give(sender)
        ISelections.instance().create(sender)

        return false
    }

    override fun validate(sender: CommandSender): Boolean {
        if (sender !is Player) {
            return false
        }
        if (pos1.isNullOrBlank() && pos2.isNullOrBlank()) {
            ISelections.instance().get(sender)?.let {
                pos1 = it.pos1()?.parsableString()
                pos2 = it.pos2()?.parsableString()
            }
        }

        runCatching {
            Coordinate.parse(pos1)
            Coordinate.parse(pos2)
        }.onFailure {
            return false
        }
        return true
    }

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

    override fun apply(t: IGameContext, sender: CommandSender) {
        if (!validate(sender)) {
            throw IllegalArgumentException("cannot validate coordinates")
        }

        val game = t.game
        val region = Region()
        previousValuePos1 = region.pos1.parsableString()
        previousValuePos2 = region.pos2.parsableString()

        if (pos1 == null && pos2 == null) {
            region.pos1 = null
            region.pos2 = null
        } else {
            region.pos1 = Coordinate.parse(pos1)
            region.pos2 = Coordinate.parse(pos2)
        }
        game.region = region

        ISelections.instance().remove(sender as Player)
    }

    companion object {
        @JvmStatic
        @ChangeMetadataMethod
        fun metadata(): ChangeMetadata {
            return ChangeMetadata(
                "game-region-change",
                IGameContext::class.java,
                0..6,
                12,
                EditorLanguage::GAME_REGION_CHANGE
            )
        }
    }
}