package io.github.gdrfgdrf.cutebedwars.math

class Cube(
    var length: Double,
    var width: Double,
    var height: Double
) {
    fun resize(length: Double, width: Double, height: Double) {
        this.length = length
        this.width = width
        this.height = height
    }

    fun baseArea(): Double {
        return length * width
    }

    fun volume(): Double {
        return baseArea() * height
    }

    fun square(): Boolean {
        return length == width && width == height && length == height
    }
}