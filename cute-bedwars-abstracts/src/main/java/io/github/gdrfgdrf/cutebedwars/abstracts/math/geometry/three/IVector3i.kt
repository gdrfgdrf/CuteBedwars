package io.github.gdrfgdrf.cutebedwars.abstracts.math.geometry.three

import io.github.gdrfgdrf.cutebedwars.abstracts.math.IMathNumber
import io.github.gdrfgdrf.cutebedwars.abstracts.math.common.mathNumber
import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.Service
import io.github.gdrfgdrf.multimodulemediator.bean.ArgumentSet

@Service("vector_3i", singleton = false)
interface IVector3i {
    val x: IMathNumber
    val y: IMathNumber
    val z: IMathNumber

    fun norm(): IMathNumber
    fun unit(): IVector3i
    fun point3D(): IPoint3D

    fun plus(x2: IMathNumber, y2: IMathNumber, z2: IMathNumber): IVector3i
    operator fun plus(number: IMathNumber): IVector3i
    operator fun plus(vector3i: IVector3i): IVector3i

    fun minus(x2: IMathNumber, y2: IMathNumber, z2: IMathNumber): IVector3i
    operator fun minus(number: IMathNumber): IVector3i
    operator fun minus(vector3i: IVector3i): IVector3i

    fun times(x2: IMathNumber, y2: IMathNumber, z2: IMathNumber): IVector3i
    operator fun times(number: IMathNumber): IVector3i
    operator fun times(vector3i: IVector3i): IVector3i

    fun div(x2: IMathNumber, y2: IMathNumber, z2: IMathNumber): IVector3i
    operator fun div(number: IMathNumber): IVector3i
    operator fun div(vector3i: IVector3i): IVector3i

    companion object {
        fun new(x: IMathNumber, y: IMathNumber, z: IMathNumber): IVector3i = Mediator.get(
            IVector3i::class.java, ArgumentSet(
                arrayOf(x, y, z)
            )
        )!!

        fun new(x: Number, y: Number, z: Number) = new(x.mathNumber(), y.mathNumber(), z.mathNumber())
    }
}