package io.github.gdrfgdrf.cutebedwars.abstracts.math.geometry

import io.github.gdrfgdrf.cutebedwars.abstracts.math.base.IPoint3D
import io.github.gdrfgdrf.cutebedwars.beans.pojo.common.Coordinate
import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.Service
import io.github.gdrfgdrf.multimodulemediator.bean.ArgumentSet

/**
 * 该类的点和线在 math-readme 中均有描述
 */
@Service("box", singleton = false)
interface IOutlineBox : IShape {
    val pos1: IPoint3D
    val pos2: IPoint3D

    val a: ILine3D
    val b: ILine3D
    val c: ILine3D
    val d: ILine3D

    val e: ILine3D
    val f: ILine3D
    val g: ILine3D
    val h: ILine3D

    val i: ILine3D
    val j: ILine3D
    val k: ILine3D
    val l: ILine3D

    fun contains(point3d: IPoint3D): Boolean

    companion object {
        fun new(pos1: IPoint3D, pos2: IPoint3D): IOutlineBox =
            Mediator.get(IOutlineBox::class.java, ArgumentSet(arrayOf(pos1, pos2)))!!

        fun new(coordinate1: Coordinate, coordinate2: Coordinate): IOutlineBox =
            new(IPoint3D.new(coordinate1), IPoint3D.new(coordinate2))
    }
}