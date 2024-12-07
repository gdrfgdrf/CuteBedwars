package io.github.gdrfgdrf.cutebedwars.abstracts.math.geometry.two

import io.github.gdrfgdrf.cutebedwars.abstracts.math.IMathNumber
import io.github.gdrfgdrf.cutebedwars.abstracts.math.geometry.base.IPoint
import io.github.gdrfgdrf.cutebedwars.abstracts.math.geometry.base.IShape
import io.github.gdrfgdrf.cutebedwars.abstracts.math.common.maxOf
import io.github.gdrfgdrf.cutebedwars.abstracts.math.common.minOf
import io.github.gdrfgdrf.cutebedwars.abstracts.math.geometry.three.IShape2D
import io.github.gdrfgdrf.multimodulemediator.annotation.Service

@Service("rectangle", singleton = false)
interface IRectangle : IShape2D {
    override val points2d: Array<IPoint2D>
        get() = arrayOf(pos1, pos2)

    /**
     * 使用两点确定一矩形中的一点
     */
    val pos1: IPoint2D

    /**
     * 使用两点确定一矩形中的一点
     */
    val pos2: IPoint2D

    val A: IPoint2D
        get() = pos1
    val B: IPoint2D
        get() = IPoint2D.new(pos2.x, pos1.y)
    val C: IPoint2D
        get() = pos2
    val D: IPoint2D
        get() = IPoint2D.new(pos1.x, pos2.y)

    val a: ILine2D
        get() = ILine2D.new(A, B)
    val b: ILine2D
        get() = ILine2D.new(B, C)
    val c: ILine2D
        get() = ILine2D.new(C, D)
    val d: ILine2D
        get() = ILine2D.new(D, A)

    val length: IMathNumber
        get() = maxOf(a.length, b.length)
    val width: IMathNumber
        get() = minOf(a.length, b.length)

    val circumference: IMathNumber
        get() = (a.length + b.length) * 2
    val area: IMathNumber
        get() = a.length * b.length

    val otherShapes: List<IShape2D>

    /**
     * 某点是否在该矩形中
     */
    fun contains(point2d: IPoint2D): Boolean
    /**
     * 将某个 2D 形状添加在该矩形中，该形状必须整个位于该矩形中
     */
    fun addShape(shape2d: IShape2D)
}