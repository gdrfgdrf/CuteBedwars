package io.github.gdrfgdrf.cutebedwars.particles

import io.github.gdrfgdrf.cutebedwars.abstracts.enums.IParticleStatuses
import io.github.gdrfgdrf.cutebedwars.abstracts.particles.IManagedParticle
import io.github.gdrfgdrf.cutebedwars.abstracts.particles.IParticleGroup
import io.github.gdrfgdrf.cutebedwars.abstracts.particles.IParticleInfo
import io.github.gdrfgdrf.cutebedwars.beans.pojo.common.Coordinate
import org.bukkit.World

class ParticleGroup private constructor(
    override val name: String,
    override val parent: IManagedParticle
) : IParticleGroup {
    private var status = IParticleStatuses.valueOf("DEFAULT")
    private val list = arrayListOf<IParticleInfo>()

    override fun status(): IParticleStatuses = status

    private fun check() {
        if (status == IParticleStatuses.valueOf("DISMISSED")) {
            throw IllegalStateException("this particle group is dismissed")
        }
    }

    private fun check2() {
        if (status == IParticleStatuses.valueOf("ACTIVATED")) {
            throw IllegalStateException("this particle group is ACTIVATED, cannot perform this operation")
        }
    }

    override fun add(particleInfo: IParticleInfo) {
        check()
        check2()
        if (particleInfo.particle != parent.particle) {
            throw IllegalArgumentException("wrong particle type")
        }
        list.add(particleInfo)
    }

    override fun add(x: Double, y: Double, z: Double, count: Int) {
        check()
        check2()
        val coordinate = Coordinate()
        coordinate.x = x
        coordinate.y = y
        coordinate.z = z

        val particleInfo = ParticleInfo(parent.particle, coordinate, count)
        list.add(particleInfo)
    }

    override fun remove(particleInfo: IParticleInfo) {
        check()
        check2()
        list.remove(particleInfo)
    }

    override fun removeAt(index: Int) {
        check()
        check2()
        list.removeAt(index)
    }

    override fun removeAt(x: Double, y: Double, z: Double) {
        check()
        check2()
        val particleInfos = list.stream()
            .filter {
                val coordinate = it.coordinate
                return@filter coordinate.x == x && coordinate.y == y && coordinate.z == z
            }
            .toList()
        particleInfos?.let {
            it.forEach { particleInfo ->
                list.remove(particleInfo)
            }
        }
    }

    override fun spawn(world: World): List<Any> {
        check()
        status = IParticleStatuses.valueOf("ACTIVATED")

        val results = arrayListOf<Any>()
        list.forEach { particleInfo ->
            val particle = parent.particle
            val coordinate = particleInfo.coordinate
            val count = particleInfo.count
            val extra = particleInfo.extra
            val data = particleInfo.data

            if (extra == null) {
                val result = world.spawnParticle(
                    particle,
                    coordinate.x,
                    coordinate.y,
                    coordinate.z,
                    count,
                    0.0,
                    0.0,
                    0.0,
                    data
                )
                results.add(result)
            } else {
                val result = world.spawnParticle(
                    particle,
                    coordinate.x,
                    coordinate.y,
                    coordinate.z,
                    count,
                    0.0,
                    0.0,
                    0.0,
                    extra,
                    data
                )
                results.add(result)
            }
        }

        status = IParticleStatuses.valueOf("DEFAULT")

        return results
    }

    override fun dismiss() {
        status = IParticleStatuses.valueOf("DISMISSED")

        list.clear()
        parent.remove(name)
    }

    companion object {
        fun create(name: String, managedParticle: ManagedParticle) = ParticleGroup(name, managedParticle)
    }
}