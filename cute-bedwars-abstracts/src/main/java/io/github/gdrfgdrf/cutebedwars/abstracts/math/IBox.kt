package io.github.gdrfgdrf.cutebedwars.abstracts.math

import io.github.gdrfgdrf.cutebedwars.abstracts.math.base.IPoint3D
import io.github.gdrfgdrf.cutebedwars.beans.pojo.common.Coordinate
import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.Service
import io.github.gdrfgdrf.multimodulemediator.bean.ArgumentSet

@Service("box", singleton = false)
interface IBox {
    val pos1: IPoint3D
    val pos2: IPoint3D

    fun contains(point3d: IPoint3D): Boolean

    companion object {
        fun new(pos1: IPoint3D, pos2: IPoint3D): IBox =
            Mediator.get(IBox::class.java, ArgumentSet(arrayOf(pos1, pos2)))!!

        fun new(coordinate1: Coordinate, coordinate2: Coordinate): IBox =
            new(IPoint3D.new(coordinate1), IPoint3D.new(coordinate2))
    }
}