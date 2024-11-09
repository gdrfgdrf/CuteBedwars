package io.github.gdrfgdrf.cutebedwars.math.common

import io.github.gdrfgdrf.cutebedwars.abstracts.math.IMathNumber

operator fun Int.plus(mathNumber: IMathNumber): IMathNumber {
    return mathNumber + this
}

operator fun Int.minus(mathNumber: IMathNumber): IMathNumber {
    return MathNumber.of(this) - mathNumber
}

operator fun Int.times(mathNumber: IMathNumber): IMathNumber {
    return mathNumber * this
}

operator fun Int.div(mathNumber: IMathNumber): IMathNumber {
    return MathNumber.of(this) / mathNumber
}

operator fun Double.plus(mathNumber: IMathNumber): IMathNumber {
    return mathNumber + this
}

operator fun Double.minus(mathNumber: IMathNumber): IMathNumber {
    return MathNumber.of(this) - mathNumber
}

operator fun Double.times(mathNumber: IMathNumber): IMathNumber {
    return mathNumber * this
}

operator fun Double.div(mathNumber: IMathNumber): IMathNumber {
    return MathNumber.of(this) / mathNumber
}

operator fun Float.plus(mathNumber: IMathNumber): IMathNumber {
    return mathNumber + this
}

operator fun Float.minus(mathNumber: IMathNumber): IMathNumber {
    return MathNumber.of(this) - mathNumber
}

operator fun Float.times(mathNumber: IMathNumber): IMathNumber {
    return mathNumber * this
}

operator fun Float.div(mathNumber: IMathNumber): IMathNumber {
    return MathNumber.of(this) / mathNumber
}

fun Number.mathNumber(): IMathNumber {
    return MathNumber.of(this)
}

fun arg(any: Any): Argument {
    return Argument.of(any)
}

fun args(vararg any: Any): Arguments {
    return Arguments.of(*any)
}

fun argsFrom(vararg argument: Argument): Arguments {
    return Arguments.from(*argument)
}