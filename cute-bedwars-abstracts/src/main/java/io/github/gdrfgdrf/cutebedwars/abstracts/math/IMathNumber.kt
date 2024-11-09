package io.github.gdrfgdrf.cutebedwars.abstracts.math

import io.github.gdrfgdrf.cutebedwars.abstracts.math.enums.IPrecisions

interface IMathNumber {
    val number: Number
    operator fun plus(number2: Int): IMathNumber
    operator fun plus(number2: Double): IMathNumber
    operator fun plus(number2: Float): IMathNumber
    operator fun plus(mathNumber: IMathNumber): IMathNumber

    operator fun minus(number2: Int): IMathNumber
    operator fun minus(number2: Double): IMathNumber
    operator fun minus(number2: Float): IMathNumber
    operator fun minus(mathNumber: IMathNumber): IMathNumber

    operator fun times(number2: Int): IMathNumber
    operator fun times(number2: Double): IMathNumber
    operator fun times(number2: Float): IMathNumber
    operator fun times(mathNumber: IMathNumber): IMathNumber

    operator fun div(number2: Int): IMathNumber
    operator fun div(number2: Double): IMathNumber
    operator fun div(number2: Float): IMathNumber
    operator fun div(mathNumber: IMathNumber): IMathNumber

    fun pow(number2: Int): IMathNumber
    fun sqrt(): IMathNumber
    fun cut(precisions: IPrecisions): IMathNumber
}