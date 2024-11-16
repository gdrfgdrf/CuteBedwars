package io.github.gdrfgdrf.cutebedwars.particles

import io.github.gdrfgdrf.cutebedwars.abstracts.particles.IManagedParticle
import io.github.gdrfgdrf.cutebedwars.abstracts.particles.IParticleGroup
import org.bukkit.Particle

class ManagedParticle private constructor(override val particle: Particle) : IManagedParticle {
    private val particleGroups = arrayListOf<IParticleGroup>()

    override operator fun get(name: String): IParticleGroup? {
        return particleGroups.stream()
            .filter {
                return@filter it.name == name
            }
            .findFirst()
            .orElse(null)
    }

    override fun create(name: String): IParticleGroup {
        val particleGroup = ParticleGroup.create(name, this)
        particleGroups.add(particleGroup)
        return particleGroup
    }

    override fun remove(name: String) {
        get(name)?.let {
            particleGroups.remove(it)
        }
    }

    override fun dismissAll() {
        particleGroups.forEach { particleGroup ->
            particleGroup.dismiss()
        }
    }

    companion object {
        fun create(particle: Particle) = ManagedParticle(particle)
    }
}