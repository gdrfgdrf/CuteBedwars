package io.github.gdrfgdrf.cutebedwars.utils

import io.github.gdrfgdrf.cutebedwars.abstracts.utils.IJarClassLoader
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.logError
import io.github.gdrfgdrf.multimodulemediator.annotation.Service
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import io.github.gdrfgdrf.multimodulemediator.bean.ArgumentSet
import java.io.File
import java.io.InputStream
import java.net.URL
import java.security.CodeSource
import java.util.*
import java.util.jar.JarFile

@ServiceImpl("jar_class_loader", needArgument = true)
class JarClassLoader : IJarClassLoader {
    private val jarFile: JarFile
    private val url: URL

    constructor(argumentSet: ArgumentSet): this(argumentSet.args[0] as File, argumentSet.args[1] as ClassLoader)

    constructor(file: File): this(file, Thread.currentThread().contextClassLoader)
    constructor(file: File, parent: ClassLoader): super(arrayOf(file.toURI().toURL()), parent) {
        this.jarFile = JarFile(file)
        this.url = file.toURI().toURL()
    }

    private fun classNameToJarEntry(name: String): String {
        val s = name.replace("\\.".toRegex(), "/")
        return "$s.class"
    }

    override fun findClass(name: String): Class<*>? {
        runCatching {
            var c: Class<*>? = null

            val jarEntryName = classNameToJarEntry(name)
            val entry = jarFile.getJarEntry(jarEntryName)

            if (null != entry) {
                val inputStream = jarFile.getInputStream(entry)
                val availableLen = inputStream.available()

                var len = 0
                val bt1 = ByteArray(availableLen)
                while (len < availableLen) {
                    len += inputStream.read(bt1, len, availableLen - len)
                }

                val signers = entry.codeSigners
                val source = CodeSource(url, signers)

                c = defineClass(name, bt1, 0, bt1.size, source)
            } else {
                if (parent != null) {
                    return parent.loadClass(name)
                }
            }

            return c
        }.onFailure {
            "Class $name not found".logError(it)
        }
        return null
    }

    override fun getResource(name: String?): URL? {
        return findResource(name)
    }

    override fun getResources(name: String?): Enumeration<URL> {
        return findResources(name)
    }

    override fun getResourceAsStream(name: String): InputStream? {
        var inputStream: InputStream? = null

        runCatching {
            val entry = jarFile.getJarEntry(name)
            if (entry != null) {
                inputStream = jarFile.getInputStream(entry)
            }
            if (inputStream == null) {
                inputStream = super.getResourceAsStream(name)
            }
        }.onFailure {
            return null
        }

        return inputStream
    }
}