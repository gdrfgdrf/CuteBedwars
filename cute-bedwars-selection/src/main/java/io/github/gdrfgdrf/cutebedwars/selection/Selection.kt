package io.github.gdrfgdrf.cutebedwars.selection

import io.github.gdrfgdrf.cutebedwars.abstracts.math.geometry.base.IPoint
import io.github.gdrfgdrf.cutebedwars.abstracts.math.geometry.three.IOutlineBox
import io.github.gdrfgdrf.cutebedwars.abstracts.math.geometry.two.IPoint2D
import io.github.gdrfgdrf.cutebedwars.abstracts.math.geometry.three.IPoint3D
import io.github.gdrfgdrf.cutebedwars.abstracts.math.geometry.two.ICircle2D
import io.github.gdrfgdrf.cutebedwars.abstracts.math.geometry.three.ILine3D
import io.github.gdrfgdrf.cutebedwars.abstracts.math.common.mathNumber
import io.github.gdrfgdrf.cutebedwars.abstracts.math.geometry.two.ILine2D
import io.github.gdrfgdrf.cutebedwars.abstracts.math.geometry.two.IRectangle
import io.github.gdrfgdrf.cutebedwars.abstracts.particles.IParticleGroup
import io.github.gdrfgdrf.cutebedwars.abstracts.particles.IParticles
import io.github.gdrfgdrf.cutebedwars.abstracts.selection.ISelection
import io.github.gdrfgdrf.cutebedwars.abstracts.utils.IStopSignal
import io.github.gdrfgdrf.cutebedwars.beans.pojo.common.Coordinate
import io.github.gdrfgdrf.multimodulemediator.annotation.ServiceImpl
import io.github.gdrfgdrf.multimodulemediator.bean.ArgumentSet
import org.bukkit.Particle
import org.bukkit.entity.Player
import java.util.stream.Stream
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

        val baseBlockCoordinate1 = fix(centerPoint, pos1)
        val baseBlockCoordinate2 = fix(centerPoint, pos2)

        // 保证 blockCoordinate1 始终在 blockCoordinate2 之上
        val blockCoordinate1 = if (baseBlockCoordinate1.y >= baseBlockCoordinate2.y) {
            baseBlockCoordinate1
        } else {
            baseBlockCoordinate2
        }
        val blockCoordinate2 = if (baseBlockCoordinate2.y < baseBlockCoordinate1.y) {
            baseBlockCoordinate2
        } else {
            baseBlockCoordinate1
        }

        val outlineBox = IOutlineBox.new(blockCoordinate1, blockCoordinate2)

        // 对角线
        outlineBox.addLine(outlineBox.A_, outlineBox.C)
        outlineBox.addLine(outlineBox.C_, outlineBox.A)
        outlineBox.addLine(outlineBox.B_, outlineBox.O)
        outlineBox.addLine(outlineBox.D_, outlineBox.B)

        // 顶部交叉
        outlineBox.addLine(outlineBox.A_, outlineBox.O)
        outlineBox.addLine(outlineBox.D_, outlineBox.A)
        // 底部交叉
        outlineBox.addLine(outlineBox.B_, outlineBox.C)
        outlineBox.addLine(outlineBox.C_, outlineBox.B)

        // 三分之一
        val oneThirdY = (blockCoordinate1.y - ((blockCoordinate1.y - blockCoordinate2.y).absoluteValue / 3)).mathNumber()

        // 三分之一处矩形
        val rectangle = IRectangle.new(
            blockCoordinate1.x,
            blockCoordinate1.z,
            blockCoordinate2.x,
            blockCoordinate2.z
        )

        // 三分之一处十字的起点和终点
        val crossLine1Start = rectangle.a.half.end as IPoint2D
        val crossLine1End = rectangle.c.half.end as IPoint2D
        val crossLine2Start = rectangle.b.half.end as IPoint2D
        val crossLine2End = rectangle.d.half.end as IPoint2D

        // 三分之一处十字
        val crossLine1 = ILine2D.new(crossLine1Start, crossLine1End)
        val crossLine2 = ILine2D.new(crossLine2Start, crossLine2End)

        // 将三分之一处十字添加到矩形中
        rectangle.addShape(crossLine1)
        rectangle.addShape(crossLine2)

        // 三分之一处的四个半圆的半径
        val semiCircleR1 = rectangle.a.length / 4
        val semiCircleR2 = rectangle.b.length / 4

        // 三分之一处的四个半圆
        val semicircle1 = ICircle2D.new(crossLine1Start, semiCircleR1)
            .divide3d(0.5.mathNumber(), oneThirdY)
        val semicircle2 = ICircle2D.new(crossLine2Start, semiCircleR2)
            .divide3d(0.5.mathNumber(), oneThirdY)
        val semicircle3 = ICircle2D.new(crossLine1End, semiCircleR1)
            .divide3d(0.5.mathNumber(), oneThirdY)
        val semicircle4 = ICircle2D.new(crossLine2End, semiCircleR2)
            .divide3d(0.5.mathNumber(), oneThirdY)

        // 将三分之一处的四个半圆添加到 Outline Box 中
        Stream.of(semicircle1, semicircle2, semicircle3, semicircle4)
            .flatMap {
                it.stream()
            }
            .forEach { point3d ->
                runCatching {
                    outlineBox.addPoint(point3d)
                }
            }

        // 三分之一处中心圆的半径
        val centralCircleR1 = (crossLine1.length / 2) - semiCircleR1
        val centralCircleR2 = (crossLine2.length / 2) - semiCircleR2
        val centralCircleR = if (centralCircleR1 >= centralCircleR2) {
            centralCircleR2.abs()
        } else {
            centralCircleR1.abs()
        }
        // 三分之一处中心圆
        val centralCircle = ICircle2D.new(rectangle.center, centralCircleR)

        // 将三分之一处中心圆添加到矩形中
        rectangle.addShape(centralCircle)

        // 将矩形添加到 Outline Box 中
        outlineBox.addShape(rectangle, 0.5.mathNumber(), oneThirdY)

        // 添加到选区内
        outlineBox.divide3d(0.5.mathNumber()).forEach {
            otherPoints.add(it)
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