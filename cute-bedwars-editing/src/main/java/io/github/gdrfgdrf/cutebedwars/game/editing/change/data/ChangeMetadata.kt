package io.github.gdrfgdrf.cutebedwars.game.editing.change.data

import io.github.gdrfgdrf.cuteframework.locale.LanguageString

class ChangeMetadata(
    val identifier: String,
    val type: Class<*>,
    val argsRange: IntRange,
    val maxArgsForProtobuf: Int,
    private val localizedIdentifier: () -> LanguageString,
) {
    fun identifier(): String = identifier
    fun localizedIdentifier(): () -> LanguageString = localizedIdentifier
}