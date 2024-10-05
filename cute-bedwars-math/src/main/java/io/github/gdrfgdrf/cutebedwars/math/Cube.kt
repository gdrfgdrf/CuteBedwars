package io.github.gdrfgdrf.cutebedwars.math

class Cube(
    var length: Double,
    var width: Double,
    var heigth: Double
) {
    fun resize(length: Double, width: Double, height: Double) {
        this.length = length
        this.width = width
        this.heigth = height
    }

    fun baseArea(): Double {
        return length * width
    }

    fun volume(): Double {
        return baseArea() * heigth
    }

    fun square(): Boolean {
        return length == width && width == heigth && length == heigth
    }
}