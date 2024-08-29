package io.github.gdrfgdrf.cutebedwars.game.editing.pojo

import io.github.gdrfgdrf.cutebedwars.game.editing.base.AbstractChange

class PropertyChange(private val key: String, private val value: Any?, name: String = "change $key to $value") : AbstractChange(name) {
    private var previousValue: Any? = null

    override fun makeUndo(): AbstractChange {
        val propertyChange = PropertyChange(key, previousValue, "change back $key from $value to $previousValue")
        propertyChange.previousValue = value
        return propertyChange
    }
}