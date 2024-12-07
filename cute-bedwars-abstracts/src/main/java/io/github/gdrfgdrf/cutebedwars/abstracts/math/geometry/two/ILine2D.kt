package io.github.gdrfgdrf.cutebedwars.abstracts.math.geometry.two

import io.github.gdrfgdrf.cutebedwars.abstracts.math.IMathNumber
import io.github.gdrfgdrf.cutebedwars.abstracts.math.geometry.base.ILine
import io.github.gdrfgdrf.cutebedwars.abstracts.math.geometry.three.IPoint3D
import io.github.gdrfgdrf.cutebedwars.abstracts.math.geometry.three.IShape2D
import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.Service
import io.github.gdrfgdrf.multimodulemediator.bean.ArgumentSet

@Service("line_2d", singleton = false)
interface ILine2D : ILine, IShape2D {
    override val points2d: Array<IPoint2D>
        get() = arrayOf(start as IPoint2D, end as IPoint2D)

    companion object {
        fun new(start: IPoint2D, end: IPoint2D): ILine2D =
            Mediator.get(ILine2D::class.java, ArgumentSet(arrayOf(start, end)))!!

        fun new(
            x1: IMathNumber,
            y1: IMathNumber,
            x2: IMathNumber,
            y2: IMathNumber,
        ): ILine2D = new(
            IPoint2D.new(x1, y1),
            IPoint2D.new(x2, y2)
        )
    }
}