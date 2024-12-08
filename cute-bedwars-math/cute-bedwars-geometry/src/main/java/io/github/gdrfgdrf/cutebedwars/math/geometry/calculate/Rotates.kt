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
        n: IVector3i,
        a: IMathNumber
    ): IPoint3D {
        val theta = a.radians()

        val px = P.x
        val py = P.y
        val pz = P.z

        val nx = n.x
        val ny = n.y
        val nz = n.z

        val t0 = nx * px + ny * py + nz * pz

        val xc = nx * t0
        val yc = ny * t0
        val zc = nz * t0

        val r = ((px - xc) * (px - xc) + ((py - yc) * (py - yc)) + ((pz - zc) * (pz - zc))).sqrt()

        val opX = (px - xc) / r
        val opY = (py - yc) / r
        val opZ = (pz - zc) / r

        val yPrimeX = nx * opY - ny * opZ
        val yPrimeY = ny * opZ - nz * opX

        val cosTheta = theta.cos()
        val sinTheta = theta.sin()

        val R11 = nx * nx + (ny * ny + nz * nz) * cosTheta
        val R12 = nx * ny * (1 - cosTheta) - nz * sinTheta
        val R13 = nx * nz * (1 - cosTheta) + ny * sinTheta
        val R21 = ny * nx * (1 - cosTheta) + nz * sinTheta
        val R22 = ny * ny + (nx * nx + nz * nz) * cosTheta
        val R23 = ny * nz * (1 - cosTheta) - nx * sinTheta
        val R31 = nz * nx * (1 - cosTheta) - ny * sinTheta
        val R32 = nz * ny * (1 - cosTheta) + nx * sinTheta
        val R33 = nz * nz + (nx * nx + ny * ny) * cosTheta

        val pxPrime = R11 * opX + R12 * yPrimeX + R13 * yPrimeY + xc
        val pyPrime = R21 * opX + R22 * yPrimeX + R23 * yPrimeY + yc
        val pzPrime = R31 * opX + R32 * yPrimeX + R33 * yPrimeY + zc

        return IPoint3D.new(pxPrime, pyPrime, pzPrime)
    }
}