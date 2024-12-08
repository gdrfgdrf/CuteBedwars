package io.github.gdrfgdrf.cutebedwars.abstracts.math

import io.github.gdrfgdrf.cutebedwars.abstracts.math.enums.IPrecisions
import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.Service
import io.github.gdrfgdrf.multimodulemediator.bean.ArgumentSet

@Service("math_number", singleton = false)
interface IMathNumber {
    val number: Number
    operator fun plus(number2: Int): IMathNumber
    operator fun plus(number2: Double): IMathNumber
    operator fun plus(number2: Float): IMathNumber
    operator fun plus(mathNumber: IMathNumber): IMathNumber
    operator fun unaryPlus(): IMathNumber

    operator fun minus(number2: Int): IMathNumber
    operator fun minus(number2: Double): IMathNumber
    operator fun minus(number2: Float): IMathNumber
    operator fun minus(mathNumber: IMathNumber): IMathNumber
    operator fun unaryMinus(): IMathNumber

    operator fun times(number2: Int): IMathNumber
    operator fun times(number2: Double): IMathNumber
    operator fun times(number2: Float): IMathNumber
    operator fun times(mathNumber: IMathNumber): IMathNumber

    operator fun div(number2: Int): IMathNumber
    operator fun div(number2: Double): IMathNumber
    operator fun div(number2: Float): IMathNumber
    operator fun div(mathNumber: IMathNumber): IMathNumber

    operator fun compareTo(number2: Int): Int
    operator fun compareTo(number2: Double): Int
    operator fun compareTo(number2: Float): Int
    operator fun compareTo(mathNumber: IMathNumber): Int

    fun abs(): IMathNumber
    fun pow(number2: Int): IMathNumber
    fun sqrt(): IMathNumber
    fun cut(precisions: IPrecisions): IMathNumber
    fun reciprocal(): IMathNumber

    fun radians(): IMathNumber

    fun cos(): IMathNumber
    fun sin(): IMathNumber
    fun tan(): IMathNumber

    fun toInt(): Int
    fun toDouble(): Double
    fun toFloat(): Float

    companion object {
        fun new(number: Number): IMathNumber = Mediator.get(IMathNumber::class.java, ArgumentSet(arrayOf(number)))!!
    }
}