package io.github.gdrfgdrf.cutebedwars.math.common

fun Number.mathNumber(): MathNumber {
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