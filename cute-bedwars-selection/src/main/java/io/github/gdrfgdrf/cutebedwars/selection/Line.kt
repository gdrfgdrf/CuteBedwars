package io.github.gdrfgdrf.cutebedwars.selection

import io.github.gdrfgdrf.cutebedwars.abstracts.math.IDistanceFormula
import io.github.gdrfgdrf.cutebedwars.abstracts.math.IMathNumber
import io.github.gdrfgdrf.cutebedwars.abstracts.math.base.IPoint3D
import io.github.gdrfgdrf.cutebedwars.abstracts.math.calculate.ILines
import io.github.gdrfgdrf.cutebedwars.abstracts.math.enums.IDimensions
import io.github.gdrfgdrf.cutebedwars.abstracts.math.enums.ISpaces
import io.github.gdrfgdrf.cutebedwars.beans.pojo.common.Coordinate

class Line(
    val start: Coordinate,
    val end: Coordinate
) {
    /**
     * 将一条线平均分成若干个等份，并保证每个等份的长度为同一个定值
     */
    fun dividePoint(step: Double): List<Coordinate> {
        val space = ISpaces.valueOf("EUCLIDEAN")
        val dimension = IDimensions.valueOf("THREE")

        val pos1 = IPoint3D.new(start.x, start.y, start.z)
        val pos2 = IPoint3D.new(end.x, end.y, end.z)

        var current = 0.0
        val result = arrayListOf<Coordinate>()
        while (true) {
            val C = ILines.instance().findAPointCOnALineABInSpaceSuchThatTheDistanceOfBCIsAFixedValueD(pos1, pos2, IMathNumber.new(step))
            val distance = IDistanceFormula.instance().calculate(space, dimension, C, pos2)
            if (distance < 0) {
                throw IllegalArgumentException("distance cannot be negative")
            }
            // 无法继续往下分
            if (distance >= step) {
                break
            }

            val coordinate = Coordinate()
            coordinate.x = C.x.number.toDouble()
            coordinate.y = C.y.number.toDouble()
            coordinate.z = C.z.number.toDouble()
            result.add(coordinate)

            current += step
        }

        return result
    }

    override fun toString(): String {
        return "(${start.x}, ${start.y}, ${start.z}) -> (${end.x}, ${end.y}, ${end.z})"
    }



}