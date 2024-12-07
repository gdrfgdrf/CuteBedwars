package io.github.gdrfgdrf.cutebedwars.selection

import io.github.gdrfgdrf.cutebedwars.abstracts.math.base.IPoint
import io.github.gdrfgdrf.cutebedwars.abstracts.math.geometry.three.IOutlineBox
import io.github.gdrfgdrf.cutebedwars.abstracts.math.geometry.two.IPoint2D
import io.github.gdrfgdrf.cutebedwars.abstracts.math.geometry.three.IPoint3D
import io.github.gdrfgdrf.cutebedwars.abstracts.math.calculate.ICuboids
import io.github.gdrfgdrf.cutebedwars.abstracts.math.geometry.two.ICircle2D
import io.github.gdrfgdrf.cutebedwars.abstracts.math.geometry.three.ILine3D
import io.github.gdrfgdrf.cutebedwars.abstracts.math.common.mathNumber
import io.github.gdrfgdrf.cutebedwars.abstracts.particles.IParticleGroup
import io.github.gdrfgdrf.cutebedwars.abstracts.particles.IParticles
import io.github.gdrfgdrf.cutebedwars.abstracts.selection.ISelection
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.IStopSignal
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.logDebug
import io.github.gdrfgdrf.cutebedwars.beans.pojo.common.Coordinate
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import io.github.gdrfgdrf.multimodulemediator.bean.ArgumentSet
import org.bukkit.Particle
import org.bukkit.entity.Player
import kotlin.math.absoluteValue

