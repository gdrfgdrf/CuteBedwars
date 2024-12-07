package io.github.gdrfgdrf.cutebedwars.abstracts.math.geometry.two

import io.github.gdrfgdrf.cutebedwars.abstracts.math.IMathNumber
import io.github.gdrfgdrf.cutebedwars.abstracts.math.base.IPoint
import io.github.gdrfgdrf.cutebedwars.abstracts.math.common.mathNumber
import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.Service
import io.github.gdrfgdrf.multimodulemediator.bean.ArgumentSet

@Service("point_2d", singleton = false)
interface IPoint2D : IPoint {
    val x: IMathNumber
    val y: IMathNumber

    companion object {
        fun new(x: IMathNumber, y: IMathNumber): IPoint2D =
            Mediator.get(IPoint2D::class.java, ArgumentSet(arrayOf(x, y)))!!

        fun new(x: Number, y: Number): IPoint2D = new(
            x.mathNumber(),
            y.mathNumber()
        )
    }
}