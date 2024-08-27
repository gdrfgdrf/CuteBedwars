package io.github.gdrfgdrf.cutebedwars.game.management

import io.github.gdrfgdrf.cutebedwars.abstracts.game.management.ISetter
import io.github.gdrfgdrf.cutebedwars.beans.annotation.PositiveNumber
import io.github.gdrfgdrf.cutebedwars.beans.annotation.Undefinable
import io.github.gdrfgdrf.cutebedwars.game.management.exception.ConvertException
import io.github.gdrfgdrf.cutebedwars.game.management.exception.NotPositiveNumberException
import io.github.gdrfgdrf.cutebedwars.game.management.exception.UndefinablePropertyException
import io.github.gdrfgdrf.cutebedwars.utils.StringUtils
import org.bukkit.command.CommandSender

open class SetterImpl<T> : ISetter {
    var instanceGetter: (() -> T)? = null
    var convert: ((Class<*>, Any) -> Any)? = null

    override fun set(sender: CommandSender, jsonKey: String, any: Any) {
        if (instanceGetter == null || convert == null) {
            throw IllegalArgumentException("argument is not initialized")
        }

        val fieldName = StringUtils.jsonKeyToFieldName(jsonKey)
        val declaredField = instanceGetter!!()!!::class.java.getDeclaredField(fieldName)

        var undefinable = false
        var positiveNumber = false
        if (declaredField.isAnnotationPresent(Undefinable::class.java)) {
            undefinable = true
        }
        if (declaredField.isAnnotationPresent(PositiveNumber::class.java)) {
            positiveNumber = true
        }
        if (undefinable) {
            throw UndefinablePropertyException()
        }

        if (positiveNumber) {
            runCatching {
                val number = convert!!(java.lang.Integer::class.java, any) as Int
                if (number < 0) {
                    throw NotPositiveNumberException()
                }
            }.onFailure {
                throw ConvertException()
            }
        }

        var converted: Any? = null
        runCatching {
            converted = convert!!(declaredField.type, any)
        }.onFailure {
            throw ConvertException()
        }
        if (converted == null) {
            throw ConvertException()
        }

        declaredField.isAccessible = true
        declaredField.set(instanceGetter!!(), converted)
    }
}