package io.github.gdrfgdrf.cutebedwars.abstracts.math.geometry.calculate

import io.github.gdrfgdrf.cutebedwars.abstracts.math.IMathNumber
import io.github.gdrfgdrf.cutebedwars.abstracts.math.geometry.two.IPoint2D
import io.github.gdrfgdrf.multimodulemediator.annotation.KotlinSingleton
import io.github.gdrfgdrf.multimodulemediator.annotation.Service

@Service("rotates")
@KotlinSingleton
interface IRotates {
    /**
     * 某个二维点旋转特定角度后的新的二维点
     * center: 旋转中心
     * origin: 被旋转的点
     * theta: 旋转角度 (角度制，运行时会转为弧度制)
     * 返回值为旋转后的二维点
     *
     * x' = cos(theta) * (x - rx) - sin(theta) * (y - ry) + rx
     * y' = sin(theta) * (x - rx) + cos(theta) * (y - ry) + ry
     */
    fun rotatePoint2d(center: IPoint2D, origin: IPoint2D, theta: IMathNumber): IPoint2D
}