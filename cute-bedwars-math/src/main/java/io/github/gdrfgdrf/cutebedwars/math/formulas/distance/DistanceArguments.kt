package io.github.gdrfgdrf.cutebedwars.math.formulas.distance

import io.github.gdrfgdrf.cutebedwars.math.base.IPoint
import io.github.gdrfgdrf.cutebedwars.math.common.Argument
import io.github.gdrfgdrf.cutebedwars.math.common.Arguments
import io.github.gdrfgdrf.cutebedwars.math.common.MathNumber
import io.github.gdrfgdrf.cutebedwars.math.enums.Dimensions
import io.github.gdrfgdrf.cutebedwars.math.enums.Spaces

class DistanceArguments(private val arguments: Arguments) {
    private val pointStep: Int = (arguments[2].value as IPoint).step()

    fun space(): Spaces {
        return arguments[0].value as Spaces
    }

    fun dimension(): Dimensions {
        return arguments[1].value as Dimensions
    }

    fun point(index: Int): IPoint {
        return arguments[index + 2].value as IPoint
    }

    fun allPoints(): Arguments {
        val copy = arguments.copy()
        copy.removeAt(0)
        copy.removeAt(0)

        val newArguments = Arguments()
        val pointGroup = arrayListOf<IPoint>()
        copy.forEach {
            pointGroup.add(it.value as IPoint)
        }

        val all = arrayListOf<MathNumber>()
        for (i1 in 0 until pointStep) {
            for (i2 in 0 until 2) {
                all.add(pointGroup[i2].all()[i1])
            }
        }

        all.forEach {
            newArguments.add(Argument(it))
        }

        return newArguments
    }
}