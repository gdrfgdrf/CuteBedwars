package io.github.gdrfgdrf.cutebedwars.beans

import io.github.gdrfgdrf.cutebedwars.beans.annotation.ConvertPropertyFunction

class Convertible(clazz: Class<*>) {
    private val method = clazz.methods.toList().stream()
        .filter {
            it.isAnnotationPresent(ConvertPropertyFunction::class.java)
        }
        .findAny()
        .orElseThrow()

    @Suppress("UNCHECKED_CAST")
    fun <T> invoke(targetType: Class<*>, any: Any?): T {
        return method.invoke(null, targetType, any) as T
    }

    companion object {
        fun of(clazz: Class<*>) = Convertible(clazz)
    }

}