package io.github.gdrfgdrf.cutebedwars.abstracts.math.calculate

import io.github.gdrfgdrf.cutebedwars.abstracts.math.geometry.two.IPoint2D
import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.KotlinSingleton
import io.github.gdrfgdrf.multimodulemediator.annotation.Service

@Service("rectangles")
@KotlinSingleton
interface IRectangles {
    /**
     * 计算矩形的几何中心
     */
    fun geometricCenter(A: IPoint2D, B: IPoint2D): IPoint2D

    companion object {
        fun instance(): IRectangles = Mediator.get(IRectangles::class.java)!!
    }
}