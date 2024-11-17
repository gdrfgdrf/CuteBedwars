package io.github.gdrfgdrf.cutebedwars.selection

import io.github.gdrfgdrf.cutebedwars.abstracts.math.common.ILine3D
import io.github.gdrfgdrf.cutebedwars.abstracts.math.mathNumber
import io.github.gdrfgdrf.cutebedwars.abstracts.particles.IParticles
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.frequencyTask
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.logDebug
import io.github.gdrfgdrf.cutebedwars.beans.pojo.common.Coordinate
import org.bukkit.Particle
import org.bukkit.World

class Selection(
    val pos1: Coordinate,
    val pos2: Coordinate
) {
    private val lines = arrayListOf<ILine3D>()
    private var initialized = false

    fun spawnParticle(particle: Particle, world: World, frequency: Long) {
        val managedParticle = IParticles.instance().getOrCreate(particle)
        val particleGroup = managedParticle.create("selection-particle")

        lines.forEach { line3d ->
            line3d.divide(1.mathNumber()).forEach { point3d ->
                val x = point3d.x
                val y = point3d.y
                val z = point3d.z

                particleGroup.add(x.toDouble(), y.toDouble(), z.toDouble())
            }

        }

        particleGroup.spawn(world, frequency)
    }

    fun initialize() {
        val blockCoordinate1 = pos1.blockCoordinate()
        val blockCoordinate2 = pos2.blockCoordinate()

        lines.apply {
            run {
                "Calculating the top of the selection".logDebug()

                run {
                    val coordinate = Coordinate()
                    coordinate.x = blockCoordinate2.x
                    coordinate.y = blockCoordinate1.y
                    coordinate.z = blockCoordinate1.z

                    val line = ILine3D.new(blockCoordinate1, coordinate)
                    add(line)

                    "(x, y, z)(pos1) -> (x2, y, z) is $line".logDebug()
                }
                run {
                    val coordinate = Coordinate()
                    coordinate.x = blockCoordinate1.x
                    coordinate.y = blockCoordinate1.y
                    coordinate.z = blockCoordinate2.z

                    val line = ILine3D.new(blockCoordinate1, coordinate)
                    add(line)

                    "(x, y, z)(pos1) -> (x, y, z2) is $line".logDebug()
                }
                run {
                    val coordinate1 = lines[0].end
                    val coordinate2 = Coordinate()
                    coordinate2.x = blockCoordinate2.x
                    coordinate2.y = blockCoordinate1.y
                    coordinate2.z = blockCoordinate2.z

                    val line = ILine3D.new(coordinate1.coordinate(), coordinate2)
                    add(line)

                    "(x2, y, z) -> (x2, y, z2) is $line".logDebug()
                }
                run {
                    val coordinate1 = lines[1].end
                    val coordinate2 = lines[2].end

                    val line = ILine3D.new(coordinate1, coordinate2)
                    add(line)

                    "(x, y, z2) -> (x2, y, z2) is $line".logDebug()
                }
            }

            run {
                "Calculating the bottom of the selection".logDebug()

                run {
                    val coordinate1 = Coordinate()
                    coordinate1.x = blockCoordinate1.x
                    coordinate1.y = blockCoordinate2.y
                    coordinate1.z = blockCoordinate1.z

                    val coordinate2 = Coordinate()
                    coordinate2.x = blockCoordinate2.x
                    coordinate2.y = blockCoordinate2.y
                    coordinate2.z = blockCoordinate1.z

                    val line = ILine3D.new(coordinate1, coordinate2)
                    add(line)

                    "(x, y2, z) -> (x2, y2, z) is $line".logDebug()
                }
                run {
                    val coordinate1 = lines[4].start
                    val coordinate2 = Coordinate()
                    coordinate2.x = blockCoordinate1.x
                    coordinate2.y = blockCoordinate2.y
                    coordinate2.z = blockCoordinate2.z

                    val line = ILine3D.new(coordinate1.coordinate(), coordinate2)
                    add(line)

                    "(x, y2, z) -> (x, y2, z2) is $line".logDebug()
                }
                run {
                    val coordinate1 = lines[4].end
                    val line = ILine3D.new(coordinate1.coordinate(), blockCoordinate2)
                    add(line)

                    "(x2, y2, z) -> (x2, y2, z2)(pos2) is $line".logDebug()
                }
                run {
                    val coordinate1 = lines[5].end
                    val line = ILine3D.new(coordinate1.coordinate(), blockCoordinate2)
                    add(line)

                    "(x, y2, z2) -> (x2, y2, z2)(pos2) is $line".logDebug()
                }
            }

            run {
                "Calculating the middle of the selection".logDebug()

                run {
                    val coordinate2 = lines[4].start
                    val line = ILine3D.new(blockCoordinate1, coordinate2.coordinate())
                    add(line)

                    "(x, y, z) -> (x, y2, z) is $line".logDebug()
                }
                run {
                    val coordinate1 = lines[0].end
                    val coordinate2 = lines[4].end
                    val line = ILine3D.new(coordinate1, coordinate2)
                    add(line)

                    "(x2, y, z) -> (x2, y2, z) is $line".logDebug()
                }
                run {
                    val coordinate1 = lines[1].end
                    val coordinate2 = lines[5].end
                    val line = ILine3D.new(coordinate1, coordinate2)
                    add(line)

                    "(x, y, z2) is (x, y2, z2) is $line".logDebug()
                }
                run {
                    val coordinate1 = lines[2].end
                    val line = ILine3D.new(coordinate1.coordinate(), blockCoordinate2)
                    add(line)

                    "(x2, y, z2) -> (x2, y2, z2) is $line".logDebug()
                }
            }
        }
        initialized = true
    }
}