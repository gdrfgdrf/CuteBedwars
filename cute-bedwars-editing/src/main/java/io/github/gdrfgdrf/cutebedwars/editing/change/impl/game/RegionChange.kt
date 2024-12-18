package io.github.gdrfgdrf.cutebedwars.editing.change.impl.game

import io.github.gdrfgdrf.cutebedwars.abstracts.editing.change.AbstractChange
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IItems
import io.github.gdrfgdrf.cutebedwars.abstracts.game.management.game.IGameContext
import io.github.gdrfgdrf.cutebedwars.abstracts.locale.ITranslationAgent
import io.github.gdrfgdrf.cutebedwars.abstracts.locale.localizationScope
import io.github.gdrfgdrf.cutebedwars.abstracts.selection.ISelections
import io.github.gdrfgdrf.cutebedwars.beans.pojo.common.Coordinate
import io.github.gdrfgdrf.cutebedwars.beans.pojo.common.Region
import io.github.gdrfgdrf.cutebedwars.editing.change.annotation.ChangeMetadataMethod
import io.github.gdrfgdrf.cutebedwars.editing.change.data.ChangeData
import io.github.gdrfgdrf.cutebedwars.editing.change.data.ChangeMetadata
import io.github.gdrfgdrf.cutebedwars.languages.collect.EditorLanguage
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class RegionChange(
    var x1: String?,
    var y1: String?,
    var z1: String?,
    var x2: String?,
    var y2: String?,
    var z2: String?
) : AbstractChange<IGameContext>() {
    private var previousValueX1: String? = null
    private var previousValueY1: String? = null
    private var previousValueZ1: String? = null
    private var previousValueX2: String? = null
    private var previousValueY2: String? = null
    private var previousValueZ2: String? = null

    constructor(changeData: ChangeData) : this(
        changeData.getStringOrBlank(0),
        changeData.getStringOrBlank(1),
        changeData.getStringOrBlank(2),
        changeData.getStringOrBlank(3),
        changeData.getStringOrBlank(4),
        changeData.getStringOrBlank(5)
    ) {
        if (changeData.length() == 12) {
            previousValueX1 = changeData.getStringOrBlank(6)
            previousValueY1 = changeData.getStringOrBlank(7)
            previousValueZ1 = changeData.getStringOrBlank(8)
            previousValueX2 = changeData.getStringOrBlank(9)
            previousValueY2 = changeData.getStringOrBlank(10)
            previousValueZ2 = changeData.getStringOrBlank(11)
        }
    }

    override fun preload(sender: CommandSender): Boolean {
        if (sender !is Player) {
            throw IllegalArgumentException("only player can do this")
        }
        if (!x1.isNullOrBlank() ||
            !y1.isNullOrBlank() ||
            !z1.isNullOrBlank() ||
            !x2.isNullOrBlank() ||
            !y2.isNullOrBlank() ||
            !z2.isNullOrBlank()) {
            return true
        }

        val item = IItems.valueOf("SELECTION_TOOL").special()
        if (item.has(sender) && ISelections.instance().has(sender)) {
            val select = ISelections.instance().get(sender)!!
            return select.pos1() != null && select.pos2() != null
        }

        item.give(sender)
        ISelections.instance().create(sender)

        return false
    }

    override fun validate(sender: CommandSender): Boolean {
        if (sender !is Player) {
            return false
        }
        if (x1.isNullOrBlank() &&
            y1.isNullOrBlank() &&
            z1.isNullOrBlank() &&
            x2.isNullOrBlank() &&
            y2.isNullOrBlank() &&
            z2.isNullOrBlank()) {
            ISelections.instance().get(sender)?.let {
                val pos1 = it.pos1()
                val pos2 = it.pos2()

                x1 = pos1?.x.toString()
                y1 = pos1?.y.toString()
                z1 = pos1?.z.toString()
                x2 = pos2?.x.toString()
                y2 = pos2?.y.toString()
                z2 = pos2?.z.toString()
            }
        }

        runCatching {
            Coordinate.tryParse(x1, y1, z1)
            Coordinate.tryParse(x2, y2, z2)
        }.onFailure {
            return false
        }
        return true
    }

    override fun makeUndo(): AbstractChange<IGameContext> {
        val regionChange = RegionChange(
            previousValueX1,
            previousValueY1,
            previousValueZ1,
            previousValueX2,
            previousValueY2,
            previousValueZ2
        )
        regionChange.previousValueX1 = x1
        regionChange.previousValueY1 = y1
        regionChange.previousValueZ1 = z1
        regionChange.previousValueX2 = x2
        regionChange.previousValueY2 = y2
        regionChange.previousValueZ2 = z2
        return regionChange
    }

    override fun args(): Array<Any?> {
        val pos1Coordinate = Coordinate.tryParse(x1, y1, z1)
        val pos2Coordinate = Coordinate.tryParse(x2, y2, z2)
        val previousPos1Coordinate = Coordinate.tryParse(previousValueX1, previousValueY1, previousValueZ1)
        val previousPos2Coordinate = Coordinate.tryParse(previousValueX2, previousValueY2, previousValueZ2)

        val x1 = pos1Coordinate?.x ?: ""
        val y1 = pos1Coordinate?.y ?: ""
        val z1 = pos1Coordinate?.z ?: ""
        val x2 = pos2Coordinate?.x ?: ""
        val y2 = pos2Coordinate?.y ?: ""
        val z2 = pos2Coordinate?.z ?: ""

        val previousX1 = previousPos1Coordinate?.x ?: ""
        val previousY1 = previousPos1Coordinate?.y ?: ""
        val previousZ1 = previousPos1Coordinate?.z ?: ""
        val previousX2 = previousPos2Coordinate?.x ?: ""
        val previousY2 = previousPos2Coordinate?.y ?: ""
        val previousZ2 = previousPos2Coordinate?.z ?: ""

        return arrayOf(
            x1,
            y1,
            z1,
            x2,
            y2,
            z2,
            previousX1,
            previousY1,
            previousZ1,
            previousX2,
            previousY2,
            previousZ2
        )
    }

    override fun name(): String {
        val pos1Coordinate = Coordinate.tryParse(x1, y1, z1)
        val pos2Coordinate = Coordinate.tryParse(x2, y2, z2)
        val previousPos1Coordinate = Coordinate.tryParse(previousValueX1, previousValueY1, previousValueZ1)
        val previousPos2Coordinate = Coordinate.tryParse(previousValueX2, previousValueY2, previousValueZ2)
        return "change \"from $previousPos1Coordinate -> $previousPos2Coordinate\" to \"from $pos1Coordinate -> $pos2Coordinate\""
    }

    override fun localizedName(): (CommandSender) -> ITranslationAgent {
        return { sender ->
            val pos1Coordinate = Coordinate.tryParse(x1, y1, z1)
            val pos2Coordinate = Coordinate.tryParse(x2, y2, z2)
            val previousPos1Coordinate = Coordinate.tryParse(previousValueX1, previousValueY1, previousValueZ1)
            val previousPos2Coordinate = Coordinate.tryParse(previousValueX2, previousValueY2, previousValueZ2)
            localizationScope(sender) {
                message(EditorLanguage.GAME_REGION_CHANGE_NAME)
                    .format0(previousPos1Coordinate ?: "null", previousPos2Coordinate ?: "null", pos1Coordinate ?: "null", pos2Coordinate ?: "null")
            }
        }
    }

    override fun apply(t: IGameContext, sender: CommandSender) {
        if (!validate(sender)) {
            throw IllegalArgumentException("cannot validate coordinates")
        }

        val game = t.game

        val previousRegion = game.region
        previousValueX1 = previousRegion?.pos1?.x.toString()
        previousValueY1 = previousRegion?.pos1?.y.toString()
        previousValueZ1 = previousRegion?.pos1?.z.toString()
        previousValueX2 = previousRegion?.pos2?.x.toString()
        previousValueY2 = previousRegion?.pos2?.y.toString()
        previousValueZ2 = previousRegion?.pos2?.z.toString()

        var newRegion: Region? = Region()

        var newPos1: Coordinate? = Coordinate()
        if (!x1.isNullOrBlank() && !y1.isNullOrBlank() && !z1.isNullOrBlank()) {
            newPos1!!.x = x1.toString().toDouble()
            newPos1.y = y1.toString().toDouble()
            newPos1.z = z1.toString().toDouble()
        } else {
            newPos1 = null
        }

        var newPos2: Coordinate? = Coordinate()
        if (!x2.isNullOrBlank() && !y2.isNullOrBlank() && !z2.isNullOrBlank()) {
            newPos2!!.x = x2.toString().toDouble()
            newPos2.y = y2.toString().toDouble()
            newPos2.z = z2.toString().toDouble()
        } else {
            newPos2 = null
        }

        if (newPos1 != null && newPos2 != null) {
            newRegion!!.pos1 = newPos1
            newRegion.pos2 = newPos2
        } else {
            newRegion = null
        }

        game.region = newRegion

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
                12..12,
                EditorLanguage::GAME_REGION_CHANGE
            )
        }
    }
}