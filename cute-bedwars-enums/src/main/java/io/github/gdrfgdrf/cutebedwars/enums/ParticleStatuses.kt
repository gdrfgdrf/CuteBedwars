package io.github.gdrfgdrf.cutebedwars.enums

import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IParticleStatuses
import io.github.gdrfgdrf.multimodulemediator.annotation.EnumService

@EnumService("particle_statues")
enum class ParticleStatuses : IParticleStatuses {
    DEFAULT,
    ACTIVATED,
    DISMISSED,
}