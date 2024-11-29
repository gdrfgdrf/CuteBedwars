package io.github.gdrfgdrf.cutebedwars.utils.json

import com.fasterxml.jackson.databind.JsonNode
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.ICustomJsonNode
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import io.github.gdrfgdrf.multimodulemediator.bean.ArgumentSet

@ServiceImpl("custom_json_node", needArgument = true)
class CustomJsonNode(
    val node: JsonNode
) : ICustomJsonNode {
    constructor(argumentSet: ArgumentSet): this(argumentSet.args[0] as JsonNode)

    override operator fun get(index: Int): String {
        return node.get(index).asText()
    }

    override operator fun get(key: String): String {
        return node.get(key).asText()
    }

    override fun getOrNull(index: Int): String? {
        if (contains(index)) {
            return get(index)
        }
        return null
    }

    override fun getOrNull(key: String): String? {
        if (contains(key)) {
            return get(key)
        }
        return null
    }

    override fun contains(index: Int): Boolean {
        return node.has(index)
    }

    override fun contains(key: String): Boolean {
        return node.has(key)
    }

    override fun size(): Int {
        return node.size()
    }

    override fun keySet(): Iterator<String> {
        return node.fieldNames()
    }
}