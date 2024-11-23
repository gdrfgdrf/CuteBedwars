package io.github.gdrfgdrf.cutebedwars.abstracts.particles

import com.github.fierioziy.particlenativeapi.api.ParticleNativeAPI
import io.github.gdrfgdrf.multimodulemediator.Mediator
import io.github.gdrfgdrf.multimodulemediator.annotation.KotlinSingleton
import io.github.gdrfgdrf.multimodulemediator.annotation.Service
import org.bukkit.Particle

@Service("particles")
@KotlinSingleton
interface IParticles {
    fun api(): ParticleNativeAPI?

    fun initialize()
    fun getOrCreate(particle: Particle): IManagedParticle

    companion object {
        fun instance(): IParticles = Mediator.get(IParticles::class.java)!!
    }
}