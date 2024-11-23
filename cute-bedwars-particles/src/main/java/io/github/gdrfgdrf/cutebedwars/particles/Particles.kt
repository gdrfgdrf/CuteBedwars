package io.github.gdrfgdrf.cutebedwars.particles

import com.github.fierioziy.particlenativeapi.api.ParticleNativeAPI
import com.github.fierioziy.particlenativeapi.core.ParticleNativeCore
import io.github.gdrfgdrf.cutebedwars.abstracts.core.IPlugin
import io.github.gdrfgdrf.cutebedwars.abstracts.particles.IManagedParticle
import io.github.gdrfgdrf.cutebedwars.abstracts.particles.IParticles
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.logInfo
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import org.bukkit.Particle
import java.util.concurrent.ConcurrentHashMap

@ServiceImpl("particles")
object Particles : IParticles {
    private val map = ConcurrentHashMap<Particle, IManagedParticle>()
    private var particleApi: ParticleNativeAPI? = null

    override fun api(): ParticleNativeAPI? = particleApi

    override fun initialize() {
        "Initializing particle native api".logInfo()
        particleApi = ParticleNativeCore.loadAPI(IPlugin.instance().javaPlugin())
    }

    override fun getOrCreate(particle: Particle): IManagedParticle {
        if (map.containsKey(particle)) {
            return map[particle]!!
        }

        val managedParticle = ManagedParticle.create(particle)
        map[particle] = managedParticle

        return managedParticle
    }
}