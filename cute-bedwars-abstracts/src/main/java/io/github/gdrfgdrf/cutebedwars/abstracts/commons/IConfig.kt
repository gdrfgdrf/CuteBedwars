package io.github.gdrfgdrf.cutebedwars.abstracts.commons

interface IConfig {
    fun fulfill()
    fun <T> get(key: String): T
}