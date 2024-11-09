package io.github.gdrfgdrf.cutebedwars.abstracts.math.base

import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.Service
import io.github.gdrfgdrf.multimodulemediator.bean.ArgumentSet

@Service("point_3d", singleton = false)
interface IPoint3D : IPoint {
    companion object {
        fun new(x: Number, y: Number, z: Number): IPoint3D =
            Mediator.get(IPoint3D::class.java, ArgumentSet(arrayOf(x, y, z)))!!
    }
}