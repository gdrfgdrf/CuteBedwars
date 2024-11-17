package io.github.gdrfgdrf.cutebedwars.abstracts.math.common

import io.github.gdrfgdrf.cutebedwars.abstracts.math.IMathNumber
import io.github.gdrfgdrf.cutebedwars.abstracts.math.base.IPoint3D
import io.github.gdrfgdrf.multimodulemediator.annotation.Service

@Service("line_3d", singleton = false)
interface ILine3D {
    val start: IPoint3D
    val end: IPoint3D

    /**
     * 将一条线平均分成若干个等份，并保证每个等份的长度为同一个定值
     * 返回值为所有的切割点
     */
    fun divide(step: IMathNumber): List<IPoint3D>
}