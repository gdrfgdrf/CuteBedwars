package io.github.gdrfgdrf.cutebedwars.game.editing.change

import io.github.gdrfgdrf.cutebedwars.abstracts.editing.change.AbstractChange
import io.github.gdrfgdrf.cutebedwars.abstracts.editing.IChangeTypeRegistry
import io.github.gdrfgdrf.cutebedwars.abstracts.editing.change.IChangeClassHolder
import io.github.gdrfgdrf.cutebedwars.game.editing.change.annotation.ChangeMetadataMethod
import io.github.gdrfgdrf.cutebedwars.game.editing.change.data.ChangeMetadata
import io.github.gdrfgdrf.cutebedwars.utils.extension.logInfo
import io.github.gdrfgdrf.cuteframework.utils.ClassUtils
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import java.util.LinkedHashSet
import java.util.concurrent.ConcurrentHashMap

@ServiceImpl("change_type_registry")
object ChangeTypeRegistry : IChangeTypeRegistry {
    private val map = ConcurrentHashMap<String, IChangeClassHolder<AbstractChange<*>>>()

    private fun register(identifier: String, abstractChangeClass: Class<AbstractChange<*>>, metadata: ChangeMetadata) {
        "Registering change type, identifier: $identifier, class: $abstractChangeClass, type: ${metadata.type}".logInfo()
        map[identifier] = ChangeClassHolder.create(abstractChangeClass, metadata)
    }

    @Suppress("UNCHECKED_CAST")
    override fun init() {
        val classes = LinkedHashSet<Class<*>>()

        ClassUtils.searchJar(ChangeTypeRegistry::class.java.classLoader, "io.github.gdrfgdrf.cutebedwars.game.editing.change.impl", {
            return@searchJar it.superclass == AbstractChange::class.java
        }, classes)

        classes.forEach { clazz ->
            val metadataMethod = clazz.methods.toList().stream()
                .filter {
                    it.isAnnotationPresent(ChangeMetadataMethod::class.java)
                }
                .findAny()
                .orElse(null)
            if (metadataMethod == null) {
                throw IllegalStateException("change $clazz does not have metadata")
            }
            val metadata = metadataMethod.invoke(null) as ChangeMetadata
            register(metadata.identifier, clazz as Class<AbstractChange<*>>, metadata)
        }
    }

    override fun clear() {
        map.clear()
    }

    override fun get(identifier: String): IChangeClassHolder<AbstractChange<*>>? {
        return map[identifier]
    }

    override fun forEach(block: (String, IChangeClassHolder<AbstractChange<*>>) -> Unit) {
        map.forEach { (k, v) ->
            block(k, v)
        }
    }
}