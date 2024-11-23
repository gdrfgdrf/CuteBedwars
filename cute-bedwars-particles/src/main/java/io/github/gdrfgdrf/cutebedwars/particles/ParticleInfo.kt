package io.github.gdrfgdrf.cutebedwars.particles

import io.github.gdrfgdrf.cutebedwars.abstracts.particles.IParticleInfo
import io.github.gdrfgdrf.cutebedwars.beans.pojo.common.Coordinate
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import io.github.gdrfgdrf.multimodulemediator.bean.ArgumentSet
import org.bukkit.Particle

@ServiceImpl("particle_info", needArgument = true)
class ParticleInfo(
    override val particle: Particle,
    override val coordinate: Coordinate,
    override val count: Int
): IParticleInfo {
    override var extra: Double? = 0.0

    constructor(argumentSet: ArgumentSet): this(
        argumentSet.args[0] as Particle,
        argumentSet.args[1] as Coordinate,
        argumentSet.args[2] as Int,
        argumentSet.args[3] as Double?,
        argumentSet.args[4]
    )

    constructor(
        particle: Particle,
        coordinate: Coordinate,
        count: Int,
        extra: Double?,
        data: Any?
    ) : this(particle, coordinate, count) {
        this.extra = extra
    }

    override fun equals(other: Any?): Boolean {
        if (other == null) {
            return false
        }
        if (other !is ParticleInfo) {
            return false
        }

        if (particle == other.particle &&
            coordinate == other.coordinate &&
            count == other.count &&
            extra == other.extra
        ) {
            return true
        }
        return false
    }

    override fun hashCode(): Int {
        var result = particle.hashCode()
        result = 31 * result + coordinate.hashCode()
        result = 31 * result + count
        result = 31 * result + (extra?.hashCode() ?: 0)
        return result
    }
}