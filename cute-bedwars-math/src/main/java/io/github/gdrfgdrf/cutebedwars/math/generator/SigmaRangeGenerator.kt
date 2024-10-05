package io.github.gdrfgdrf.cutebedwars.math.generator

import io.github.gdrfgdrf.cutebedwars.math.base.AbstractRangeGenerator
import io.github.gdrfgdrf.cutebedwars.math.common.MathNumber
import io.github.gdrfgdrf.cutebedwars.math.common.mathNumber
import io.github.gdrfgdrf.cutebedwars.math.enums.Precisions

class SigmaRangeGenerator(
    private val start: Int,
    private val end: Int,
    private val precision: Precisions
) : AbstractRangeGenerator() {
    override fun start(): MathNumber = start.mathNumber()
    override fun end(): MathNumber = end.mathNumber()

    override fun forEach(block: (MathNumber) -> Unit) {
        val realStart = start().cut(precision)
        val realEnd = end().cut(precision)

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
        val realStart = start().cut(precision)
        val realEnd = end().cut(precision)

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