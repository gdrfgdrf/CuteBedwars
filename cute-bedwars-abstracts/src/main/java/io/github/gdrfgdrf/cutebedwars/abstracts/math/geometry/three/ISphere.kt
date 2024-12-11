package io.github.gdrfgdrf.cutebedwars.abstracts.math.geometry.three

import io.github.gdrfgdrf.cutebedwars.abstracts.math.IMathNumber
import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.Service
import io.github.gdrfgdrf.multimodulemediator.bean.ArgumentSet

@Service("sphere", singleton = false)
interface ISphere : IShape3D {
    override val points3d: Array<IPoint3D>
        get() = arrayOf(center)

    val center: IPoint3D
    val R: IMathNumber

    companion object {
        fun new(center: IPoint3D, R: IMathNumber): ISphere =
            Mediator.get(ISphere::class.java, ArgumentSet(arrayOf(center, R)))!!
    }
}