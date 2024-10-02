/*
 * Copyright 2024 CuteTrade's contributors
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package io.github.gdrfgdrf.cutebedwars.protobuf

import com.google.protobuf.AbstractMessage
import com.google.protobuf.GeneratedMessage.Builder
import com.google.protobuf.Message
import com.google.protobuf.Parser
import java.io.BufferedInputStream
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

class Protobuf<T : Message> {
    var message: T? = null
    var storeFile: File? = null

    fun rebuild(
        function: (T?) -> T
    ) {
        message = function(message)
    }

    fun save() {
        if (message == null ||
            storeFile == null ||
            !storeFile!!.exists() ||
            !storeFile!!.isFile) {
            return
        }

        val fileOutputStream = FileOutputStream(storeFile!!)
        val buffOutputStream = BufferedOutputStream(fileOutputStream)

        buffOutputStream.write(message!!.toByteArray())
        buffOutputStream.close()
        fileOutputStream.close()
    }

    companion object {
        fun <T : Message> prepare(
            storeFile: File,
            parser: Parser<T>,
            builder: () -> T,
        ): Protobuf<T> {
            if (!storeFile.exists()) {
                storeFile.createNewFile()
                val protobuf = Protobuf<T>()
                protobuf.message = builder()
                protobuf.storeFile = storeFile
                protobuf.save()

                return protobuf
            }
            val protobuf = new(storeFile, parser)
            return protobuf
        }

        fun <T : Message> new(
            storeFile: File,
            parser: Parser<T>,
        ): Protobuf<T> {
            if (!storeFile.exists() || !storeFile.isFile) {
                throw IllegalArgumentException("file is not exists or not a file")
            }

            val fileInputStream = FileInputStream(storeFile)
            val bufferedInputStream = BufferedInputStream(fileInputStream)
            val bytes = bufferedInputStream.readAllBytes()

            fileInputStream.close()
            bufferedInputStream.close()

            val t = parser.parseFrom(bytes)

            val protobuf = Protobuf<T>()
            protobuf.message = t
            protobuf.storeFile = storeFile

            return protobuf
        }
    }
}