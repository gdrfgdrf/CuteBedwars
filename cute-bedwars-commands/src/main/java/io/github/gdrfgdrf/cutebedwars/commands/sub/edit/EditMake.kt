package io.github.gdrfgdrf.cutebedwars.commands.sub.edit

import io.github.gdrfgdrf.cutebedwars.abstracts.commands.AbstractSubCommand
import io.github.gdrfgdrf.cutebedwars.abstracts.enums.ICommands
import io.github.gdrfgdrf.cutebedwars.abstracts.editing.IChangeTypeRegistry
import io.github.gdrfgdrf.cutebedwars.abstracts.editing.change.AbstractChange
import io.github.gdrfgdrf.cutebedwars.commands.finder.BetterChangesFinder
import io.github.gdrfgdrf.cutebedwars.languages.collect.CommandDescriptionLanguage
import io.github.gdrfgdrf.cutebedwars.languages.collect.CommandSyntaxLanguage
import io.github.gdrfgdrf.cutebedwars.languages.collect.EditorLanguage
import io.github.gdrfgdrf.cutebedwars.locale.localizationScope
import io.github.gdrfgdrf.cuteframework.locale.LanguageString
import org.bukkit.command.CommandSender

object EditMake : AbstractSubCommand(
    command = ICommands.valueOf("EDIT_MAKE")
) {
    override fun syntax(): LanguageString? = CommandSyntaxLanguage.EDIT_MAKE
    override fun description(): LanguageString? = CommandDescriptionLanguage.EDIT_MAKE

    @Suppress("UNCHECKED_CAST")
    override fun run(sender: CommandSender, args: Array<String>, paramSchemeIndex: Int) {
        localizationScope(sender) {
            val changes = BetterChangesFinder.find(sender) ?: return@localizationScope

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
            if (!changeClassHolder.validate(change)) {
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