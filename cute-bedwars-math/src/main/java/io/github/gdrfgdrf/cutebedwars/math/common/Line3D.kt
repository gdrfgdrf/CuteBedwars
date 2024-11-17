package io.github.gdrfgdrf.cutebedwars.math.common

import io.github.gdrfgdrf.cutebedwars.abstracts.math.IDistanceFormula
import io.github.gdrfgdrf.cutebedwars.abstracts.math.IMathNumber
import io.github.gdrfgdrf.cutebedwars.abstracts.math.base.IPoint3D
import io.github.gdrfgdrf.cutebedwars.abstracts.math.calculate.ILines
import io.github.gdrfgdrf.cutebedwars.abstracts.math.common.ILine3D
import io.github.gdrfgdrf.cutebedwars.abstracts.math.enums.IDimensions
import io.github.gdrfgdrf.cutebedwars.abstracts.math.enums.ISpaces
import io.github.gdrfgdrf.cutebedwars.beans.pojo.common.Coordinate
import io.github.gdrfgdrf.cutebedwars.math.calculate.Lines
import io.github.gdrfgdrf.cutebedwars.math.enums.Dimensions
import io.github.gdrfgdrf.cutebedwars.math.enums.Spaces
import io.github.gdrfgdrf.cutebedwars.math.formula.distance.DistanceFormula
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import io.github.gdrfgdrf.multimodulemediator.bean.ArgumentSet
import org.bukkit.Bukkit

@ServiceImpl("line_3d", needArgument = true)
class Line3D(
    override val start: IPoint3D,
    override val end: IPoint3D
) : ILine3D {
    constructor(argumentSet: ArgumentSet): this(
        argumentSet.args[0] as IPoint3D,
        argumentSet.args[1] as IPoint3D
    )

    override fun divide(step: IMathNumber): List<IPoint3D> {
        val space = Spaces.EUCLIDEAN
        val dimension = Dimensions.THREE

        val pos1 = Point3D.of(start.x, start.y, start.z)
        val pos2 = Point3D.of(end.x, end.y, end.z)

        var current = step
        val result = arrayListOf<IPoint3D>()
        while (true) {
            val C = Lines.findAPointCOnALineABInSpaceSuchThatTheDistanceOfBCIsAFixedValueD(pos1, pos2, current)
            val distance = DistanceFormula.calculate(space, dimension, C, pos1)
            if (distance < 0) {
                throw IllegalArgumentException("distance cannot be negative")
            }
            result.add(C)

            // 无法继续往下分
            if (distance <= step) {
                break
            }

            current += step
        }

        return result
    }

    override fun toString(): String {
        return "(${start.x}, ${start.y}, ${start.z}) -> (${end.x}, ${end.y}, ${end.z})"
    }



}