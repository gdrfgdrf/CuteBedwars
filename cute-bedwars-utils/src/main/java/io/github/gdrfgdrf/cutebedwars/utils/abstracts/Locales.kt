package io.github.gdrfgdrf.cutebedwars.utils.abstracts

import com.fasterxml.jackson.databind.JsonNode
import io.github.gdrfgdrf.cutebedwars.abstracts.commons.IConstants
import io.github.gdrfgdrf.cutebedwars.abstracts.locale.ILanguageString
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.IILanguageBlock
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.IILanguageCollect
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.ILocales
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.logError
import io.github.gdrfgdrf.cutebedwars.utils.json.CustomJsonNode
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import java.io.File
import java.lang.reflect.Field
import java.util.*

@ServiceImpl("locales")
object Locales : ILocales {
    override fun load(
        classLoader: ClassLoader,
        collectPackage: String,
        languagePackage: String,
        language: String
    ) {
        val implLanguageFolder = File(IConstants["LANGUAGE_FOLDER"])
        if (!implLanguageFolder.exists()) {
            implLanguageFolder.mkdirs()
        }

        val languageFile = File(implLanguageFolder, "$language.json")
        if (languageFile.exists()) {
            loadFromFile(
                classLoader,
                collectPackage,
                languagePackage,
                language
            )
            return
        }
        loadFromClass(
            classLoader,
            collectPackage,
            languagePackage,
            language
        )

        saveCollectClass(
            classLoader,
            collectPackage,
            languageFile
        )
    }

    @Suppress("UNCHECKED_CAST")
    private fun loadFromFile(
        classLoader: ClassLoader,
        collectPackage: String,
        languagePackage: String,
        language: String
    ) {
        var mutableCollectPackage = collectPackage
        var mutableLanguagePackage = languagePackage
        mutableCollectPackage = Classes.formatPackageName(mutableCollectPackage)
        mutableLanguagePackage = Classes.formatPackageName(mutableLanguagePackage)

        val languageFile = File(IConstants["LANGUAGE_FOLDER"], "$language.json")
        if (!languageFile.exists()) {
            return
        }

        val languagePackageString = language.replace("_", ".")
        val fullLanguagePackage = "$mutableLanguagePackage.$languagePackageString"
        val languagePackageExists: Boolean = Classes.isPackageExists(classLoader, fullLanguagePackage)

        val allLanguageCollectClasses = HashSet<Class<*>>()

        Classes.search(
            classLoader,
            mutableCollectPackage,
            allLanguageCollectClasses
        ) { clazz ->
            Classes.isImplement(clazz, IILanguageCollect::class.java)
        }

        Classes.search(
            classLoader,
            fullLanguagePackage,
            allLanguageCollectClasses
        ) { clazz ->
            Classes.isImplement(clazz, IILanguageCollect::class.java)
        }

        val superJsonNode = Jsons.read(languageFile)
        allLanguageCollectClasses.forEach { collectClass ->
            runCatching {
                val collectClassName = collectClass.simpleName
                val collectFields =
                    Arrays.stream(collectClass.declaredFields)
                        .filter { field -> field.type == ILanguageString::class.java }
                        .toList()
                        .toTypedArray()
                val collectNode = CustomJsonNode(superJsonNode.node.get(collectClassName))

                for (collectField in collectFields) {
                    val fieldName = collectField.name

                    if (collectNode.contains(fieldName)) {
                        val languageContent = collectNode.getOrNull(fieldName)
                        if (languageContent.isNullOrBlank()) {
                            return@forEach
                        }
                        val languageString = ILanguageString.new(languageContent)

                        runCatching {
                            setFromString(fieldName, languageString, collectClass as Class<IILanguageCollect>)
                        }.onFailure {
                            "Cannot set field for collect class".logError(it)
                        }

                        continue
                    }
                    if (!languagePackageExists) {
                        continue
                    }

                    runCatching {
                        val languageBlockClass =
                            classLoader.loadClass("$fullLanguagePackage.$collectClassName") as Class<IILanguageBlock>
                        setFromBlock(collectField, languageBlockClass.getDeclaredField(fieldName))
                    }.onFailure {
                        "Load language error from class".logError(it)
                    }
                }
            }.onFailure {
                "Load language error from file".logError(it)
            }
        }
    }

    private fun loadFromClass(
        classLoader: ClassLoader,
        collectPackage: String,
        languagePackage: String,
        language: String
    ) {
        Classes.formatPackageName(collectPackage)
        Classes.formatPackageName(languagePackage)

        val languagePackageString = language.replace("_", ".")
        val fullLanguagePackage = "$languagePackage.$languagePackageString"

        if (!Classes.isPackageExists(classLoader, fullLanguagePackage)) {
            throw IllegalArgumentException("not found language classes: $fullLanguagePackage")
        }

        val allLanguageBlockClasses = HashSet<Class<*>>()

        Classes.search(
            classLoader,
            fullLanguagePackage,
            allLanguageBlockClasses
        ) { clazz ->
            Classes.isImplement(clazz, IILanguageBlock::class.java)
        }

        allLanguageBlockClasses.forEach { languageBlockClass ->
            val className = languageBlockClass.simpleName

            runCatching {
                val collectClass = classLoader.loadClass("$collectPackage.$className")
                val collectFields = Arrays.stream(collectClass.declaredFields)
                    .filter { field -> field.type == ILanguageString::class.java }
                    .toList()
                    .toTypedArray()

                for (collectField in collectFields) {
                    val fieldName = collectField.name

                    setFromBlock(
                        collectField,
                        languageBlockClass.getDeclaredField(fieldName)
                    )
                }
            }.onFailure {
                "load language error from classes".logError(it)
            }
        }
    }

    private fun saveCollectClass(classLoader: ClassLoader, collectPackage: String, file: File) {
        Classes.formatPackageName(collectPackage)

        val allCollectClasses = HashSet<Class<*>>()

        Classes.search(
            classLoader,
            collectPackage,
            allCollectClasses
        ) { clazz ->
            Classes.isImplement(clazz, IILanguageCollect::class.java)
        }

        val root = Jsons.new()
        allCollectClasses.forEach { collectClass ->
            val languageCollectNode = Jsons.new()

            val collectFields = Arrays.stream(collectClass.declaredFields)
                .filter { field ->
                    field.type == ILanguageString::class.java
                }
                .toList()
                .toTypedArray()

            for (collectField in collectFields) {
                val languageKey = collectField.name
                val languageContent = collectField[null] as ILanguageString

                languageCollectNode.put(languageKey, languageContent.operate().string)
            }

            root.set<JsonNode>(collectClass.simpleName, languageCollectNode)
        }

        if (!file.exists()) {
            file.createNewFile()
        }
        val writer = Files.writer(file)
        writer.write(root.toString())
        writer.close()
    }

    private fun setFromBlock(collectField: Field, blockField: Field) {
        Classes.accessibleField(null, blockField) { accessibleBlockField ->
            runCatching {
                val languageString = accessibleBlockField.get(null) as ILanguageString
                collectField[null] = languageString
            }.onFailure {
                "Cannot set field for collect class".logError(it)
            }
        }
    }

    private fun setFromString(
        languageKey: String,
        languageContent: ILanguageString,
        languageCollect: Class<out IILanguageCollect?>
    ) {
        if (languageKey.isBlank()) {
            return
        }

        val languageField = languageCollect.getDeclaredField(languageKey)
        Classes.accessibleField(null, languageField) { accessibleLanguageField ->
            accessibleLanguageField.set(null, languageContent)
        }
    }

}