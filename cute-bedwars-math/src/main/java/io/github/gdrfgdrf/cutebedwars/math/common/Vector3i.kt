package io.github.gdrfgdrf.cutebedwars.math.common

import io.github.gdrfgdrf.cutebedwars.abstracts.math.IMathNumber
import io.github.gdrfgdrf.cutebedwars.abstracts.math.base.IPoint3D
import io.github.gdrfgdrf.cutebedwars.abstracts.math.common.IVector3i
import io.github.gdrfgdrf.cutebedwars.abstracts.math.mathNumber
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import io.github.gdrfgdrf.multimodulemediator.bean.ArgumentSet

@ServiceImpl("vector_3i", needArgument = true)
class Vector3i private constructor(
    override val x: IMathNumber,
    override val y: IMathNumber,
    override val z: IMathNumber
) : IVector3i {
    constructor(argumentSet: ArgumentSet): this(
        argumentSet.args[0] as IMathNumber,
        argumentSet.args[1] as IMathNumber,
        argumentSet.args[2] as IMathNumber
    )

    override fun norm(): IMathNumber {
        return (x.pow(2) + y.pow(2) + z.pow(2)).sqrt()
    }

    override fun unit(): IVector3i {
        return this * norm().reciprocal()
    }

    override fun point3D(): IPoint3D {
        return Point3D.of(x, y, z)
    }

    override fun plus(x2: IMathNumber, y2: IMathNumber, z2: IMathNumber): IVector3i {
        return of(x + x2, y + y2, z + z2)
    }

    override fun plus(number: IMathNumber): IVector3i {
        return of(x + number, y + number, z + number)
    }

    override operator fun plus(vector3i: IVector3i): IVector3i {
        return of(x + vector3i.x, y + vector3i.y, z + vector3i.z)
    }

    override fun minus(x2: IMathNumber, y2: IMathNumber, z2: IMathNumber): IVector3i {
        return of(x - x2, y - y2, z - z2)
    }

    override fun minus(number: IMathNumber): IVector3i {
        return of(x - number, y - number, z - number)
    }

    override operator fun minus(vector3i: IVector3i): IVector3i {
        return of(x - vector3i.x, y - vector3i.y, z - vector3i.z)
    }

    override fun times(x2: IMathNumber, y2: IMathNumber, z2: IMathNumber): IVector3i {
        return of(x * x2, y * y2, z * z2)
    }

    override fun times(number: IMathNumber): IVector3i {
        return of(x * number, y * number, z * number)
    }

    override operator fun times(vector3i: IVector3i): IVector3i {
        return of(x * vector3i.x, y * vector3i.y, z * vector3i.z)
    }

    override fun div(x2: IMathNumber, y2: IMathNumber, z2: IMathNumber): IVector3i {
        return of(x / x2, y / y2, z / z2)
    }

    override fun div(number: IMathNumber): IVector3i {
        return of(x / number, y / number, z / number)
    }

    override operator fun div(vector3i: IVector3i): IVector3i {
        return of(x / vector3i.x, y / vector3i.y, z / vector3i.z)
    }

    companion object {
        fun of(x: Number, y: Number, z: Number) = Vector3i(x.mathNumber(), y.mathNumber(), z.mathNumber())
        fun of(x: IMathNumber, y: IMathNumber, z: IMathNumber) = Vector3i(x, y, z)
    }
}