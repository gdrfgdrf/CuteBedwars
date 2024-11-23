package io.github.gdrfgdrf.cutebedwars.math.common

import io.github.gdrfgdrf.cutebedwars.abstracts.math.IMathNumber
import io.github.gdrfgdrf.cutebedwars.abstracts.math.base.IPoint2D
import io.github.gdrfgdrf.cutebedwars.abstracts.math.base.IPoint3D
import io.github.gdrfgdrf.cutebedwars.abstracts.math.common.ICircle2D
import io.github.gdrfgdrf.cutebedwars.math.calculate.Circles

class Circle2D(
    override val center: IPoint2D,
    override val R: IMathNumber,
    override val half: Boolean = false
) : ICircle2D {
    init {
        if (R <= 0) {
            throw IllegalArgumentException("the radius of a circle cannot less or equal than zero")
        }
    }

    override fun divide(step: IMathNumber): List<IPoint2D> {
        var current = MathNumber.of(0)
        val result = arrayListOf<IPoint2D>()

        while (true) {
            val point = Circles.getAPointOnTheCircleFromTheAngle(center.x, center.y, R, current)
            if (point.x.toDouble().isNaN() || point.y.toDouble().isNaN()) {
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

    override fun divide3d(step: IMathNumber, y: IMathNumber): List<IPoint3D> {
        var current = MathNumber.of(0)
        val result = arrayListOf<IPoint3D>()

        while (true) {
            val point = Circles.getAPointOnTheCircleFromTheAngle(center.x, center.y, R, current)
            if (point.x.toDouble().isNaN() || point.y.toDouble().isNaN()) {
                break
            }
            val point3d = IPoint3D.new(point.x, y, point.y)
            result.add(point3d)

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
}