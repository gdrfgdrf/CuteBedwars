package io.github.gdrfgdrf.cutebedwars.abstracts.math.geometry.calculate

import io.github.gdrfgdrf.cutebedwars.abstracts.math.IMathNumber
import io.github.gdrfgdrf.cutebedwars.abstracts.math.geometry.three.IPoint3D
import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.KotlinSingleton
import io.github.gdrfgdrf.multimodulemediator.annotation.Service

@Service("boxes")
@KotlinSingleton
interface IBoxes {
    /**
     * 计算 Box 的几何中心
     */
    fun geometricCenter(A: IPoint3D, B: IPoint3D): IPoint3D
    fun size(A: IPoint3D, B: IPoint3D): IMathNumber

    companion object {
        fun instance(): IBoxes = Mediator.get(IBoxes::class.java)!!
    }
}