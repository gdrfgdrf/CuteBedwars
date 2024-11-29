package io.github.gdrfgdrf.cutebedwars.utils.abstracts

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.node.ObjectNode
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.IJsons
import io.github.gdrfgdrf.cutebedwars.utils.json.CustomJsonNode
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import java.io.File
import java.io.InputStream

@Suppress("UNCHECKED_CAST")
@ServiceImpl("jsons")
object Jsons : IJsons {
    private val mapper = ObjectMapper()

    override fun <T> read(string: String, type: Class<*>): T {
        return mapper.readValue(string, type) as T
    }

    override fun <T> read(file: File, type: Class<*>): T {
        return mapper.readValue(file, type) as T
    }

    override fun <T> read(inputStream: InputStream, type: Class<*>): T {
        return mapper.readValue(inputStream, type) as T
    }

    override fun <T> read(bytes: ByteArray, type: Class<*>): T {
        return mapper.readValue(bytes, type) as T
    }

    override fun new(): ObjectNode {
        return mapper.readTree("{}") as ObjectNode
    }

    override fun read(string: String): CustomJsonNode {
        return CustomJsonNode(mapper.readTree(string))
    }

    override fun read(file: File): CustomJsonNode {
        return CustomJsonNode(mapper.readTree(file))
    }

    override fun read(inputStream: InputStream): CustomJsonNode {
        return CustomJsonNode(mapper.readTree(inputStream))
    }

    override fun read(bytes: ByteArray): CustomJsonNode {
        return CustomJsonNode(mapper.readTree(bytes))
    }

    override fun <E> list(string: String, eType: Class<E>): List<E> {
        val result: MutableList<E> = ArrayList()
        val jsonNode = read(string)
        if (jsonNode.node.isArray) {
            for (i in 0 until jsonNode.size()) {
                val e = read<E>(jsonNode[i], eType)
                result.add(e)
            }
        }

        return result
    }

    override fun write(obj: Any): String {
        return mapper.writeValueAsString(obj)
    }
}