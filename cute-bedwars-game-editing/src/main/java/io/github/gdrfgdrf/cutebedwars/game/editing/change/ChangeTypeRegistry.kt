package io.github.gdrfgdrf.cutebedwars.game.editing.change

import io.github.gdrfgdrf.cutebedwars.abstracts.game.editing.AbstractChange
import io.github.gdrfgdrf.cutebedwars.abstracts.game.editing.IChangeTypeRegistry
import io.github.gdrfgdrf.cutebedwars.game.editing.change.annotation.Change
import io.github.gdrfgdrf.cuteframework.utils.ClassUtils
import java.util.LinkedHashSet
import java.util.concurrent.ConcurrentHashMap

object ChangeTypeRegistry : IChangeTypeRegistry {
    private val map = ConcurrentHashMap<String, Class<AbstractChange<*>>>()

    @Suppress("UNCHECKED_CAST")
    override fun init() {
        val classes = LinkedHashSet<Class<*>>()

        ClassUtils.searchJar(ChangeTypeRegistry::class.java.classLoader, "io.github.gdrfgdrf.cutebedwars.game.editing.change.impl", {
            if (it.superclass != AbstractChange::class.java) {
                return@searchJar false
            }

            val changeAnnotation = it.getAnnotation(Change::class.java)
            return@searchJar !(changeAnnotation == null || changeAnnotation.name.isBlank())
        }, classes)

        classes.forEach {
            val changeAnnotation = it.getAnnotation(Change::class.java) ?: return
            register(changeAnnotation.name, it as Class<AbstractChange<*>>)
        }
    }

    override fun clear() {
        map.clear()
    }

    override fun register(name: String, abstractChangeClass: Class<AbstractChange<*>>) {
        map[name] = abstractChangeClass
    }

    override fun get(name: String): Class<AbstractChange<*>>? {
        return map[name]
    }

    override fun forEach(block: (String, Class<AbstractChange<*>>) -> Unit) {
        map.forEach { (k, v) ->
            block(k, v)
        }
    }
}