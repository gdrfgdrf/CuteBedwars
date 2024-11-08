package io.github.gdrfgdrf.cutebedwars.selection

import io.github.gdrfgdrf.cutebedwars.beans.pojo.common.Coordinate

class Line(
    val start: Coordinate,
    val end: Coordinate
) {
    override fun toString(): String {
        return "(${start.x}, ${start.y}, ${start.z}) -> (${end.x}, ${end.y}, ${end.z})"
    }



}