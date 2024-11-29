package io.github.gdrfgdrf.cutebedwars.abstracts.utils

import com.fasterxml.jackson.databind.JsonNode
import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.Service
import io.github.gdrfgdrf.multimodulemediator.bean.ArgumentSet

@Service("custom_json_node", singleton = false)
interface ICustomJsonNode {
    operator fun get(index: Int): String
    operator fun get(key: String): String

    fun getOrNull(index: Int): String?
    fun getOrNull(key: String): String?

    fun contains(index: Int): Boolean
    fun contains(key: String): Boolean

    fun size(): Int

    fun keySet(): Iterator<String>

    companion object {
        fun new(node: JsonNode): ICustomJsonNode =
            Mediator.get(ICustomJsonNode::class.java, ArgumentSet(arrayOf(node)))!!
    }
}