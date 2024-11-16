package io.github.gdrfgdrf.cutebedwars.particles

import io.github.gdrfgdrf.cutebedwars.abstracts.particles.IManagedParticle
import io.github.gdrfgdrf.cutebedwars.abstracts.particles.IParticles
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import org.bukkit.Particle
import java.util.concurrent.ConcurrentHashMap

@ServiceImpl("particles")
object Particles : IParticles {
    private val map = ConcurrentHashMap<Particle, IManagedParticle>()

    override fun getOrCreate(particle: Particle): IManagedParticle {
        if (map.containsKey(particle)) {
            return map[particle]!!
        }

        val managedParticle = ManagedParticle.create(particle)
        map[particle] = managedParticle

        return managedParticle
    }
}