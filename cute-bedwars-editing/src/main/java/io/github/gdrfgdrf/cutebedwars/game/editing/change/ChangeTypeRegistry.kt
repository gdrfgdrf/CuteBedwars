package io.github.gdrfgdrf.cutebedwars.game.editing.change

import io.github.gdrfgdrf.cutebedwars.abstracts.editing.change.AbstractChange
import io.github.gdrfgdrf.cutebedwars.abstracts.editing.IChangeTypeRegistry
import io.github.gdrfgdrf.cutebedwars.abstracts.editing.change.IChangeClassHolder
import io.github.gdrfgdrf.cutebedwars.game.editing.change.annotation.Change
import io.github.gdrfgdrf.cutebedwars.utils.extension.logInfo
import io.github.gdrfgdrf.cuteframework.utils.ClassUtils
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import java.util.LinkedHashSet
import java.util.concurrent.ConcurrentHashMap

@ServiceImpl("change_type_registry")
object ChangeTypeRegistry : IChangeTypeRegistry {
    private val map = ConcurrentHashMap<String, IChangeClassHolder<AbstractChange<*>>>()

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
        "Registering change type, name: $name, class: ${abstractChangeClass.name}".logInfo()
        map[name] = ChangeClassHolder.create(abstractChangeClass)
    }

    override fun get(name: String): IChangeClassHolder<AbstractChange<*>>? {
        return map[name]
    }

    override fun forEach(block: (String, IChangeClassHolder<AbstractChange<*>>) -> Unit) {
        map.forEach { (k, v) ->
            block(k, v)
        }
    }
}