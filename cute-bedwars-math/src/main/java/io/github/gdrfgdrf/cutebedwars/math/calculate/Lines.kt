package io.github.gdrfgdrf.cutebedwars.math.calculate

import io.github.gdrfgdrf.cutebedwars.abstracts.math.IMathNumber
import io.github.gdrfgdrf.cutebedwars.abstracts.math.geometry.three.IPoint3D
import io.github.gdrfgdrf.cutebedwars.abstracts.math.calculate.ILines
import io.github.gdrfgdrf.cutebedwars.abstracts.math.common.minus
import io.github.gdrfgdrf.cutebedwars.abstracts.math.geometry.two.IPoint2D
import io.github.gdrfgdrf.cutebedwars.math.enums.Dimensions
import io.github.gdrfgdrf.cutebedwars.math.enums.Spaces
import io.github.gdrfgdrf.cutebedwars.math.formula.distance.DistanceFormula
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl

@ServiceImpl("lines")
object Lines : ILines {
    override fun findAPointCOnALineABInSpaceSuchThatTheDistanceOfBCIsAFixedValueD_ThreeDimension(A: IPoint3D, B: IPoint3D, D: IMathNumber): IPoint3D {
        val norm = DistanceFormula.calculate(Spaces.EUCLIDEAN, Dimensions.THREE, A, B)

        val x1 = A.x
        val y1 = A.y
        val z1 = A.z

        val x2 = B.x
        val y2 = B.y
        val z2 = B.z

        val x = x1 + ((1 - (D / norm)) * (x2 - x1))
        val y = y1 + ((1 - (D / norm)) * (y2 - y1))
        val z = z1 + ((1 - (D / norm)) * (z2 - z1))

        return IPoint3D.new(x, y, z)
    }

    override fun findAPointCOnALineABInSpaceSuchThatTheDistanceOfBCIsAFixedValueD_TwoDimension(A: IPoint2D, B: IPoint2D, D: IMathNumber): IPoint2D {
        val norm = DistanceFormula.calculate(Spaces.EUCLIDEAN, Dimensions.TWO, A, B)

        val x1 = A.x
        val y1 = A.y

        val x2 = B.x
        val y2 = B.y

        val x = x1 + ((1 - (D / norm)) * (x2 - x1))
        val y = y1 + ((1 - (D / norm)) * (y2 - y1))

        return IPoint2D.new(x, y)
    }
}