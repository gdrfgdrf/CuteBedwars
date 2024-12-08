package io.github.gdrfgdrf.cutebedwars.math.common

import io.github.gdrfgdrf.cutebedwars.abstracts.math.IMathNumber
import io.github.gdrfgdrf.cutebedwars.abstracts.math.common.div
import io.github.gdrfgdrf.cutebedwars.abstracts.math.enums.IPrecisions
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import io.github.gdrfgdrf.multimodulemediator.bean.ArgumentSet
import java.text.DecimalFormat
import kotlin.math.*

@ServiceImpl("math_number", needArgument = true, instanceGetter = "of")
class MathNumber private constructor(override val number: Number) : IMathNumber, Number() {
    override operator fun plus(number2: Int): IMathNumber {
        if (number is Int) {
            return of(number + number2)
        }
        if (number is Double) {
            return of(number + number2)
        }
        if (number is Float) {
            return of(number + number2)
        }
        throw UnsupportedOperationException()
    }

    override operator fun plus(number2: Double): IMathNumber {
        if (number is Int) {
            return of(number + number2)
        }
        if (number is Double) {
            return of(number + number2)
        }
        if (number is Float) {
            return of(number + number2)
        }
        throw UnsupportedOperationException()
    }

    override operator fun plus(number2: Float): IMathNumber {
        if (number is Int) {
            return of(number + number2)
        }
        if (number is Double) {
            return of(number + number2)
        }
        if (number is Float) {
            return of(number + number2)
        }
        throw UnsupportedOperationException()
    }

    override operator fun plus(mathNumber: IMathNumber): IMathNumber {
        if (mathNumber.number is Int) {
            return plus(mathNumber.number as Int)
        }
        if (mathNumber.number is Double) {
            return plus(mathNumber.number as Double)
        }
        if (mathNumber.number is Float) {
            return plus(mathNumber.number as Float)
        }
        throw UnsupportedOperationException()
    }

    override fun unaryPlus(): IMathNumber {
        if (number is Int) {
            return of(+number)
        }
        if (number is Double) {
            return of(+number)
        }
        if (number is Float) {
            return of(+number)
        }
        throw UnsupportedOperationException()
    }

    override operator fun minus(number2: Int): IMathNumber {
        return plus(-number2)
    }

    override operator fun minus(number2: Double): IMathNumber {
        return plus(-number2)
    }

    override operator fun minus(number2: Float): IMathNumber {
        return plus(-number2)
    }

    override operator fun minus(mathNumber: IMathNumber): IMathNumber {
        if (mathNumber.number is Int) {
            return plus(-mathNumber)
        }
        if (mathNumber.number is Double) {
            return plus(-mathNumber)
        }
        if (mathNumber.number is Float) {
            return plus(-mathNumber)
        }
        throw UnsupportedOperationException()
    }

    override fun unaryMinus(): IMathNumber {
        if (number is Int) {
            return of(-number)
        }
        if (number is Double) {
            return of(-number)
        }
        if (number is Float) {
            return of(-number)
        }
        throw UnsupportedOperationException()
    }

    override operator fun times(number2: Int): IMathNumber {
        if (number is Int) {
            return of(number * number2)
        }
        if (number is Double) {
            return of(number * number2)
        }
        if (number is Float) {
            return of(number * number2)
        }
        throw UnsupportedOperationException()
    }

    override operator fun times(number2: Double): IMathNumber {
        if (number is Int) {
            return of(number * number2)
        }
        if (number is Double) {
            return of(number * number2)
        }
        if (number is Float) {
            return of(number * number2)
        }
        throw UnsupportedOperationException()
    }

    override operator fun times(number2: Float): IMathNumber {
        if (number is Int) {
            return of(number * number2)
        }
        if (number is Double) {
            return of(number * number2)
        }
        if (number is Float) {
            return of(number * number2)
        }
        throw UnsupportedOperationException()
    }

    override operator fun times(mathNumber: IMathNumber): IMathNumber {
        if (mathNumber.number is Int) {
            return times(mathNumber.number as Int)
        }
        if (mathNumber.number is Double) {
            return times(mathNumber.number as Double)
        }
        if (mathNumber.number is Float) {
            return times(mathNumber.number as Float)
        }
        throw UnsupportedOperationException()
    }

    override fun div(number2: Int): IMathNumber {
        if (number is Int) {
            return of(number / number2)
        }
        if (number is Double) {
            return of(number / number2)
        }
        if (number is Float) {
            return of(number / number2)
        }
        throw UnsupportedOperationException()
    }

    override fun div(number2: Double): IMathNumber {
        if (number is Int) {
            return of(number / number2)
        }
        if (number is Double) {
            return of(number / number2)
        }
        if (number is Float) {
            return of(number / number2)
        }
        throw UnsupportedOperationException()
    }

    override fun div(number2: Float): IMathNumber {
        if (number is Int) {
            return of(number / number2)
        }
        if (number is Double) {
            return of(number / number2)
        }
        if (number is Float) {
            return of(number / number2)
        }
        throw UnsupportedOperationException()
    }

    override fun div(mathNumber: IMathNumber): IMathNumber {
        if (mathNumber.number is Int) {
            return div(mathNumber.number as Int)
        }
        if (mathNumber.number is Double) {
            return div(mathNumber.number as Double)
        }
        if (mathNumber.number is Float) {
            return div(mathNumber.number as Float)
        }
        throw UnsupportedOperationException()
    }

