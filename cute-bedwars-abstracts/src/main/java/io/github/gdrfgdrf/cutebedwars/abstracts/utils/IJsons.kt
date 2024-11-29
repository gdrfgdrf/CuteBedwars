package io.github.gdrfgdrf.cutebedwars.abstracts.utils

import com.fasterxml.jackson.databind.node.ObjectNode
import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.KotlinSingleton
import io.github.gdrfgdrf.multimodulemediator.annotation.Service
import java.io.File
import java.io.InputStream

@Service("jsons")
@KotlinSingleton
interface IJsons {
    fun <T> read(string: String, type: Class<*>): T
    fun <T> read(file: File, type: Class<*>): T
    fun <T> read(inputStream: InputStream, type: Class<*>): T
    fun <T> read(bytes: ByteArray, type: Class<*>): T

    fun new(): ObjectNode

    fun read(string: String): ICustomJsonNode
    fun read(file: File): ICustomJsonNode
    fun read(inputStream: InputStream): ICustomJsonNode
    fun read(bytes: ByteArray): ICustomJsonNode

    fun <E> list(string: String, eType: Class<E>): List<E>

    fun write(any: Any): String

    companion object {
        fun instance(): IJsons = Mediator.get(IJsons::class.java)!!
    }
}