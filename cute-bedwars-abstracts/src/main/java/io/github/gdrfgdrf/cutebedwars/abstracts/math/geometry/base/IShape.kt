package io.github.gdrfgdrf.cutebedwars.abstracts.math.geometry.base

import io.github.gdrfgdrf.cutebedwars.abstracts.math.IMathNumber

interface IShape {
    /**
     * 确定一个形状的所有点
     */
    val points: Array<IPoint>

    fun divide(step: IMathNumber): List<IPoint>
}