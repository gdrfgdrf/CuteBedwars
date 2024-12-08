package io.github.gdrfgdrf.cutebedwars.abstracts.math.geometry.calculate

import io.github.gdrfgdrf.cutebedwars.abstracts.math.IMathNumber
import io.github.gdrfgdrf.cutebedwars.abstracts.math.common.minus
import io.github.gdrfgdrf.cutebedwars.abstracts.math.geometry.three.IPoint3D
import io.github.gdrfgdrf.cutebedwars.abstracts.math.geometry.three.IVector3i
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
    /**
     * 将三维点绕 X 轴旋转,
     * P: 原始点,
     * a: 旋转角度 (角度制，运行时转为弧度制)
     * n 的取值为 1, 0, 0
     */
    fun rotatePoint3dXAxis(P: IPoint3D, a: IMathNumber): IPoint3D
    /**
     * 将三维点绕 Y 轴旋转,
     * P: 原始点,
     * a: 旋转角度 (角度制，运行时转为弧度制)
     * n 的取值为 0, 1, 0
     */
    fun rotatePoint3dYAxis(P: IPoint3D, a: IMathNumber): IPoint3D
    /**
     * 将三维点绕 Z 轴旋转,
     * P: 原始点,
     * a: 旋转角度 (角度制，运行时转为弧度制)
     * n 的取值为 0, 0, 1
     */
    fun rotatePoint3dZAxis(P: IPoint3D, a: IMathNumber): IPoint3D
    /**
     * 基于 https://blog.csdn.net/maple_2014/article/details/104443928，
     * 将某点绕某个轴旋转指定角度
     *
     * Q 的取值为 0, 0, 0
     * P: 原始点,
     * n: 旋转轴的单位方向向量
     * a: 旋转角度 (角度制，运行时转为弧度制)
     * 返回旋转后的点
     */
    fun rotatePoint3d(P: IPoint3D, n: IVector3i, a: IMathNumber): IPoint3D
}