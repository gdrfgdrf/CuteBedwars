package io.github.gdrfgdrf.cutebedwars.math.calculate

import io.github.gdrfgdrf.cutebedwars.abstracts.math.IMathNumber
import io.github.gdrfgdrf.cutebedwars.abstracts.math.base.IPoint3D
import io.github.gdrfgdrf.cutebedwars.abstracts.math.calculate.ILines
import io.github.gdrfgdrf.cutebedwars.math.common.Point3D
import io.github.gdrfgdrf.cutebedwars.math.common.times
import io.github.gdrfgdrf.cutebedwars.math.enums.Dimensions
import io.github.gdrfgdrf.cutebedwars.math.enums.Spaces
import io.github.gdrfgdrf.cutebedwars.math.formula.distance.DistanceFormula
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl

@ServiceImpl("lines")
object Lines : ILines {
    override fun findAPointCOnALineABInSpaceSuchThatTheDistanceOfBCIsAFixedValueD(A: IPoint3D, B: IPoint3D, D: IMathNumber): IPoint3D {
        val unitVector = DistanceFormula.calculate(Spaces.EUCLIDEAN, Dimensions.THREE, A, B)
        val eX = (B.x - A.x) / unitVector
        val eY = (B.y - A.y) / unitVector
        val eZ = (B.z - A.z) / unitVector

        val a = eX.pow(2) + eY.pow(2) + eZ.pow(2)
        val b = -2 * (eX * B.x + eY * B.y + eZ * B.z)
        val c = B.x.pow(2) + B.y.pow(2) + B.z.pow(2) - D.pow(2)
        val pair = Equations.quadraticOneVariable(a, b, c)
        if (pair.first == null || pair.second == null) {
            throw IllegalArgumentException("Quadratic equations with one variable have no real solutions")
        }

        val k = if (pair.first!! > 0) {
            pair.first!!
        } else {
            pair.second!!
        }

        val resultX = k * eX
        val resultY = k * eY
        val resultZ = k * eZ

        return Point3D.of(resultX, resultY, resultZ)
    }
}