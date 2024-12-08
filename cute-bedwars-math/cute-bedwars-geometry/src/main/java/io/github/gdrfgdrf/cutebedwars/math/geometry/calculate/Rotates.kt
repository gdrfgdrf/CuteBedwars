package io.github.gdrfgdrf.cutebedwars.math.geometry.calculate

import io.github.gdrfgdrf.cutebedwars.abstracts.math.IMathNumber
import io.github.gdrfgdrf.cutebedwars.abstracts.math.common.minus
import io.github.gdrfgdrf.cutebedwars.abstracts.math.geometry.calculate.IRotates
import io.github.gdrfgdrf.cutebedwars.abstracts.math.geometry.three.IPoint3D
import io.github.gdrfgdrf.cutebedwars.abstracts.math.geometry.three.IVector3i
import io.github.gdrfgdrf.cutebedwars.abstracts.math.geometry.two.IPoint2D
import io.github.gdrfgdrf.cutebedwars.math.geometry.two.Point2D
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl

@ServiceImpl("rotates")
object Rotates : IRotates {
    override fun rotatePoint2d(center: IPoint2D, origin: IPoint2D, theta: IMathNumber): IPoint2D {
        val x = origin.x
        val y = origin.y
        val rx = center.x
        val ry = center.y

        val cosTheta = theta.radians().cos()
        val sinTheta = theta.radians().sin()

        val resultX = cosTheta * (x - rx) - sinTheta * (y - ry) + rx
        val resultY = sinTheta * (x - rx) + cosTheta * (y - ry) + ry

        return Point2D.of(resultX, resultY)
    }

    override fun rotatePoint3dXAxis(P: IPoint3D, a: IMathNumber): IPoint3D {
        val n = IVector3i.new(1, 0, 0)
        return rotatePoint3d(P, n, a)
    }

    override fun rotatePoint3dYAxis(P: IPoint3D, a: IMathNumber): IPoint3D {
        val n = IVector3i.new(0, 1, 0)
        return rotatePoint3d(P, n, a)
    }

    override fun rotatePoint3dZAxis(P: IPoint3D, a: IMathNumber): IPoint3D {
        val n = IVector3i.new(0, 0, 1)
        return rotatePoint3d(P, n, a)
    }

    override fun rotatePoint3d(
        P: IPoint3D,
        axis: IVector3i,
        a: IMathNumber
    ): IPoint3D {
        val normalizedAxis = axis.normalize()
        val cosTheta = a.radians().cos()
        val sinTheta = a.radians().sin()
        val K = 1 - cosTheta

        val x = normalizedAxis.x
        val y = normalizedAxis.y
        val z = normalizedAxis.z

        val px = P.x
        val py = P.y
        val pz = P.z

        val xx = x * y
        val yy = y * y
        val zz = z * z
        val xy = x * y
        val xz = x * z
        val yz = y * z
        val xSinTheta = x * sinTheta
        val ySinTheta = y * sinTheta
        val zSinTheta = z * sinTheta

        val resultX = x * (x * px + y * py + z * pz) * K + px * cosTheta + (y * zSinTheta - z * ySinTheta) * py + (z * xSinTheta - x * zSinTheta) * pz
        val resultY = (x * xy + y * (y * py + z * pz) * K) + py * cosTheta + (z * xSinTheta + x * ySinTheta) * px + (x * zSinTheta - z * xSinTheta) * pz
        val resultZ = (x * xz + z * (x * px + y * py) * K) + pz * cosTheta + (y * xSinTheta + x * zSinTheta) * px + (y * ySinTheta + y * xSinTheta) * py

        return IPoint3D.new(resultX, resultY, resultZ)
    }
}