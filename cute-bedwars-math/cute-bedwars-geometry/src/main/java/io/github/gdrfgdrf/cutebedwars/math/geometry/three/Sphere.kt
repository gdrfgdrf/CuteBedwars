package io.github.gdrfgdrf.cutebedwars.math.geometry.three

import io.github.gdrfgdrf.cutebedwars.abstracts.math.IMathNumber
import io.github.gdrfgdrf.cutebedwars.abstracts.math.common.div
import io.github.gdrfgdrf.cutebedwars.abstracts.math.common.mathNumber
import io.github.gdrfgdrf.cutebedwars.abstracts.math.common.times
import io.github.gdrfgdrf.cutebedwars.abstracts.math.geometry.calculate.ISpheres
import io.github.gdrfgdrf.cutebedwars.abstracts.math.geometry.three.IPoint3D
import io.github.gdrfgdrf.cutebedwars.abstracts.math.geometry.three.ISphere
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import io.github.gdrfgdrf.multimodulemediator.bean.ArgumentSet

@ServiceImpl("sphere", needArgument = true)
class Sphere(
    override val center: IPoint3D,
    override val R: IMathNumber
) : ISphere {
    constructor(argumentSet: ArgumentSet): this(
        argumentSet.args[0] as IPoint3D,
        argumentSet.args[1] as IMathNumber
    )

    override fun divide3d(): List<IPoint3D> {
        val result = arrayListOf<IPoint3D>()
        val deltaTheta = ((16 * Math.PI / 3) / R.pow(2)).sqrt()
        val deltaPhi = deltaTheta

        var theta = 0.0.mathNumber()
        while (theta <= Math.PI) {
            var phi = 0.0.mathNumber()

            while (phi <= 2 * Math.PI) {
                val point3d = ISpheres.instance().getPoint3dByThetaAndPhi(center, R, theta, phi)
                result.add(point3d)

                phi += deltaPhi
            }
            theta += deltaTheta
        }

        return result
    }
}
