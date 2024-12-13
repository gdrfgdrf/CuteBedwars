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

    override fun divide3d(step: IMathNumber): List<IPoint3D> {
        return divide3d()
    }

    fun divide3d(): List<IPoint3D>

    companion object {
        fun new(center: IPoint3D, R: IMathNumber): ISphere =
            Mediator.get(ISphere::class.java, ArgumentSet(arrayOf(center, R)))!!
    }
}