@ServiceImpl("selection", needArgument = true)
class Selection(
    override val pos1: Coordinate,
    override val pos2: Coordinate
) : ISelection {
    constructor(argumentSet: ArgumentSet) : this(
        argumentSet.args[0] as Coordinate,
        argumentSet.args[1] as Coordinate
    )

    private val lines = arrayListOf<ILine3D>()
    private val otherPoints = arrayListOf<IPoint3D>()
    private var cachedParticleGroup: IParticleGroup? = null

    private var initialized = false
    private var destroyed = false

    private fun check() {
        if (!initialized) {
            throw IllegalStateException("this selection is not initialized")
        }
    }

    private fun check2() {
        if (destroyed) {
            throw IllegalStateException("this selection has been destroyed")
        }
    }

    override fun spawnParticle(particle: Particle, playerCollection: Collection<Player>, frequency: Long): IStopSignal {
        check()
        check2()
        if (cachedParticleGroup != null) {
            return cachedParticleGroup!!.spawn(playerCollection, frequency)
        }

        val managedParticle = IParticles.instance().getOrCreate(particle)
        val particleGroup = managedParticle.create("selection-particle")

        lines.forEach { line3d ->
            line3d.divide(0.5.mathNumber()).forEach { point: IPoint ->
                val point3d = point as IPoint3D

                val x = point3d.x
                val y = point3d.y
                val z = point3d.z
                particleGroup.add(x.toDouble(), y.toDouble(), z.toDouble())
            }
        }
        otherPoints.forEach { point3d ->
            val x = point3d.x
            val y = point3d.y
            val z = point3d.z
            particleGroup.add(x.toDouble(), y.toDouble(), z.toDouble())
        }

        cachedParticleGroup = particleGroup
        return cachedParticleGroup!!.spawn(playerCollection, frequency)
    }

    override fun destroy() {
        destroyed = true
        lines.clear()
        otherPoints.clear()
        cachedParticleGroup?.clear()
    }

    init {
        val centerPoint = Coordinate()
        centerPoint.x = (pos1.x + pos2.x) / 2
        centerPoint.y = (pos1.y + pos2.y) / 2
        centerPoint.z = (pos1.z + pos2.z) / 2

        val blockCoordinate1 = fix(centerPoint, pos1)
        val blockCoordinate2 = fix(centerPoint, pos2)

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

            run {
                "Calculating the diagonal line of the selection".logDebug()

                run {
                    val line = ILine3D.new(blockCoordinate1, blockCoordinate2)
                    add(line)

                    "(x, y, z)(pos1) -> (x2, y2, z2)(pos2) is $line".logDebug()
                }
                run {
                    val coordinate1 = lines[2].end
                    val coordinate2 = lines[4].start

                    val line = ILine3D.new(coordinate1, coordinate2)
                    add(line)

                    "(x2, y, z2) -> (x, y2, z) is $line".logDebug()
                }
                run {
                    val coordinate1 = lines[0].end
                    val coordinate2 = lines[5].end

                    val line = ILine3D.new(coordinate1, coordinate2)
                    add(line)

                    "(x2, y, z) -> (x, y2, z2) is $line".logDebug()
                }
                run {
                    val coordinate1 = lines[1].end
                    val coordinate2 = lines[4].end

                    val line = ILine3D.new(coordinate1, coordinate2)
                    add(line)

                    "(x, y, z2) -> (x2, y2, z) is $line".logDebug()
                }
            }

            val biggerY = if (blockCoordinate1.y >= blockCoordinate2.y) {
                blockCoordinate1.y
            } else {
                blockCoordinate2.y
            }

            val oneThirdY = (biggerY - ((blockCoordinate1.y - blockCoordinate2.y).absoluteValue / 3)).mathNumber()

            run {
                "Calculating the 1 / 3 y cross of the selection".logDebug()

                run {
                    val coordinate1 = Coordinate()
                    coordinate1.x = blockCoordinate1.x
                    coordinate1.y = oneThirdY.toDouble()
                    coordinate1.z = blockCoordinate1.z

                    val coordinate2 = Coordinate()
                    coordinate2.x = blockCoordinate2.x
                    coordinate2.y = oneThirdY.toDouble()
                    coordinate2.z = blockCoordinate1.z

                    val line = ILine3D.new(coordinate1, coordinate2)
                    add(line)

                    "(x, y, z)(pos1)( 1 / 3 y ) -> (x2, y, z)( 1 / 3 y ) is $line".logDebug()
                }
                run {
                    val coordinate1 = Coordinate()
                    coordinate1.x = blockCoordinate1.x
                    coordinate1.y = oneThirdY.toDouble()
                    coordinate1.z = blockCoordinate1.z

                    val coordinate2 = Coordinate()
                    coordinate2.x = blockCoordinate1.x
                    coordinate2.y = oneThirdY.toDouble()
                    coordinate2.z = blockCoordinate2.z

                    val line = ILine3D.new(coordinate1, coordinate2)
                    add(line)

                    "(x, y, z)(pos1)( 1 / 3 y ) -> (x, y, z2)( 1 / 3 y ) is $line".logDebug()
                }
                run {
                    val coordinate1 = lines[16].end
                    val coordinate2 = Coordinate()
                    coordinate2.x = blockCoordinate2.x
                    coordinate2.y = oneThirdY.toDouble()
                    coordinate2.z = blockCoordinate2.z

                    val line = ILine3D.new(coordinate1.coordinate(), coordinate2)
                    add(line)

                    "(x2, y, z)( 1 / 3 y ) -> (x2, y, z2)( 1 / 3 y ) is $line".logDebug()
                }
                run {
                    val coordinate1 = lines[17].end
                    val coordinate2 = lines[18].end

                    val line = ILine3D.new(coordinate1, coordinate2)
                    add(line)

                    "(x, y, z2)( 1 / 3 y ) -> (x2, y, z2)( 1 / 3 y ) is $line".logDebug()
                }
                run {
                    val coordinate1 = lines[6].half().end
                    val coordinate2 = lines[5].half().end

                    val coordinate1Result = IPoint3D.new(coordinate1.x, oneThirdY, coordinate1.z)
                    val coordinate2Result = IPoint3D.new(coordinate2.x, oneThirdY, coordinate2.z)

                    val line = ILine3D.new(coordinate1Result, coordinate2Result)
                    add(line)

                    "(x2, y2, z) -> (x2, y2, z2)(pos2) / 2 ( 1 / 3 y2 ) -> (x, y2, z) -> (x, y2, z2) / 2 ( 1 / 3 y2 ) is $line".logDebug()
                }
                run {
                    val coordinate1 = lines[4].half().end
                    val coordinate2 = lines[7].half().end

                    val coordinate1Result = IPoint3D.new(coordinate1.x, oneThirdY, coordinate1.z)
                    val coordinate2Result = IPoint3D.new(coordinate2.x, oneThirdY, coordinate2.z)

                    val line = ILine3D.new(coordinate1Result, coordinate2Result)
                    add(line)

                    ("(x, y2, z) -> (x2, y2, z) / 2 ( 1 / 3 y2 ) -> (x, y2, z2) -> (x2, y2, z2)(pos2) / 2 ( 1 / 3 y2 ) is $line").logDebug()
                }
            }

            run {
                "Calculating the 1 / 3 y semicircle of the selection".logDebug()

                val box = IOutlineBox.new(blockCoordinate1, blockCoordinate2)

                run {
                    val line = lines[21]
                    val line2 = lines[16]

                    val center2dX = line.start.x
                    val center2dY = line.start.z
                    val R = line2.length() / 4

                    val circle2d = ICircle2D.new(center2dX, center2dY, R)
                    var result = circle2d.divide(1.mathNumber(), y = oneThirdY)
                    result = result.stream()
                        .filter {
                            return@filter box.contains(it)
                        }
                        .toList()

                    otherPoints.addAll(result)

                    "center x: $center2dX, center y: ${line.start.y}, center z: $center2dY, r: $R".logDebug()
                }
                run {
                    val line = lines[21]
                    val line2 = lines[16]

                    val center2dX = line.end.x
                    val center2dY = line.end.z
                    val R = line2.length() / 4

                    val circle2d = ICircle2D.new(center2dX, center2dY, R)
                    var result = circle2d.divide(1.mathNumber(), y = oneThirdY)
                    result = result.stream()
                        .filter {
                            return@filter box.contains(it)
                        }
                        .toList()

                    otherPoints.addAll(result)

                    "center x: $center2dX, center y: ${line.end.y}, center z: $center2dY, r: $R".logDebug()
                }
                run {
                    val line = lines[20]
                    val line2 = lines[17]

                    val center2dX = line.start.x
                    val center2dY = line.start.z
                    val R = line2.length() / 4

                    val circle2d = ICircle2D.new(center2dX, center2dY, R)
                    var result = circle2d.divide(1.mathNumber(), y = oneThirdY)
                    result = result.stream()
                        .filter {
                            return@filter box.contains(it)
                        }
                        .toList()

                    otherPoints.addAll(result)

                    "center x: $center2dX, center y: ${line.start.y}, center z: $center2dY, r: $R".logDebug()
                }
                run {
                    val line = lines[20]
                    val line2 = lines[17]

                    val center2dX = line.end.x
                    val center2dY = line.end.z
                    val R = line2.length() / 4

                    val circle2d = ICircle2D.new(center2dX, center2dY, R)
                    var result = circle2d.divide(1.mathNumber(), y = oneThirdY)
                    result = result.stream()
                        .filter {
                            return@filter box.contains(it)
                        }
                        .toList()

                    otherPoints.addAll(result)

                    "center x: $center2dX, center y: ${line.end.y}, center z: $center2dY, r: $R".logDebug()
                }
            }

            run {
                "Calculating the 1 / 3 central circle of the selection".logDebug()

                val blockPoint3d1 = IPoint3D.new(
                    blockCoordinate1.x,
                    blockCoordinate1.y,
                    blockCoordinate1.z
                )
                val blockPoint3d2 = IPoint3D.new(
                    blockCoordinate2.x,
                    blockCoordinate2.y,
                    blockCoordinate2.z
                )
                val line1 = lines[20]
                val line2 = lines[21]

                val center = ICuboids.instance().geometricCenter(blockPoint3d1, blockPoint3d2)
                val center2d = IPoint2D.new(center.x, center.z)

                val R1 = (line1.length() / 2) - (lines[17].length() / 4)
                val R2 = (line2.length() / 2) - (lines[16].length() / 4)
                val R = if (R1 >= R2) {
                    R2.abs()
                } else {
                    R1.abs()
                }

                val circle = ICircle2D.new(center2d, R)
                val result = circle.divide(1.mathNumber(), y = oneThirdY)

                otherPoints.addAll(result)

                "center x: ${center.x}, center y: ${center.y}, center z: ${center.z}, r: $R".logDebug()
            }

            val twoThirdsY = (biggerY - ( 2 * (blockCoordinate1.y - blockCoordinate2.y).absoluteValue / 3)).mathNumber()

            run {
                "Calculating the 2 / 3 y cross of the selection".logDebug()

                run {
                    val coordinate1 = Coordinate()
                    coordinate1.x = blockCoordinate1.x
                    coordinate1.y = twoThirdsY.toDouble()
                    coordinate1.z = blockCoordinate1.z

                    val coordinate2 = Coordinate()
                    coordinate2.x = blockCoordinate2.x
                    coordinate2.y = twoThirdsY.toDouble()
                    coordinate2.z = blockCoordinate1.z

                    val line = ILine3D.new(coordinate1, coordinate2)
                    add(line)

                    "(x, y, z)(pos1)( 2 / 3 y ) -> (x2, y, z)( 2 / 3 y ) is $line".logDebug()
                }
                run {
                    val coordinate1 = Coordinate()
                    coordinate1.x = blockCoordinate1.x
                    coordinate1.y = twoThirdsY.toDouble()
                    coordinate1.z = blockCoordinate1.z

                    val coordinate2 = Coordinate()
                    coordinate2.x = blockCoordinate1.x
                    coordinate2.y = twoThirdsY.toDouble()
                    coordinate2.z = blockCoordinate2.z

                    val line = ILine3D.new(coordinate1, coordinate2)
                    add(line)

                    "(x, y, z)(pos1)( 2 / 3 y ) -> (x, y, z2)( 2 / 3 y ) is $line".logDebug()
                }
                run {
                    val coordinate1 = lines[22].end
                    val coordinate2 = Coordinate()
                    coordinate2.x = blockCoordinate2.x
                    coordinate2.y = twoThirdsY.toDouble()
                    coordinate2.z = blockCoordinate2.z

                    val line = ILine3D.new(coordinate1.coordinate(), coordinate2)
                    add(line)

                    "(x2, y, z)( 2 / 3 y ) -> (x2, y, z2)( 2 / 3 y ) is $line".logDebug()
                }
                run {
                    val coordinate1 = lines[23].end
                    val coordinate2 = lines[24].end

                    val line = ILine3D.new(coordinate1, coordinate2)
                    add(line)

                    "(x, y, z2)( 2 / 3 y ) -> (x2, y, z2)( 2 / 3 y ) is $line".logDebug()
                }
            }

            val oneFirstY = (biggerY - ((blockCoordinate1.y - blockCoordinate2.y).absoluteValue / 2)).mathNumber()

            run {
                "Calculating the 1 / 2 y cross of the selection".logDebug()

                run {
                    val coordinate1 = Coordinate()
                    coordinate1.x = blockCoordinate1.x
                    coordinate1.y = oneFirstY.toDouble()
                    coordinate1.z = blockCoordinate1.z

                    val coordinate2 = Coordinate()
                    coordinate2.x = blockCoordinate2.x
                    coordinate2.y = oneFirstY.toDouble()
                    coordinate2.z = blockCoordinate1.z

                    val line = ILine3D.new(coordinate1, coordinate2)
                    add(line)

                    "(x, y, z)(pos1)( 1 / 2 y ) -> (x2, y, z)( 1 / 2 y ) is $line".logDebug()
                }
                run {
                    val coordinate1 = Coordinate()
                    coordinate1.x = blockCoordinate1.x
                    coordinate1.y = oneFirstY.toDouble()
                    coordinate1.z = blockCoordinate1.z

                    val coordinate2 = Coordinate()
                    coordinate2.x = blockCoordinate1.x
                    coordinate2.y = oneFirstY.toDouble()
                    coordinate2.z = blockCoordinate2.z

                    val line = ILine3D.new(coordinate1, coordinate2)
                    add(line)

                    "(x, y, z)(pos1)( 1 / 2 y ) -> (x, y, z2)( 1 / 2 y ) is $line".logDebug()
                }
                run {
                    val coordinate1 = lines[26].end
                    val coordinate2 = Coordinate()
                    coordinate2.x = blockCoordinate2.x
                    coordinate2.y = oneFirstY.toDouble()
                    coordinate2.z = blockCoordinate2.z

                    val line = ILine3D.new(coordinate1.coordinate(), coordinate2)
                    add(line)

                    "(x2, y, z)( 1 / 2 y ) -> (x2, y, z2)( 1 / 2 y ) is $line".logDebug()
                }
                run {
                    val coordinate1 = lines[27].end
                    val coordinate2 = lines[28].end

                    val line = ILine3D.new(coordinate1, coordinate2)
                    add(line)

                    "(x, y, z2)( 1 / 2 y ) -> (x2, y, z2)( 1 / 2 y ) is $line".logDebug()
                }
            }

            run {
                "Calculating the top cross of the selection".logDebug()

                run {
                    val coordinate2 = lines[2].end.coordinate()

                    val line = ILine3D.new(blockCoordinate1, coordinate2)
                    add(line)

                    "(x, y, z)(pos1) -> (x2, y, z2) is $line".logDebug()
                }
                run {
                    val coordinate1 = lines[1].end
                    val coordinate2 = lines[0].end

                    val line = ILine3D.new(coordinate1, coordinate2)
                    add(line)

                    "(x, y, z2) -> (x2, y, z) is $line".logDebug()
                }
            }

            run {
                "Calculating the bottom cross of the selection".logDebug()

                run {
                    val coordinate1 = lines[8].end.coordinate()

                    val line = ILine3D.new(coordinate1, blockCoordinate2)
                    add(line)

                    "(x, y2, z) -> (x2, y2, z2)(pos2) is $line".logDebug()
                }
                run {
                    val coordinate1 = lines[5].end
                    val coordinate2 = lines[4].end

                    val line = ILine3D.new(coordinate1, coordinate2)
                    add(line)

                    "(x, y2, z2) -> (x2, y2, z) is $line".logDebug()
                }
            }
        }

        initialized = true
    }

    companion object {
        fun fix(centerPoint: Coordinate, point: Coordinate): Coordinate {
            val coordinate = Coordinate()
            coordinate.x = if (point.x > centerPoint.x) point.x + 1 else point.x
            coordinate.y = if (point.y > centerPoint.y) point.y + 1 else point.y
            coordinate.z = if (point.z > centerPoint.z) point.z + 1 else point.z
            return coordinate
        }
    }

}