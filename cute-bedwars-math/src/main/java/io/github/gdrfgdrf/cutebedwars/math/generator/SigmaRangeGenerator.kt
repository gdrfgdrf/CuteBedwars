package io.github.gdrfgdrf.cutebedwars.math.generator

import io.github.gdrfgdrf.cutebedwars.abstracts.math.IMathNumber
import io.github.gdrfgdrf.cutebedwars.math.base.AbstractRangeGenerator
import io.github.gdrfgdrf.cutebedwars.math.common.MathNumber
import io.github.gdrfgdrf.cutebedwars.math.common.mathNumber
import io.github.gdrfgdrf.cutebedwars.math.enums.Precisions

class SigmaRangeGenerator(
    private val start: Int,
    private val end: Int,
    private val precision: Precisions
) : AbstractRangeGenerator() {
    override fun start(): IMathNumber = start.mathNumber()
    override fun end(): IMathNumber = end.mathNumber()

    override fun forEach(block: (IMathNumber) -> Unit) {
        val realStart = start().cut(precision)
        val realEnd = end().cut(precision)

        val array = arrayListOf<IMathNumber>()
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

    override fun forEachIndexed(block: (Int, IMathNumber) -> Unit) {
        val realStart = start().cut(precision)
        val realEnd = end().cut(precision)

        val array = arrayListOf<IMathNumber>()
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