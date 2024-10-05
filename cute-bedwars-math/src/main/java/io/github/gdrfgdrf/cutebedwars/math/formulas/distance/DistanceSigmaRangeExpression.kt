package io.github.gdrfgdrf.cutebedwars.math.formulas.distance

import io.github.gdrfgdrf.cutebedwars.math.base.AbstractRangeExpression
import io.github.gdrfgdrf.cutebedwars.math.common.MathNumber
import io.github.gdrfgdrf.cutebedwars.math.enums.Precisions

class DistanceSigmaRangeExpression(private val start: MathNumber, private val end: MathNumber, private val precision: Precisions) : AbstractRangeExpression() {
    override fun start(): MathNumber = start
    override fun end(): MathNumber = end

    override fun forEach(block: (MathNumber) -> Unit) {
        val realStart = start.cut(precision)
        val realEnd = end.cut(precision)

        val array = arrayListOf<MathNumber>()
        array.add(realStart)
        array.add(realStart)

        while (true) {
            val last = array[array.size - 1]
            val next = precision.next(last)
            array.add(next)
            array.add(next)

            if (next == realEnd) {
                break
            }
        }

        array.forEach(block)
    }

    override fun forEachIndexed(block: (Int, MathNumber) -> Unit) {
        val realStart = start.cut(precision)
        val realEnd = end.cut(precision)

        val array = arrayListOf<MathNumber>()
        array.add(realStart)

        while (true) {
            val last = array[array.size - 1]
            val next = precision.next(last)
            array.add(next)

            if (next == realEnd) {
                break
            }
        }

        array.forEachIndexed(block)
    }
}