package io.github.gdrfgdrf.cutebedwars.math.common

import io.github.gdrfgdrf.cutebedwars.abstracts.math.IMathNumber
import io.github.gdrfgdrf.cutebedwars.abstracts.math.enums.IPrecisions
import io.github.gdrfgdrf.cutebedwars.math.enums.Precisions
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import io.github.gdrfgdrf.multimodulemediator.bean.ArgumentSet
import java.text.DecimalFormat
import kotlin.math.absoluteValue
import kotlin.math.pow
import kotlin.math.sqrt

@ServiceImpl("math_number", needArgument = true)
class MathNumber private constructor(override val number: Number) : IMathNumber {
    constructor(argumentSet: ArgumentSet): this(argumentSet.args[0] as Number)

    override operator fun plus(number2: Number): IMathNumber {
        val functions = map[number2::class.java] ?: throw UnsupportedOperationException("unknown type: ${number2::class.java}")
        val result = functions.plus(number, number2)
        return MathNumber(result)
    }

    override operator fun plus(mathNumber: IMathNumber): IMathNumber {
        return plus(mathNumber.number)
    }

    override fun unaryPlus(): IMathNumber {
        val functions = map[number::class.java] ?: throw UnsupportedOperationException()
        val result = functions.unaryPlus(number)
        return MathNumber(result)
    }

    override operator fun minus(number2: Number): IMathNumber {
        val functions = map[number2::class.java] ?: throw UnsupportedOperationException()
        val result = functions.minus(number, number2)
        return MathNumber(result)
    }

    override operator fun minus(mathNumber: IMathNumber): IMathNumber {
        return minus(mathNumber.number)
    }

    override fun unaryMinus(): IMathNumber {
        val functions = map[number::class.java] ?: throw UnsupportedOperationException()
        val result = functions.unaryMinus(number)
        return MathNumber(result)
    }

    override operator fun times(number2: Number): IMathNumber {
        val functions = map[number2::class.java] ?: throw UnsupportedOperationException()
        val result = functions.times(number, number2)
        return MathNumber(result)
    }

    override operator fun times(mathNumber: IMathNumber): IMathNumber {
        return times(mathNumber.number)
    }

    override fun div(number2: Number): IMathNumber {
        val functions = map[number2::class.java] ?: throw UnsupportedOperationException()
        val result = functions.div(number, number2)
        return MathNumber(result)
    }

    override fun div(mathNumber: IMathNumber): IMathNumber {
        return div(mathNumber.number)
    }

    override fun compareTo(number2: Number): Int {
        val functions = map[number2::class.java] ?: throw UnsupportedOperationException()
        val result = functions.compareTo(number, number2)
        return result
    }

    override fun compareTo(mathNumber: IMathNumber): Int {
        return compareTo(mathNumber.number)
    }

    override fun abs(): IMathNumber {
        val compareResult = compareTo(0)
        if (compareResult >= 0) {
            return MathNumber(number)
        }
        return unaryMinus()
    }

    override fun pow(number2: Int): IMathNumber {
        val functions = map[number::class.java] ?: throw UnsupportedOperationException()
        val result = functions.pow(number, number2)
        return MathNumber(result)
    }

    override fun sqrt(): IMathNumber {
        if (number is Int) {
            return MathNumber(sqrt(number.toDouble()))
        }
        if (number is Double) {
            return MathNumber(sqrt(number))
        }
        if (number is Float) {
            return MathNumber(sqrt(number))
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

    override fun equals(other: Any?): Boolean {
        if (other == number) {
            return false
        }
        if (other == number) {
            return false
        }
        if (other !is MathNumber) {
            return false
        }

        return number == other.number
    }

    override fun hashCode(): Int {
        return number.hashCode()
    }

    override fun toString(): String {
        return number.toString()
    }

    companion object {
        private val map = HashMap<Class<out Number>, Functions<out Number>>()
        init {
            val intFunctions = Functions<Int>().apply {
                plusByte = Int::plus
                plusDouble = Int::plus
                plusFloat = Int::plus
                plusInt = Int::plus
                plusLong = Int::plus
                plusShort = Int::plus
                unaryPlus = Int::unaryPlus

                minusByte = Int::minus
                minusDouble = Int::minus
                minusFloat = Int::minus
                minusInt = Int::minus
                minusLong = Int::minus
                minusShort = Int::minus
                unaryMinus = Int::unaryMinus

                timesByte = Int::times
                timesDouble = Int::times
                timesFloat = Int::times
                timesInt = Int::times
                timesLong = Int::times
                timesShort = Int::times

                divByte = Int::div
                divDouble = Int::div
                divFloat = Int::div
                divInt = Int::div
                divLong = Int::div
                divShort = Int::div

                compareToByte = Int::compareTo
                compareToDouble = Int::compareTo
                compareToFloat = Int::compareTo
                compareToInt = Int::compareTo
                compareToLong = Int::compareTo
                compareToShort = Int::compareTo
            }
            map[Int::class.java] = intFunctions
            map[java.lang.Integer::class.java] = intFunctions

            val doubleFunctions = Functions<Double>().apply {
                plusByte = Double::plus
                plusDouble = Double::plus
                plusFloat = Double::plus
                plusInt = Double::plus
                plusLong = Double::plus
                plusShort = Double::plus
                unaryPlus = Double::unaryPlus

                minusByte = Double::minus
                minusDouble = Double::minus
                minusFloat = Double::minus
                minusInt = Double::minus
                minusLong = Double::minus
                minusShort = Double::minus
                unaryMinus = Double::unaryMinus

                timesByte = Double::times
                timesDouble = Double::times
                timesFloat = Double::times
                timesInt = Double::times
                timesLong = Double::times
                timesShort = Double::times

                divByte = Double::div
                divDouble = Double::div
                divFloat = Double::div
                divInt = Double::div
                divLong = Double::div
                divShort = Double::div

                powDouble = Double::pow
                powInt = Double::pow

                compareToByte = Double::compareTo
                compareToDouble = Double::compareTo
                compareToFloat = Double::compareTo
                compareToInt = Double::compareTo
                compareToLong = Double::compareTo
                compareToShort = Double::compareTo
            }
            map[Double::class.java] = doubleFunctions
            map[java.lang.Double::class.java] = doubleFunctions

            val floatFunctions = Functions<Float>().apply {
                plusByte = Float::plus
                plusDouble = Float::plus
                plusFloat = Float::plus
                plusInt = Float::plus
                plusLong = Float::plus
                plusShort = Float::plus
                unaryPlus = Float::unaryPlus

                minusByte = Float::minus
                minusDouble = Float::minus
                minusFloat = Float::minus
                minusInt = Float::minus
                minusLong = Float::minus
                minusShort = Float::minus
                unaryMinus = Float::unaryMinus

                timesByte = Float::times
                timesDouble = Float::times
                timesFloat = Float::times
                timesInt = Float::times
                timesLong = Float::times
                timesShort = Float::times

                divByte = Float::div
                divDouble = Float::div
                divFloat = Float::div
                divInt = Float::div
                divLong = Float::div
                divShort = Float::div

                powFloat = Float::pow
                powInt = Float::pow

                compareToByte = Float::compareTo
                compareToDouble = Float::compareTo
                compareToFloat = Float::compareTo
                compareToInt = Float::compareTo
                compareToLong = Float::compareTo
                compareToShort = Float::compareTo
            }
            map[Float::class.java] = floatFunctions
            map[java.lang.Float::class.java] = floatFunctions
        }

        fun of(number: Number) = MathNumber(number)
    }

}