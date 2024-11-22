package io.github.gdrfgdrf.cutebedwars.commands.sub

import io.github.gdrfgdrf.cutebedwars.abstracts.commands.AbstractSubCommand
import io.github.gdrfgdrf.cutebedwars.abstracts.commands.IParamCombination
import io.github.gdrfgdrf.cutebedwars.abstracts.editing.IEditors
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.ICommands
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.uuid
import io.github.gdrfgdrf.cutebedwars.commands.finder.BetterAreaFinder
import io.github.gdrfgdrf.cutebedwars.commands.finder.BetterGameFinder
import io.github.gdrfgdrf.cutebedwars.languages.collect.CommandDescriptionLanguage
import io.github.gdrfgdrf.cutebedwars.languages.collect.CommandSyntaxLanguage
import io.github.gdrfgdrf.cutebedwars.languages.collect.EditorLanguage
import io.github.gdrfgdrf.cutebedwars.abstracts.locale.localizationScope
import io.github.gdrfgdrf.cuteframework.locale.LanguageString
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object EditorGame : AbstractSubCommand(
    command = ICommands.valueOf("EDITOR_GAME")
) {
    override fun syntax(): LanguageString? = CommandSyntaxLanguage.EDITOR_GAME
    override fun description(): LanguageString? = CommandDescriptionLanguage.EDITOR_GAME

    override fun run(sender: CommandSender, args: Array<String>, paramCombination: IParamCombination) {
        localizationScope(sender) {
            val areaFindType = paramCombination.findType()
            val areaIdentifier = paramCombination.areaIdentifier()
            val gameFindType = paramCombination.findType(1)
            val gameIdentifier = paramCombination.gameIdentifier()
            val uuid = sender.uuid()

            val areaManager = BetterAreaFinder.find(
                sender,
                areaFindType!!,
                areaIdentifier
            ) ?: return@localizationScope
            val gameContext = BetterGameFinder.find(
                sender,
                gameFindType!!,
                areaManager,
                gameIdentifier
            ) ?: return@localizationScope

            val editor = IEditors.instance().get(uuid)
            if (editor != null) {
                message(EditorLanguage.ALREADY_IN_EDITING_MODE)
                    .send()
                return@localizationScope
            }

            message(EditorLanguage.LOADING_GAME_EDITOR)
                .send()

            runCatching {
                val areaEditor = IEditors.instance().createGameEditor(uuid, gameContext)
                IEditors.instance().put(areaEditor)

                message(EditorLanguage.EDITOR_LOAD_FINISHED)
                    .send()
            }.onFailure {
                it.printStackTrace()
                message(EditorLanguage.EDITOR_LOAD_ERROR)
                    .send()
            }
        }
    }
}