package io.github.gdrfgdrf.cutebedwars.commands.sub.edit

import io.github.gdrfgdrf.cutebedwars.abstracts.commands.AbstractSubCommand
import io.github.gdrfgdrf.cutebedwars.abstracts.commands.IParamCombination
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.ICommands
import io.github.gdrfgdrf.cutebedwars.abstracts.editing.IChangeTypeRegistry
import io.github.gdrfgdrf.cutebedwars.abstracts.editing.change.AbstractChange
import io.github.gdrfgdrf.cutebedwars.abstracts.locale.ILanguageString
import io.github.gdrfgdrf.cutebedwars.commands.finder.BetterChangesFinder
import io.github.gdrfgdrf.cutebedwars.languages.collect.CommandDescriptionLanguage
import io.github.gdrfgdrf.cutebedwars.languages.collect.CommandSyntaxLanguage
import io.github.gdrfgdrf.cutebedwars.languages.collect.EditorLanguage
import io.github.gdrfgdrf.cutebedwars.abstracts.locale.localizationScope
import org.bukkit.command.CommandSender

object EditMake : AbstractSubCommand(
    command = ICommands.valueOf("EDIT_MAKE")
) {
    override val syntax: ILanguageString = CommandSyntaxLanguage.EDIT_MAKE
    override val description: ILanguageString = CommandDescriptionLanguage.EDIT_MAKE

    @Suppress("UNCHECKED_CAST")
    override fun run(sender: CommandSender, args: Array<String>, paramCombination: IParamCombination) {
        localizationScope(sender) {
            val changes = BetterChangesFinder.find(sender) ?: return@localizationScope

            // when this command is used, paramSchemeIndex may be -1, so args is used here
            val changeTypeName = args[0]
            val changeClassHolder = IChangeTypeRegistry.instance().get(changeTypeName) ?: return@localizationScope

            val newArgs = arrayOfNulls<String>(args.size - 1)
            System.arraycopy(args, 1, newArgs, 0, args.size - 1)

            if (!changeClassHolder.validateArgsLength(false, *(newArgs as Array<out Any>))) {
                message(EditorLanguage.ARGUMENT_ERROR)
                    .send()
                return@localizationScope
            }
            val change: AbstractChange<*> = changeClassHolder.create(*(newArgs as Array<out Any>))
            runCatching {
                if (!change.preload(sender)) {
                    return@localizationScope
                }
            }.onFailure {
                it.printStackTrace()
                message(EditorLanguage.CANNOT_PRELOAD_CHANGE)
                    .send()
                return@localizationScope
            }

            if (!change.validate(sender)) {
                message(EditorLanguage.ARGUMENT_ERROR)
                    .send()
                return@localizationScope
            }

            val addResult = changes.tryAdd(change)
            if (!addResult) {
                message(EditorLanguage.ADD_CHANGE_ERROR)
                    .send()
            } else {
                message(EditorLanguage.ADD_CHANGE_SUCCESS)
                    .send()
            }
        }
    }
}