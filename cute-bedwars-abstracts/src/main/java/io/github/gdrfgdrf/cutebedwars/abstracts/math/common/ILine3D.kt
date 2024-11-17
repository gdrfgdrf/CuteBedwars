package io.github.gdrfgdrf.cutebedwars.abstracts.math.common

import io.github.gdrfgdrf.cutebedwars.abstracts.math.IMathNumber
import io.github.gdrfgdrf.cutebedwars.abstracts.math.base.IPoint3D
import io.github.gdrfgdrf.cutebedwars.beans.pojo.common.Coordinate
import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.Service
import io.github.gdrfgdrf.multimodulemediator.bean.ArgumentSet

@Service("line_3d", singleton = false)
interface ILine3D {
    val start: IPoint3D
    val end: IPoint3D

    /**
     * 将一条线平均分成若干个等份，并保证每个等份的长度为同一个定值
     * 返回值为所有的切割点
     */
    fun divide(step: IMathNumber): List<IPoint3D>

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