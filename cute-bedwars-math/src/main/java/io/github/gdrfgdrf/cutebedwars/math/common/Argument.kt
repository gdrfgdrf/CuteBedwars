package io.github.gdrfgdrf.cutebedwars.math.common

open class Argument private constructor(val value: Any) {
    companion object {
        fun of(any: Any) = Argument(any)
    }
}