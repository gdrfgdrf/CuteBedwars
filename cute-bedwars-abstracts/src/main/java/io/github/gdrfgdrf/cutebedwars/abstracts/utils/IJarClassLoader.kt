package io.github.gdrfgdrf.cutebedwars.abstracts.utils

import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.Service
import io.github.gdrfgdrf.multimodulemediator.bean.ArgumentSet
import java.io.File
import java.net.URL
import java.net.URLClassLoader

@Service("jar_class_loader", singleton = false)
abstract class IJarClassLoader(array: Array<URL>, parent: ClassLoader) : URLClassLoader(array, parent) {
    companion object {
        fun new(file: File, parent: ClassLoader): IJarClassLoader = Mediator.get(
            IJarClassLoader::class.java, ArgumentSet(
                arrayOf(file, parent)
            )
        )!!

        fun new(file: File): IJarClassLoader = new(file, Thread.currentThread().contextClassLoader)

        fun create(file: File, parent: ClassLoader) = new(file, parent)
        fun create(file: File) = new(file)
    }
}