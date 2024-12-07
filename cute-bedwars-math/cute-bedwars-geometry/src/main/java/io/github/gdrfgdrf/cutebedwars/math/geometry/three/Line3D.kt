package io.github.gdrfgdrf.cutebedwars.math.geometry.three

import io.github.gdrfgdrf.cutebedwars.abstracts.math.IMathNumber
import io.github.gdrfgdrf.cutebedwars.abstracts.math.base.ILine
import io.github.gdrfgdrf.cutebedwars.abstracts.math.base.IPoint
import io.github.gdrfgdrf.cutebedwars.abstracts.math.geometry.three.IPoint3D
import io.github.gdrfgdrf.cutebedwars.abstracts.math.geometry.three.ILine3D
import io.github.gdrfgdrf.cutebedwars.math.calculate.Lines
import io.github.gdrfgdrf.cutebedwars.math.enums.Dimensions
import io.github.gdrfgdrf.cutebedwars.math.enums.Spaces
import io.github.gdrfgdrf.cutebedwars.math.formula.distance.DistanceFormula
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import io.github.gdrfgdrf.multimodulemediator.bean.ArgumentSet

@ServiceImpl("line_3d", needArgument = true)
class Line3D(
    override val start: IPoint3D,
    override val end: IPoint3D
) : ILine3D {
    constructor(argumentSet: ArgumentSet): this(
        argumentSet.args[0] as IPoint3D,
        argumentSet.args[1] as IPoint3D
    )

    override val length: IMathNumber by lazy {
        DistanceFormula.calculate(
            Spaces.EUCLIDEAN,
            Dimensions.THREE,
            start,
            end
        )
    }

    override val midPoint: IPoint by lazy {
        val x = (start.x + end.x) / 2
        val y = (start.y + end.y) / 2
        val z = (start.z + end.z) / 2
        IPoint3D.new(x, y, z)
    }

    override val half: ILine by lazy {
        Line3D(start, midPoint as IPoint3D)
    }

    init {
        if (!start.isValid() || !end.isValid()) {
            throw IllegalArgumentException("the point of start or the point of end is not valid")
        }
    }

    override fun divide(step: IMathNumber): List<IPoint> {
        val space = Spaces.EUCLIDEAN
        val dimension = Dimensions.THREE

        var current = step
        val result = arrayListOf<IPoint>()
        result.add(start)

        runCatching {
            while (true) {
                val C = Lines.findAPointCOnALineABInSpaceSuchThatTheDistanceOfBCIsAFixedValueD_ThreeDimension(start, end, current)

                val distance = DistanceFormula.calculate(space, dimension, C, start)
                if (distance < 0) {
                    throw IllegalArgumentException("distance cannot be negative")
                }
                result.add(C)

                // 无法继续往下分时 break
                if (distance <= step) {
                    break
                }

                current += step
            }
        }.onFailure {
            result.add(end)
            return result
        }

        result.add(end)
        return result
    }

    override fun toString(): String {
        return "$start -> $end (Line3D)"
    }



}