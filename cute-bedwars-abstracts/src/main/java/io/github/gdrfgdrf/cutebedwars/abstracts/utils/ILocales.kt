package io.github.gdrfgdrf.cutebedwars.abstracts.utils

import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.KotlinSingleton
import io.github.gdrfgdrf.multimodulemediator.annotation.Service

@Service("locales")
@KotlinSingleton
interface ILocales {
    fun load(
        classLoader: ClassLoader,
        collectPackage: String,
        languagePackage: String,
        language: String
    )

    companion object {
        fun instance(): ILocales = Mediator.get(ILocales::class.java)!!
    }
}