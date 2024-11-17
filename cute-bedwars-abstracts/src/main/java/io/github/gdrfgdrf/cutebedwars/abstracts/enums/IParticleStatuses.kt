package io.github.gdrfgdrf.cutebedwars.abstracts.enums

import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.EnumService

@EnumService("particle_statuses")
interface IParticleStatuses {

    companion object {
        fun valueOf(name: String): IParticleStatuses = Mediator.valueOf(IParticleStatuses::class.java, name)!!
    }
}