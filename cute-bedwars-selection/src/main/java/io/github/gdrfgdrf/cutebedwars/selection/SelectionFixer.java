package io.github.gdrfgdrf.cutebedwars.selection;

import io.github.gdrfgdrf.cutebedwars.beans.pojo.common.Coordinate;

public class SelectionFixer {
    private final Coordinate centerPoint;

    public SelectionFixer(Coordinate centerPoint) {
        this.centerPoint = centerPoint;
    }

    public Coordinate fixPoint(Coordinate point) {
        return fixPoint(centerPoint, point);
    }

    public static Coordinate fixPoint(Coordinate centerPoint, Coordinate point) {
        Coordinate coordinate = new Coordinate();
        coordinate.setX(point.getX() > centerPoint.getX() ? point.getX() + 1 : point.getX());
        coordinate.setY(point.getY() > centerPoint.getY() ? point.getY() + 1 : point.getY());
        coordinate.setZ(point.getZ() > centerPoint.getZ() ? point.getZ() + 1 : point.getZ());
        return coordinate;
    }

}
