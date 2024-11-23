package io.github.gdrfgdrf.cutebedwars.abstracts.math.calculate

import io.github.gdrfgdrf.cutebedwars.abstracts.math.base.IPoint3D
import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.KotlinSingleton
import io.github.gdrfgdrf.multimodulemediator.annotation.Service

@Service("cuboids")
@KotlinSingleton
interface ICuboids {
    /**
     * 计算长方体的几何中心
     */
    fun geometricCenter(A: IPoint3D, B: IPoint3D): IPoint3D

    companion object {
        fun instance(): ICuboids = Mediator.get(ICuboids::class.java)!!
    }
}