package io.github.gdrfgdrf.cutebedwars.math.common

import io.github.gdrfgdrf.cutebedwars.math.enums.Precisions
import java.text.DecimalFormat
import kotlin.math.pow

class MathNumber(val number: Number) {
    operator fun plus(number2: Int): MathNumber {
        if (number is Int) {
            return MathNumber(number + number2)
        }
        if (number is Double) {
            return MathNumber(number + number2)
        }
        if (number is Float) {
            return MathNumber(number + number2)
        }
        throw UnsupportedOperationException()
    }

    operator fun plus(number2: Double): MathNumber {
        if (number is Int) {
            return MathNumber(number + number2)
        }
        if (number is Double) {
            return MathNumber(number + number2)
        }
        if (number is Float) {
            return MathNumber(number + number2)
        }
        throw UnsupportedOperationException()
    }

    operator fun plus(number2: Float): MathNumber {
        if (number is Int) {
            return MathNumber(number + number2)
        }
        if (number is Double) {
            return MathNumber(number + number2)
        }
        if (number is Float) {
            return MathNumber(number + number2)
        }
        throw UnsupportedOperationException()
    }

    operator fun plus(mathNumber: MathNumber): MathNumber {
        if (mathNumber.number is Int) {
            return plus(mathNumber.number)
        }
        if (mathNumber.number is Double) {
            return plus(mathNumber.number)
        }
        if (mathNumber.number is Float) {
            return plus(mathNumber.number)
        }
        throw UnsupportedOperationException()
    }

    operator fun minus(number2: Int): MathNumber {
        return plus(-number2)
    }

    operator fun minus(number2: Double): MathNumber {
        return plus(-number2)
    }

    operator fun minus(number2: Float): MathNumber {
        return plus(-number2)
    }

    operator fun minus(mathNumber: MathNumber): MathNumber {
        if (mathNumber.number is Int) {
            return plus(-mathNumber.number)
        }
        if (mathNumber.number is Double) {
            return plus(-mathNumber.number)
        }
        if (mathNumber.number is Float) {
            return plus(-mathNumber.number)
        }
        throw UnsupportedOperationException()
    }

    operator fun times(number2: Int): MathNumber {
        if (number is Int) {
            return MathNumber(number * number2)
        }
        if (number is Double) {
            return MathNumber(number * number2)
        }
        if (number is Float) {
            return MathNumber(number * number2)
        }
        throw UnsupportedOperationException()
    }

    operator fun times(number2: Double): MathNumber {
        if (number is Int) {
            return MathNumber(number * number2)
        }
        if (number is Double) {
            return MathNumber(number * number2)
        }
        if (number is Float) {
            return MathNumber(number * number2)
        }
        throw UnsupportedOperationException()
    }

    operator fun times(number2: Float): MathNumber {
        if (number is Int) {
            return MathNumber(number * number2)
        }
        if (number is Double) {
            return MathNumber(number * number2)
        }
        if (number is Float) {
            return MathNumber(number * number2)
        }
        throw UnsupportedOperationException()
    }

    operator fun times(mathNumber: MathNumber): MathNumber {
        if (mathNumber.number is Int) {
            return times(-mathNumber.number)
        }
        if (mathNumber.number is Double) {
            return times(-mathNumber.number)
        }
        if (mathNumber.number is Float) {
            return times(-mathNumber.number)
        }
        throw UnsupportedOperationException()
    }

    fun pow(number2: Int): MathNumber {
        if (number is Int) {
            return MathNumber(number.toDouble().pow(number2))
        }
        if (number is Double) {
            return MathNumber(number.pow(number2))
        }
        if (number is Float) {
            return MathNumber(number.pow(number2))
        }
        throw UnsupportedOperationException()
    }

    fun sqrt(): MathNumber {
        if (number is Int) {
            return MathNumber(kotlin.math.sqrt(number.toDouble()))
        }
        if (number is Double) {
            return MathNumber(kotlin.math.sqrt(number))
        }
        if (number is Float) {
            return MathNumber(kotlin.math.sqrt(number))
        }
        throw UnsupportedOperationException()
    }

    fun cut(precisions: Precisions): MathNumber {
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

    companion object {
        fun of(number: Number) = MathNumber(number)
    }

}