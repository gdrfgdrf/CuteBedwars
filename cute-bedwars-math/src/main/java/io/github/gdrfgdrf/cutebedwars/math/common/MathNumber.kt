package io.github.gdrfgdrf.cutebedwars.math.common

import io.github.gdrfgdrf.cutebedwars.abstracts.math.IMathNumber
import io.github.gdrfgdrf.cutebedwars.abstracts.math.enums.IPrecisions
import io.github.gdrfgdrf.cutebedwars.math.enums.Precisions
import java.text.DecimalFormat
import kotlin.math.pow

class MathNumber private constructor(override val number: Number) : IMathNumber {
    override operator fun plus(number2: Int): IMathNumber {
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

    override operator fun plus(number2: Double): IMathNumber {
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

    override operator fun plus(number2: Float): IMathNumber {
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
            return plus(-(mathNumber.number as Int))
        }
        if (mathNumber.number is Double) {
            return plus(-(mathNumber.number as Double))
        }
        if (mathNumber.number is Float) {
            return plus(-(mathNumber.number as Float))
        }
        throw UnsupportedOperationException()
    }

    override operator fun times(number2: Int): IMathNumber {
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

    override operator fun times(number2: Double): IMathNumber {
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

    override operator fun times(number2: Float): IMathNumber {
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
            return MathNumber(number / number2)
        }
        if (number is Double) {
            return MathNumber(number / number2)
        }
        if (number is Float) {
            return MathNumber(number / number2)
        }
        throw UnsupportedOperationException()
    }

    override fun div(number2: Double): IMathNumber {
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

    override fun div(number2: Float): IMathNumber {
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

    override fun div(mathNumber: IMathNumber): IMathNumber {
        if (mathNumber.number is Int) {
            return div(mathNumber.number as Int)
        }
        if (mathNumber.number is Double) {
            return times(mathNumber.number as Double)
        }
        if (mathNumber.number is Float) {
            return times(mathNumber.number as Float)
        }
        throw UnsupportedOperationException()
    }

    override fun pow(number2: Int): IMathNumber {
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

    override fun sqrt(): IMathNumber {
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

    companion object {
        fun of(number: Number) = MathNumber(number)
    }

}