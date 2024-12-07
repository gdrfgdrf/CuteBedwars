package io.github.gdrfgdrf.cutebedwars.math.geometry.two

import io.github.gdrfgdrf.cutebedwars.abstracts.math.IMathNumber
import io.github.gdrfgdrf.cutebedwars.abstracts.math.geometry.base.ILine
import io.github.gdrfgdrf.cutebedwars.abstracts.math.geometry.base.IPoint
import io.github.gdrfgdrf.cutebedwars.abstracts.math.geometry.two.ILine2D
import io.github.gdrfgdrf.cutebedwars.abstracts.math.geometry.two.IPoint2D
import io.github.gdrfgdrf.cutebedwars.math.calculate.Lines
import io.github.gdrfgdrf.cutebedwars.math.enums.Dimensions
import io.github.gdrfgdrf.cutebedwars.math.enums.Spaces
import io.github.gdrfgdrf.cutebedwars.math.formula.distance.DistanceFormula
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import io.github.gdrfgdrf.multimodulemediator.bean.ArgumentSet

@ServiceImpl("line_2d", needArgument = true)
class Line2D(
    override val start: IPoint2D,
    override val end: IPoint2D
) : ILine2D {
    constructor(argumentSet: ArgumentSet): this(
        argumentSet.args[0] as IPoint2D,
        argumentSet.args[1] as IPoint2D
    )

    override val length: IMathNumber by lazy {
        DistanceFormula.calculate(
            Spaces.EUCLIDEAN,
            Dimensions.TWO,
            start,
            end
        )
    }

    override val midPoint: IPoint by lazy {
        val x = (start.x + end.x) / 2
        val y = (start.y + end.y) / 2
        IPoint2D.new(x, y)
    }

    override val half: ILine by lazy {
        Line2D(start, midPoint as IPoint2D)
    }

    init {
        if (!start.isValid() || !end.isValid()) {
            throw IllegalArgumentException("the point of start or the point of end is not valid")
        }
        if (start == end) {
            throw IllegalArgumentException("start and end cannot be the same")
        }
    }

    override fun divide2d(step: IMathNumber): List<IPoint2D> {
        val space = Spaces.EUCLIDEAN
        val dimension = Dimensions.TWO

        var current = step
        val result = arrayListOf<IPoint2D>()
        result.add(start)

        runCatching {
            while (true) {
                val C = Lines.findAPointCOnALineABInSpaceSuchThatTheDistanceOfBCIsAFixedValueD_TwoDimension(start, end, current)

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

}