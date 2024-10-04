package io.github.gdrfgdrf.cutebedwars.storage.common

import com.google.protobuf.GeneratedMessage
import com.google.protobuf.Message
import com.google.protobuf.Parser
import io.github.gdrfgdrf.cutebedwars.protobuf.Protobuf
import java.io.File
import java.util.concurrent.ConcurrentHashMap

class ProtobufStorage {
    private val map = ConcurrentHashMap<String, Protobuf<*>>()

    @Suppress("UNCHECKED_CAST")
    fun <T : Message> protobuf(file: File, parser: Parser<T>, builder: GeneratedMessage.Builder<*>): Protobuf<T> {
        if (map.containsKey(file.absolutePath)) {
            return (map[file.absolutePath] as Protobuf<T>)
        }
        val protobuf = Protobuf.prepare(file, parser, builder)
        map[file.absolutePath] = protobuf

        return protobuf
    }

}