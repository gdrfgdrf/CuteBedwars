package io.github.gdrfgdrf.cutebedwars.abstracts.math.calculate

import io.github.gdrfgdrf.cutebedwars.abstracts.math.IMathNumber
import io.github.gdrfgdrf.cutebedwars.abstracts.math.base.IPoint3D
import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.KotlinSingleton
import io.github.gdrfgdrf.multimodulemediator.annotation.Service

@Service("lines")
@KotlinSingleton
interface ILines {
    /**
     * 在三维空间中的一条直线 AB 上寻找一个点 C，使得 BC 的距离为一个定值 D
     * 返回值为点 C
     */
    fun findAPointCOnALineABInSpaceSuchThatTheDistanceOfBCIsAFixedValueD(A: IPoint3D, B: IPoint3D, D: IMathNumber): IPoint3D

    companion object {
        fun instance(): ILines = Mediator.get(ILines::class.java)!!
    }
}