    override fun compareTo(number2: Int): Int {
        if (number is Int) {
            return number.compareTo(number2)
        }
        if (number is Double) {
            return number.compareTo(number2)
        }
        if (number is Float) {
            return number.compareTo(number2)
        }
        throw UnsupportedOperationException()
    }

    override fun compareTo(number2: Double): Int {
        if (number is Int) {
            return number.compareTo(number2)
        }
        if (number is Double) {
            return number.compareTo(number2)
        }
        if (number is Float) {
            return number.compareTo(number2)
        }
        throw UnsupportedOperationException()
    }

    override fun compareTo(number2: Float): Int {
        if (number is Int) {
            return number.compareTo(number2)
        }
        if (number is Double) {
            return number.compareTo(number2)
        }
        if (number is Float) {
            return number.compareTo(number2)
        }
        throw UnsupportedOperationException()
    }

    override fun compareTo(mathNumber: IMathNumber): Int {
        if (mathNumber.number is Int) {
            return compareTo(mathNumber.number as Int)
        }
        if (mathNumber.number is Double) {
            return compareTo(mathNumber.number as Double)
        }
        if (mathNumber.number is Float) {
            return compareTo(mathNumber.number as Float)
        }
        throw UnsupportedOperationException()
    }

    override fun abs(): IMathNumber {
        if (number is Int) {
            return of(number.absoluteValue)
        }
        if (number is Double) {
            return of(number.absoluteValue)
        }
        if (number is Float) {
            return of(number.absoluteValue)
        }
        throw UnsupportedOperationException()
    }

    override fun pow(number2: Int): IMathNumber {
        if (number is Int) {
            return of(number.toDouble().pow(number2))
        }
        if (number is Double) {
            return of(number.pow(number2))
        }
        if (number is Float) {
            return of(number.pow(number2))
        }
        throw UnsupportedOperationException()
    }

    override fun sqrt(): IMathNumber {
        if (number is Int) {
            return of(kotlin.math.sqrt(number.toDouble()))
        }
        if (number is Double) {
            return of(kotlin.math.sqrt(number))
        }
        if (number is Float) {
            return of(kotlin.math.sqrt(number))
        }
        throw UnsupportedOperationException()
    }

    override fun cut(precisions: IPrecisions): IMathNumber {
        val decimalFormat = DecimalFormat(precisions.pattern)
        if (number is Int) {
            return of(decimalFormat.format(number).toDouble())
        }
        if (number is Double) {
            return of(decimalFormat.format(number).toDouble())
        }
        if (number is Float) {
            return of(decimalFormat.format(number).toDouble())
        }
        throw UnsupportedOperationException()
    }

    override fun reciprocal(): IMathNumber {
        return 1 / this
    }

    override fun radians(): IMathNumber {
        if (number is Int) {
            return of(Math.toRadians(toDouble()))
        }
        if (number is Double) {
            return of(Math.toRadians(number))
        }
        if (number is Float) {
            return of(Math.toRadians(toDouble()))
        }
        throw UnsupportedOperationException()
    }

    override fun cos(): IMathNumber {
        if (number is Int) {
            return of(cos(toDouble()))
        }
        if (number is Double) {
            return of(cos(number))
        }
        if (number is Float) {
            return of(cos(number))
        }
        throw UnsupportedOperationException()
    }

    override fun sin(): IMathNumber {
        if (number is Int) {
            return of(sin(toDouble()))
        }
        if (number is Double) {
            return of(sin(number))
        }
        if (number is Float) {
            return of(sin(number))
        }
        throw UnsupportedOperationException()
    }

    override fun tan(): IMathNumber {
        if (number is Int) {
            return of(tan(toDouble()))
        }
        if (number is Double) {
            return of(tan(number))
        }
        if (number is Float) {
            return of(tan(number))
        }
        throw UnsupportedOperationException()
    }

    override fun toInt(): Int {
        return number.toInt()
    }

    override fun toLong(): Long {
        return number.toLong()
    }

    override fun toShort(): Short {
        return number.toShort()
    }

    override fun toDouble(): Double {
        return number.toDouble()
    }

    override fun toFloat(): Float {
        return number.toFloat()
    }

    override fun toByte(): Byte {
        return number.toByte()
    }

    override fun equals(other: Any?): Boolean {
        if (other == number) {
            return false
        }
        if (other !is MathNumber) {
            return false
        }

        if (number is Int && other.number is Int) {
            return number == other.number
        }
        if (number is Int && other.number is Double) {
            return number == other.number
        }
        if (number is Int && other.number is Float) {
            return number == other.number
        }
        if (number is Double && other.number is Int) {
            return number == other.number
        }
        if (number is Double && other.number is Double) {
            return number == other.number
        }
        if (number is Double && other.number is Float) {
            return number == other.number
        }
        if (number is Float && other.number is Int) {
            return number == other.number
        }
        if (number is Float && other.number is Double) {
            return number == other.number
        }
        if (number is Float && other.number is Float) {
            return number == other.number
        }

        return false
    }

    override fun hashCode(): Int {
        return number.hashCode()
    }

    override fun toString(): String {
        return number.toString()
    }

    companion object {
        fun of(number: Number): IMathNumber {
            return MathNumber(number)
        }

        @JvmStatic
        fun of(argumentSet: ArgumentSet): IMathNumber {
            return of(argumentSet.args[0] as Number)
        }
    }

}