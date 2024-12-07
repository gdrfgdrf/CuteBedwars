package io.github.gdrfgdrf.cutebedwars.math.calculate

import io.github.gdrfgdrf.cutebedwars.abstracts.math.calculate.IRectangles
import io.github.gdrfgdrf.cutebedwars.abstracts.math.geometry.two.IPoint2D
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl

@ServiceImpl("rectangles")
object Rectangles : IRectangles {
    override fun geometricCenter(A: IPoint2D, B: IPoint2D): IPoint2D {
        val x1 = A.x
        val y1 = A.y

        val x2 = B.x
        val y2 = B.y

        val resultX = (x1 + x2) / 2
        val resultY = (y1 + y2) / 2

        return IPoint2D.new(resultX, resultY)
    }
}