package io.github.gdrfgdrf.cutebedwars.utils.abstracts

import io.github.gdrfgdrf.cutebedwars.abstracts.utils.IFiles
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.io.Reader
import java.io.Writer

@ServiceImpl("files")
object Files : IFiles {

    override fun reader(file: File): Reader {
        return BufferedReader(InputStreamReader(FileInputStream(file), Charsets.UTF_8))
    }

    override fun writer(file: File): Writer {
        return BufferedWriter(OutputStreamWriter(FileOutputStream(file), Charsets.UTF_8))
    }

}