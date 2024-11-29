package io.github.gdrfgdrf.cutebedwars.abstracts.utils

import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.KotlinSingleton
import io.github.gdrfgdrf.multimodulemediator.annotation.Service

@Service("configs")
@KotlinSingleton
interface IConfigs {
    fun <T : Any> load(fileName: String, clazz: Class<T>): T
    fun save(fileName: String, any: Any)

    companion object {
        fun instance(): IConfigs = Mediator.get(IConfigs::class.java)!!
    }
}