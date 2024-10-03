package io.github.gdrfgdrf.cutebedwars.abstracts.locale

import net.md_5.bungee.api.chat.TextComponent
import org.bukkit.command.CommandSender

interface ICuteTranslation {
    fun size(): Int
    fun get(index: Int): ICuteText
    fun append(cuteText: ICuteText): ICuteTranslation
    fun appendAll(vararg cuteText: ICuteText): ICuteTranslation
    fun append(raw: String): ICuteTranslation
    fun appendAll(vararg raw: String): ICuteTranslation
    fun insert(index: Int, cuteText: ICuteText): ICuteTranslation
    fun insert(index: Int, raw: String): ICuteTranslation
    fun replace(index: Int, cuteText: ICuteText): ICuteTranslation
    fun replace(index: Int, raw: String): ICuteTranslation
    fun build(): TextComponent
    fun send(commandSender: CommandSender)
}