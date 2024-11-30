package io.github.gdrfgdrf.cutebedwars.locale.next

import io.github.gdrfgdrf.cutebedwars.abstracts.locale.ICuteTranslation
import io.github.gdrfgdrf.cutebedwars.abstracts.locale.ITranslationAgent
import io.github.gdrfgdrf.cutebedwars.abstracts.locale.ITranslationTextAgent
import io.github.gdrfgdrf.cutebedwars.languages.collect.CommonLanguage
import net.md_5.bungee.api.chat.TextComponent
import org.bukkit.command.CommandSender

class TranslationAgent(
    private val commandSender: CommandSender,
    override val cuteTranslation: ICuteTranslation
) : ITranslationAgent {
    override val string: String = cuteTranslation.buildString()

    override fun get0(): ITranslationTextAgent {
        return TranslationTextAgent(cuteTranslation.get(0))
    }

    override fun append(string: String): ITranslationAgent {
        cuteTranslation.append(string)
        return this
    }

    override fun append(translationTextAgent: ITranslationTextAgent): ITranslationAgent {
        cuteTranslation.append(translationTextAgent.cuteText())
        return this
    }

    override fun append(translationAgent: ITranslationAgent): ITranslationAgent {
        cuteTranslation.append(translationAgent.cuteTranslation)
        return this
    }

    override fun insert(index: Int, string: String): ITranslationAgent {
        cuteTranslation.insert(index, string)
        return this
    }

    override fun insert(index: Int, translationTextAgent: ITranslationTextAgent): ITranslationAgent {
        cuteTranslation.insert(index, translationTextAgent.cuteText())
        return this
    }

    override fun format0(vararg any: Any): ITranslationAgent {
        get0().format(*any)
        return this
    }

    override fun build(): TextComponent {
        return cuteTranslation.build()
    }

    override fun send(customPrefix: String) {
        val prefix = TranslationTextAgent.of(customPrefix)
        insert(0, prefix)

        commandSender.spigot().sendMessage(build())
    }

    override fun send(customPrefix: ITranslationTextAgent?) {
        if (customPrefix != null) {
            insert(0, customPrefix)
            commandSender.spigot().sendMessage(build())
            return
        }
        val translationTextAgent = TranslationTextAgent.of(CommonLanguage.PREFIX.operate().string)
        insert(0, translationTextAgent)

        commandSender.spigot().sendMessage(build())
    }
}