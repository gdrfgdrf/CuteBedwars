package io.github.gdrfgdrf.cutebedwars.abstracts.math.geometry.calculate

import io.github.gdrfgdrf.cutebedwars.abstracts.math.IMathNumber
import io.github.gdrfgdrf.cutebedwars.abstracts.math.geometry.three.IPoint3D
import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.KotlinSingleton
import io.github.gdrfgdrf.multimodulemediator.annotation.Service

@Service("spheres")
@KotlinSingleton
interface ISpheres {
    fun getPoint3dByThetaAndPhi(
        center: IPoint3D,
        R: IMathNumber,
        theta: IMathNumber,
        phi: IMathNumber
    ): IPoint3D

    companion object {
        fun instance(): ISpheres = Mediator.get(ISpheres::class.java)!!
    }

}