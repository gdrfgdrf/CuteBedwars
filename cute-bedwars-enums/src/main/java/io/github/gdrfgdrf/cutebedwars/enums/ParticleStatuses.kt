package io.github.gdrfgdrf.cutebedwars.enums

import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IParticleStatuses
import io.github.gdrfgdrf.multimodulemediator.annotation.EnumServiceImpl

@EnumServiceImpl("particle_statuses")
enum class ParticleStatuses : IParticleStatuses {
    DEFAULT,
    ACTIVATED,
    DISMISSED,
}