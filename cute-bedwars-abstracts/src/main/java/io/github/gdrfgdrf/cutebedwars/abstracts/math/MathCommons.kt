package io.github.gdrfgdrf.cutebedwars.abstracts.math

operator fun Int.plus(mathNumber: IMathNumber): IMathNumber {
    return mathNumber + this
}

operator fun Int.minus(mathNumber: IMathNumber): IMathNumber {
    return this.mathNumber() - mathNumber
}

operator fun Int.times(mathNumber: IMathNumber): IMathNumber {
    return mathNumber * this
}

operator fun Int.div(mathNumber: IMathNumber): IMathNumber {
    return this.mathNumber() / mathNumber
}

operator fun Double.plus(mathNumber: IMathNumber): IMathNumber {
    return mathNumber + this
}

operator fun Double.minus(mathNumber: IMathNumber): IMathNumber {
    return this.mathNumber() - mathNumber
}

operator fun Double.times(mathNumber: IMathNumber): IMathNumber {
    return mathNumber * this
}

operator fun Double.div(mathNumber: IMathNumber): IMathNumber {
    return this.mathNumber() / mathNumber
}

operator fun Float.plus(mathNumber: IMathNumber): IMathNumber {
    return mathNumber + this
}

operator fun Float.minus(mathNumber: IMathNumber): IMathNumber {
    return this.mathNumber() - mathNumber
}

operator fun Float.times(mathNumber: IMathNumber): IMathNumber {
    return mathNumber * this
}

operator fun Float.div(mathNumber: IMathNumber): IMathNumber {
    return this.mathNumber() / mathNumber
}

fun minOf(a: IMathNumber, b: IMathNumber): IMathNumber {
    return if (a <= b) {
        a
    } else {
        b
    }
}

fun maxOf(a: IMathNumber, b: IMathNumber): IMathNumber {
    return if (a >= b) {
        a
    } else {
        b
    }
}

fun Number.mathNumber(): IMathNumber {
    return IMathNumber.new(this)
}