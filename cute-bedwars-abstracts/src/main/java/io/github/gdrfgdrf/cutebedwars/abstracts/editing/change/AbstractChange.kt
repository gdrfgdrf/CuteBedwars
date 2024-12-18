package io.github.gdrfgdrf.cutebedwars.abstracts.editing.change

import com.github.yitter.idgen.YitIdHelper
import io.github.gdrfgdrf.cutebedwars.abstracts.locale.ILanguageString
import io.github.gdrfgdrf.cutebedwars.abstracts.locale.ITranslationAgent
import org.bukkit.command.CommandSender

abstract class AbstractChange<T> {
    val id = YitIdHelper.nextId()

    open fun preload(sender: CommandSender): Boolean {
        // empty implementation by default
        return true
    }

    abstract fun validate(sender: CommandSender): Boolean
    abstract fun apply(t: T, sender: CommandSender)
    abstract fun makeUndo(): AbstractChange<T>
    abstract fun args(): Array<Any?>

    abstract fun name(): String
    abstract fun localizedName(): (CommandSender) -> ITranslationAgent

    private var metadata: Any? = null
    private var identifier: String? = null
    private var localizedIdentifier: ILanguageString? = null

    @Suppress("UNCHECKED_CAST")
    private fun metadata(): Any {
        if (metadata != null) {
            return metadata!!
        }

        val changeMetadataMethodClass = Class.forName("io.github.gdrfgdrf.cutebedwars.editing.change.annotation.ChangeMetadataMethod")
        val metadataMethod = this::class.java.methods.toList().stream()
            .filter {
                it.isAnnotationPresent(changeMetadataMethodClass as Class<out Annotation>)
            }
            .findAny()
            .orElse(null)
        if (metadataMethod == null) {
            throw IllegalStateException("change ${this::class.java} does not have metadata")
        }
        metadata = metadataMethod.invoke(null)
        return metadata!!
    }

    fun identifier(): String {
        if (identifier != null) {
            return identifier!!
        }

        val changeMetadata = Class.forName("io.github.gdrfgdrf.cutebedwars.editing.change.data.ChangeMetadata")
        val identifierField = changeMetadata.getMethod("identifier")
        identifier = identifierField.invoke(metadata()) as String

        return identifier!!
    }

    @Suppress("UNCHECKED_CAST")
    fun localizedIdentifier(): ILanguageString {
        if (localizedIdentifier != null) {
            return localizedIdentifier!!
        }

        val changeMetadata = Class.forName("io.github.gdrfgdrf.cutebedwars.editing.change.data.ChangeMetadata")
        val localizedIdentifierField = changeMetadata.getMethod("localizedIdentifier")
        localizedIdentifier = (localizedIdentifierField.invoke(metadata()) as  () -> ILanguageString)()

        return localizedIdentifier!!
    }

}