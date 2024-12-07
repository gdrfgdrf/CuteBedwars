package io.github.gdrfgdrf.cutebedwars.abstracts.math.geometry.calculate

import io.github.gdrfgdrf.cutebedwars.abstracts.math.IMathNumber
import io.github.gdrfgdrf.cutebedwars.abstracts.math.geometry.three.IPoint3D
import io.github.gdrfgdrf.cutebedwars.abstracts.math.geometry.two.IPoint2D
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
    fun findAPointCOnALineABInSpaceSuchThatTheDistanceOfBCIsAFixedValueD_ThreeDimension(A: IPoint3D, B: IPoint3D, D: IMathNumber): IPoint3D
    /**
     * 在二维空间中的一条直线 AB 上寻找一个点 C，使得 BC 的距离为一个定值 D
     * 返回值为点 C
     */
    fun findAPointCOnALineABInSpaceSuchThatTheDistanceOfBCIsAFixedValueD_TwoDimension(A: IPoint2D, B: IPoint2D, D: IMathNumber): IPoint2D

    companion object {
        fun instance(): ILines = Mediator.get(ILines::class.java)!!
    }
}