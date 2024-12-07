package io.github.gdrfgdrf.cutebedwars.abstracts.math.geometry.three

import io.github.gdrfgdrf.cutebedwars.abstracts.math.IMathNumber
import io.github.gdrfgdrf.cutebedwars.abstracts.math.geometry.base.ILine
import io.github.gdrfgdrf.cutebedwars.abstracts.math.geometry.two.IShape3D
import io.github.gdrfgdrf.cutebedwars.beans.pojo.common.Coordinate
import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.Service
import io.github.gdrfgdrf.multimodulemediator.bean.ArgumentSet
import java.util.*

@Service("line_3d", singleton = false)
interface ILine3D : ILine, IShape3D {
    override val points3d: Array<IPoint3D>
        get() = arrayOf(start as IPoint3D, end as IPoint3D)

    companion object {
        fun new(start: IPoint3D, end: IPoint3D): ILine3D =
            Mediator.get(ILine3D::class.java, ArgumentSet(arrayOf(start, end)))!!

        fun new(
            x1: IMathNumber,
            y1: IMathNumber,
            z1: IMathNumber,
            x2: IMathNumber,
            y2: IMathNumber,
            z2: IMathNumber
        ): ILine3D = new(
            IPoint3D.new(x1, y1, z1),
            IPoint3D.new(x2, y2, z2)
        )

        fun new(start: Coordinate, end: Coordinate): ILine3D = new(
            IPoint3D.new(start),
            IPoint3D.new(end)
        )
    }
}