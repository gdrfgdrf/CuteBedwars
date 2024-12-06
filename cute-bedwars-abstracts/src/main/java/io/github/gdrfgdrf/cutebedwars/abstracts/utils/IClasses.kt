package io.github.gdrfgdrf.cutebedwars.abstracts.utils

import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.KotlinSingleton
import io.github.gdrfgdrf.multimodulemediator.annotation.Service
import java.io.File
import java.lang.reflect.Field
import java.lang.reflect.Method

@Service("classes")
@KotlinSingleton
interface IClasses {
    fun getClassParameter(any: Any, index: Int): Class<*>
    fun isImplement(clazz: Class<*>, target: Class<*>): Boolean
    fun formatPackageName(string: String): String
    fun safetyInvoke(any: Any, method: Method, vararg arguments: Any): Any?
    fun accessibleField(any: Any?, field: Field, consumer: (Field) -> Unit)
    fun isPackageExists(classLoader: ClassLoader, string: String): Boolean
    fun search(
        root: File,
        packageName: String,
        classLoader: ClassLoader,
        result: MutableSet<Class<*>>,
        predicate: ((Class<*>) -> Boolean)?
    )
    fun search(
        classLoader: ClassLoader,
        packageName: String,
        result: MutableSet<Class<*>>,
        predicate: ((Class<*>) -> Boolean)?
    )

    companion object {
        fun instance(): IClasses = Mediator.get(IClasses::class.java)!!
    }
}