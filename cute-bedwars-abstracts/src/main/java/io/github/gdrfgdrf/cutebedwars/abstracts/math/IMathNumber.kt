package io.github.gdrfgdrf.cutebedwars.abstracts.math

import io.github.gdrfgdrf.cutebedwars.abstracts.math.enums.IPrecisions
import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.Service
import io.github.gdrfgdrf.multimodulemediator.bean.ArgumentSet

@Service("math_number", singleton = false)
interface IMathNumber {
    val number: Number
    operator fun plus(number2: Number): IMathNumber
    operator fun plus(mathNumber: IMathNumber): IMathNumber
    operator fun unaryPlus(): IMathNumber

    operator fun minus(number2: Number): IMathNumber
    operator fun minus(mathNumber: IMathNumber): IMathNumber
    operator fun unaryMinus(): IMathNumber

    operator fun times(number2: Number): IMathNumber
    operator fun times(mathNumber: IMathNumber): IMathNumber

    operator fun div(number2: Number): IMathNumber
    operator fun div(mathNumber: IMathNumber): IMathNumber

    operator fun compareTo(number2: Number): Int
    operator fun compareTo(mathNumber: IMathNumber): Int

    fun abs(): IMathNumber
    fun pow(number2: Int): IMathNumber
    fun sqrt(): IMathNumber
    fun cut(precisions: IPrecisions): IMathNumber
    fun reciprocal(): IMathNumber

    companion object {
        fun new(number: Number): IMathNumber = Mediator.get(IMathNumber::class.java, ArgumentSet(arrayOf(number)))!!
    }
}