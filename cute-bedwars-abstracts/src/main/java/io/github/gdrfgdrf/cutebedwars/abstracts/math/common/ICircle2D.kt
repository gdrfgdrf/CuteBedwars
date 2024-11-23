package io.github.gdrfgdrf.cutebedwars.abstracts.math.common

import io.github.gdrfgdrf.cutebedwars.abstracts.math.IMathNumber
import io.github.gdrfgdrf.cutebedwars.abstracts.math.base.IPoint2D
import io.github.gdrfgdrf.cutebedwars.abstracts.math.base.IPoint3D
import io.github.gdrfgdrf.cutebedwars.abstracts.math.mathNumber
import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.Service
import io.github.gdrfgdrf.multimodulemediator.bean.ArgumentSet

@Service("circle_2d", singleton = false)
interface ICircle2D {
    val center: IPoint2D
    val R: IMathNumber
    val half: Boolean

    /**
     * 按照一定的角度进行步进获取圆上的点，若要进行旋转则需要 offset，方向为顺时针
     */
    fun divide(step: IMathNumber, offset: IMathNumber = 0.mathNumber()): List<IPoint2D>

    /**
     * 按照一定的角度进行步进获取圆上的点，返回的点集的 y 轴将始终为给定的参数 y，若要进行旋转则需要 offset，方向为顺时针
     */
    fun divide(step: IMathNumber, y: IMathNumber, offset: IMathNumber = 0.mathNumber()): List<IPoint3D>

    companion object {
        fun new(
            center: IPoint2D,
            R: IMathNumber,
            half: Boolean = false
        ): ICircle2D = Mediator.get(
            ICircle2D::class.java, ArgumentSet(
                arrayOf(center, R, half)
            )
        )!!

        fun new(
            x0: IMathNumber,
            y0: IMathNumber,
            R: IMathNumber,
            half: Boolean = false
        ): ICircle2D =
            new(IPoint2D.new(x0, y0), R, half)
    }
}