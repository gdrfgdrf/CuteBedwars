package io.github.gdrfgdrf.cutebedwars.math

class Rectangle(
    var length: Double,
    var width: Double
) {
    fun resize(length: Double, width: Double) {
        this.length = length
        this.width = width
    }

    fun circumference(): Double {
        return 2 * (length + width)
    }

    fun area(): Double {
        return length * width
    }

    fun square(): Boolean {
        return length == width
    }

}