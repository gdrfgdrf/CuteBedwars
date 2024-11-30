package io.github.gdrfgdrf.cutebedwars.abstracts.math.geometry

import io.github.gdrfgdrf.cutebedwars.abstracts.math.IMathNumber
import io.github.gdrfgdrf.cutebedwars.abstracts.math.base.IPoint

interface IShape {
    fun divide(step: IMathNumber): List<IPoint>
}