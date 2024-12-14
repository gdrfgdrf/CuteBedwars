package io.github.gdrfgdrf.cutebedwars.game.editing.change.data

import io.github.gdrfgdrf.cutebedwars.abstracts.locale.ILanguageString

class ChangeMetadata(
    val identifier: String,
    val type: Class<*>,
    val argsRange: IntRange,
    val argsRangeForProtobuf: IntRange,
    private val localizedIdentifier: () -> ILanguageString,
) {
    fun identifier(): String = identifier
    fun localizedIdentifier(): () -> ILanguageString = localizedIdentifier
}