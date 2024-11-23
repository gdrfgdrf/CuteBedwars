package io.github.gdrfgdrf.cutebedwars.abstracts.particles

import io.github.gdrfgdrf.cutebedwars.beans.pojo.common.Coordinate
import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.Service
import io.github.gdrfgdrf.multimodulemediator.bean.ArgumentSet
import org.bukkit.Particle

@Service("particle_info", singleton = false)
interface IParticleInfo {
    val particle: Particle
    val coordinate: Coordinate
    val count: Int
    var extra: Double?

    companion object {
        fun create(
            particle: Particle,
            coordinate: Coordinate,
            count: Int,
            extra: Double? = null,
            data: Any? = null
        ): IParticleInfo =
            Mediator.get(
                IParticleInfo::class.java, ArgumentSet(
                    arrayOf(
                        particle,
                        coordinate,
                        count,
                        extra,
                        data
                    ))
            )!!
    }
}