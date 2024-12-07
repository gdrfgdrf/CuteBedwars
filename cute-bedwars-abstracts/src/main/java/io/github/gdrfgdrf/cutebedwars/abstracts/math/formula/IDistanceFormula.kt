package io.github.gdrfgdrf.cutebedwars.abstracts.math.formula

import io.github.gdrfgdrf.cutebedwars.abstracts.math.base.IIFormula
import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.KotlinSingleton
import io.github.gdrfgdrf.multimodulemediator.annotation.Service

@Service("distance_formula")
@KotlinSingleton
interface IDistanceFormula : IIFormula {
    companion object {
        fun instance(): IDistanceFormula = Mediator.get(IDistanceFormula::class.java)!!
    }
}