package io.github.gdrfgdrf.cutebedwars.math.geometry.two

import io.github.gdrfgdrf.cutebedwars.abstracts.math.IMathNumber
import io.github.gdrfgdrf.cutebedwars.abstracts.math.geometry.base.IPoint
import io.github.gdrfgdrf.cutebedwars.abstracts.math.geometry.two.IPoint2D
import io.github.gdrfgdrf.cutebedwars.abstracts.math.geometry.three.IPoint3D
import io.github.gdrfgdrf.cutebedwars.abstracts.math.geometry.two.ICircle2D
import io.github.gdrfgdrf.cutebedwars.abstracts.math.common.mathNumber
import io.github.gdrfgdrf.cutebedwars.math.calculate.Circles
import io.github.gdrfgdrf.cutebedwars.math.common.MathNumber
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import io.github.gdrfgdrf.multimodulemediator.bean.ArgumentSet

@ServiceImpl("circle_2d", needArgument = true)
class Circle2D(
    override val center: IPoint2D,
    override val R: IMathNumber,
    override val half: Boolean = false
) : ICircle2D {
    constructor(argumentSet: ArgumentSet): this(
        argumentSet.args[0] as IPoint2D,
        argumentSet.args[1] as IMathNumber,
        argumentSet.args[2] as Boolean
    )

    init {
        if (!center.isValid()) {
            throw IllegalArgumentException("the point of center is not valid")
        }
        if (R <= 0) {
            throw IllegalArgumentException("the radius of a circle cannot less or equal than zero")
        }
    }

    override fun divide2d(step: IMathNumber): List<IPoint2D> {
        return divide(step, 0.mathNumber())
    }

    override fun divide(step: IMathNumber, offset: IMathNumber): List<IPoint2D> {
        var current = MathNumber.of(0)
        val result = arrayListOf<IPoint2D>()

        while (true) {
            val point = Circles.getAPointOnTheCircleFromTheAngle(
                center.x,
                center.y,
                R,
                current,
                offset
            )
            if (!point.isValid()) {
                break
            }
            result.add(point)

            if (!half) {
                if (-current + 360 <= step) {
                    break
                }
            } else {
                if (-current + 180 <= step) {
                    break
                }
            }

            current += step
        }

        return result
    }

    override fun divide(step: IMathNumber, y: IMathNumber, offset: IMathNumber): List<IPoint3D> {
        var current = MathNumber.of(0)
        val result = arrayListOf<IPoint3D>()

        while (true) {
            val point = Circles.getAPointOnTheCircleFromTheAngle(
                center.x,
                center.y,
                R,
                current,
                offset
            )
            if (!point.isValid()) {
                break
            }
            val point3d = IPoint3D.new(point.x, y, point.y)
            result.add(point3d)

            if (!half) {
                if (-current + 360 < step) {
                    break
                }
            } else {
                if (-current + 180 < step) {
                    break
                }
            }

            current += step
        }

        return result
    }
}