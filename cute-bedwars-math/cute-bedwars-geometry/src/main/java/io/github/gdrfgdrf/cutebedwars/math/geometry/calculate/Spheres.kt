package io.github.gdrfgdrf.cutebedwars.math.geometry.calculate

import io.github.gdrfgdrf.cutebedwars.abstracts.math.IMathNumber
import io.github.gdrfgdrf.cutebedwars.abstracts.math.geometry.calculate.ISpheres
import io.github.gdrfgdrf.cutebedwars.abstracts.math.geometry.three.IPoint3D
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl

@ServiceImpl("spheres")
object Spheres : ISpheres {
    override fun getPoint3dByThetaAndPhi(
        center: IPoint3D,
        R: IMathNumber,
        theta: IMathNumber,
        phi: IMathNumber
    ): IPoint3D {
        val x = center.x + R * theta.sin() * phi.cos()
        val y = center.y + R * theta.sin() * phi.sin()
        val z = center.z + R * theta.cos()

        return IPoint3D.new(x, y, z)
    }
}