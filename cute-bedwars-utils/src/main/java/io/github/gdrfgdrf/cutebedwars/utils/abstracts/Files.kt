package io.github.gdrfgdrf.cutebedwars.utils.abstracts

import io.github.gdrfgdrf.cutebedwars.abstracts.utils.IFiles
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import java.io.*

@ServiceImpl("files")
object Files : IFiles {
    override fun save(inputStream: InputStream, target: File) {
        val out = FileOutputStream(target)
        val buf = ByteArray(1024)
        var len: Int
        while ((inputStream.read(buf).also { len = it }) > 0) {
            out.write(buf, 0, len)
        }
        out.close()
        inputStream.close()
    }

    override fun reader(file: File): Reader {
        return BufferedReader(InputStreamReader(FileInputStream(file), Charsets.UTF_8))
    }

    override fun writer(file: File): Writer {
        return BufferedWriter(OutputStreamWriter(FileOutputStream(file), Charsets.UTF_8))
    }

}