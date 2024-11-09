package io.github.gdrfgdrf.cutebedwars.abstracts.math.common

import io.github.gdrfgdrf.cutebedwars.abstracts.math.IMathNumber
import io.github.gdrfgdrf.cutebedwars.abstracts.math.base.IPoint3D
import io.github.gdrfgdrf.multimodulemediator.annotation.Service

@Service("vector_3i")
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
}