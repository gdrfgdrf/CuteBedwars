package io.github.gdrfgdrf.cutebedwars.abstracts.utils

import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.KotlinSingleton
import io.github.gdrfgdrf.multimodulemediator.annotation.Service
import java.io.File
import java.io.Reader
import java.io.Writer

@Service("files")
@KotlinSingleton
interface IFiles {
    fun reader(file: File): Reader
    fun writer(file: File): Writer

    companion object {
        fun instance(): IFiles = Mediator.get(IFiles::class.java)!!
    }

}