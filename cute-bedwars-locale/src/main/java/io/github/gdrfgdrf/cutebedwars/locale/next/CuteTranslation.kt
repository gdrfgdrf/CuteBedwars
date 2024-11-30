package io.github.gdrfgdrf.cutebedwars.locale.next

import io.github.gdrfgdrf.cutebedwars.abstracts.locale.ICuteText
import io.github.gdrfgdrf.cutebedwars.abstracts.locale.ICuteTranslation
import net.md_5.bungee.api.chat.TextComponent
import org.bukkit.command.CommandSender

class CuteTranslation private constructor() : ICuteTranslation {
    private val list = arrayListOf<ICuteText>()
    private var cache: TextComponent? = null

    override fun all(): List<ICuteText> {
        return list
    }

    override fun size(): Int {
        return list.size
    }

    override fun get(index: Int): ICuteText {
        return list[index]
    }

    override fun append(cuteText: ICuteText): CuteTranslation {
        list.add(cuteText)
        return this
    }

    override fun appendAll(vararg cuteText: ICuteText): CuteTranslation {
        list.addAll(cuteText)
        return this
    }

    override fun append(raw: String): CuteTranslation {
        return append(CuteText.of(raw))
    }

    override fun appendAll(vararg raw: String): CuteTranslation {
        raw.forEach(this::append)
        return this
    }

    override fun append(cuteTranslation: ICuteTranslation): ICuteTranslation {
        cuteTranslation.all().forEach {
            append(it)
        }
        return this
    }

    override fun appendAll(vararg cuteTranslation: ICuteTranslation): ICuteTranslation {
        cuteTranslation.forEach {
            append(it)
        }
        return this
    }

    override fun insert(index: Int, cuteText: ICuteText): CuteTranslation {
        list.add(index, cuteText)
        return this
    }

    override fun insert(index: Int, raw: String): CuteTranslation {
        return insert(index, CuteText.of(raw))
    }

    override fun replace(index: Int, cuteText: ICuteText): CuteTranslation {
        list[index] = cuteText
        return this
    }

    override fun replace(index: Int, raw: String): CuteTranslation {
        return replace(index, CuteText.of(raw))
    }

    override fun build(): TextComponent {
        if (cache != null) {
            return cache!!
        }

        cache = TextComponent("")
        if (list.isEmpty()) {
            return cache!!
        }

        list.forEach { cuteText ->
            cache!!.addExtra(cuteText.build())
        }
        return cache!!
    }

    override fun send(commandSender: CommandSender) {
        val text = build()
        commandSender.spigot().sendMessage(text)
    }

    override fun string(): String {
        val stringBuilder = StringBuilder()
        list.forEach { cuteText ->
            stringBuilder.append(cuteText.string)
        }
        return stringBuilder.toString()
    }

    companion object {
        fun of(raw: String): CuteTranslation {
            val cuteText = CuteText.of(raw)
            return CuteTranslation().append(cuteText)
        }

        fun build(vararg cuteText: CuteText): CuteTranslation {
            return CuteTranslation().appendAll(*cuteText)
        }

        fun build(vararg raw: String): CuteTranslation {
            return CuteTranslation().appendAll(*raw)
        }
    }
}