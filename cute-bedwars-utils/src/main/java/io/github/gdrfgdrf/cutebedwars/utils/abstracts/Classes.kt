package io.github.gdrfgdrf.cutebedwars.utils.abstracts

import io.github.gdrfgdrf.cutebedwars.abstracts.utils.IClasses
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import java.io.File
import java.lang.reflect.Field
import java.lang.reflect.Method
import java.net.JarURLConnection
import java.util.*

@ServiceImpl("classes")
object Classes : IClasses {
    override fun isImplement(clazz: Class<*>, target: Class<*>): Boolean {
        val interfaces = clazz.interfaces
        for (anInterface in interfaces) {
            if (anInterface == target) {
                return true
            }
        }
        return false
    }

    override fun formatPackageName(string: String): String {
        if (!string.endsWith(".")) {
            return string
        }
        return string.substring(0, string.lastIndexOf("."))
    }

    override fun safetyInvoke(any: Any, method: Method, vararg arguments: Any): Any? {
        return runCatching {
            method.invoke(any, *arguments)
        }.onFailure {
            return null
        }
    }

    override fun accessibleField(any: Any?, field: Field, consumer: (Field) -> Unit) {
        var changeAccessible = false
        if (!field.canAccess(any)) {
            changeAccessible = true
            field.isAccessible = true
        }

        consumer(field)

        if (changeAccessible) {
            field.isAccessible = false
        }
    }

    override fun isPackageExists(classLoader: ClassLoader, string: String): Boolean {
        val packagePath = string.replace(".", "/")
        val packageUrl = classLoader.getResource(packagePath)
        return packageUrl != null
    }

    override fun search(
        root: File,
        packageName: String,
        classLoader: ClassLoader,
        result: MutableSet<Class<*>>,
        predicate: ((Class<*>) -> Boolean)?
    ) {
        searchInternal(
            root,
            packageName,
            result,
            classLoader,
            true,
            predicate
        )
    }

    private fun searchInternal(
        searchRoot: File,
        packageName: String,
        result: MutableSet<Class<*>>,
        classLoader: ClassLoader,
        flag: Boolean,
        predicate: ((Class<*>) -> Boolean)?
    ) {
        var mutablePackageName = packageName
        if (searchRoot.isDirectory) {
            val files = searchRoot.listFiles() ?: return
            if (!flag) {
                mutablePackageName = mutablePackageName + "." + searchRoot.name
            }

            val finalPackageName = mutablePackageName
            Arrays.stream(files).forEach { file: File ->
                searchInternal(
                    file,
                    finalPackageName,
                    result,
                    classLoader,
                    false,
                    predicate
                )
            }

            return
        }
        if (searchRoot.name.endsWith(".class")) {
            val clazz = Class.forName(
                "$mutablePackageName." +
                        searchRoot.name
                            .substring(0, searchRoot.name.lastIndexOf(".")),
                true,
                classLoader
            )
            if (predicate == null || predicate(clazz)) {
                result.add(clazz)
            }
        }
    }

    override fun search(
        classLoader: ClassLoader,
        packageName: String,
        result: MutableSet<Class<*>>,
        predicate: ((Class<*>) -> Boolean)?,
    ) {
        val urlEnumeration = classLoader
            .getResources(packageName.replace(".", "/"))

        while (urlEnumeration.hasMoreElements()) {
            val url = urlEnumeration.nextElement()
            val protocol = url.protocol

            if (!"jar".equals(protocol, ignoreCase = true)) {
                if ("file".equals(protocol, ignoreCase = true)) {
                    val classpath = url.path.replace(
                        packageName.replace(".", "/"),
                        ""
                    )
                    val packagePath = packageName.replace(".", "/")
                    val searchRoot = File(classpath + packagePath)

                    search(
                        searchRoot,
                        packageName,
                        classLoader,
                        result,
                        predicate
                    )
                }
                continue
            }
            val connection = url.openConnection() as JarURLConnection
            val jarFile = connection.jarFile ?: continue

            val entryEnumeration = jarFile.entries()
            while (entryEnumeration.hasMoreElements()) {
                val entry = entryEnumeration.nextElement()
                val entryName = entry.name
                if (!entryName.contains(".class") ||
                    !entryName.replace("/".toRegex(), ".")
                        .startsWith(packageName)
                ) {
                    continue
                }
                val className = entryName
                    .substring(0, entryName.lastIndexOf("."))
                    .replace("/", ".")
                val clazz = Class.forName(className, true, classLoader)

                if (predicate == null || predicate(clazz)) {
                    result.add(clazz)
                }
            }
        }
    }
}