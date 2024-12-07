package io.github.gdrfgdrf.cutebedwars.abstracts.math.geometry.three

import io.github.gdrfgdrf.cutebedwars.abstracts.math.base.IShape
import io.github.gdrfgdrf.cutebedwars.beans.pojo.common.Coordinate
import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.Service
import io.github.gdrfgdrf.multimodulemediator.bean.ArgumentSet

/**
 * 该类的点和线在 math-readme 中均有描述，
 * 所有用于区分的撇（如 A'，B' 等）在该类中均改成杠（如 A_, B_ 等）
 */
@Service("box", singleton = false)
interface IOutlineBox : IShape {
    val pos1: IPoint3D
    val pos2: IPoint3D

    val A: IPoint3D
        get() = IPoint3D.new(
            pos1.x,
            pos1.y,
            pos2.z
        )
    val B: IPoint3D
        get() = IPoint3D.new(
            pos1.x,
            pos2.y,
            pos2.z
        )
    val C: IPoint3D
        get() = pos2
    val O: IPoint3D
        get() = IPoint3D.new(
            pos2.x,
            pos1.y,
            pos2.z
        )

    val A_: IPoint3D
        get() = pos1
    val B_: IPoint3D
        get() = IPoint3D.new(
            pos1.x,
            pos2.y,
            pos1.z
        )
    val C_: IPoint3D
        get() = IPoint3D.new(
            pos2.x,
            pos2.y,
            pos1.z
        )
    val D_: IPoint3D
        get() = IPoint3D.new(
            pos2.x,
            pos1.y,
            pos1.z
        )

    val a: ILine3D
        get() = ILine3D.new(A_, B_)
    val b: ILine3D
        get() = ILine3D.new(D_, C_)
    val c: ILine3D
        get() = ILine3D.new(C_, B_)
    val d: ILine3D
        get() = ILine3D.new(B_, A_)

    val e: ILine3D
        get() = ILine3D.new(A_, A)
    val f: ILine3D
        get() = ILine3D.new(D_, O)
    val g: ILine3D
        get() = ILine3D.new(C_, C)
    val h: ILine3D
        get() = ILine3D.new(B_, B)

    val i: ILine3D
        get() = ILine3D.new(A, O)
    val j: ILine3D
        get() = ILine3D.new(O, C)
    val k: ILine3D
        get() = ILine3D.new(C, B)
    val l: ILine3D
        get() = ILine3D.new(B, A)

    val otherLines: List<ILine3D>

    /**
     * 某点是否在该 Box 中
     */
    fun contains(point3d: IPoint3D): Boolean

    /**
     * 添加一条线，start，end 均要位于该 Box 中
     */
    fun addLine(start: IPoint3D, end: IPoint3D)

    companion object {
        fun new(pos1: IPoint3D, pos2: IPoint3D): IOutlineBox =
            Mediator.get(IOutlineBox::class.java, ArgumentSet(arrayOf(pos1, pos2)))!!

        fun new(coordinate1: Coordinate, coordinate2: Coordinate): IOutlineBox =
            new(IPoint3D.new(coordinate1), IPoint3D.new(coordinate2))
    }
}