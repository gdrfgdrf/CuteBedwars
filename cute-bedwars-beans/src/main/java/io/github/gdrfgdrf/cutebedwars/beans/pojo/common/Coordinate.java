package io.github.gdrfgdrf.cutebedwars.beans.pojo.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.jetbrains.annotations.Nullable;

/**
 * @author gdrfgdrf
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Coordinate {
    private double x;
    private double y;
    private double z;

    public Coordinate blockCoordinate() {
        Coordinate coordinate = new Coordinate();
        coordinate.x = (int) x;
        coordinate.y = (int) y;
        coordinate.z = (int) z;
        return coordinate;
    }

    @Override
    public String toString() {
        return "(%s, %s, %s)".formatted(x, y, z);
    }

    public String parsableString() {
        return  "%s %s %s".formatted(x, y, z);
    }

    public static Coordinate parse(String string) {
        String[] split = string.split(" ");
        if (split.length != 3) {
            throw new IllegalArgumentException("the length of a string separated by a space must be 3");
        }
        double x = Double.parseDouble(split[0]);
        double y = Double.parseDouble(split[1]);
        double z = Double.parseDouble(split[2]);

        Coordinate result = new Coordinate();
        result.x = x;
        result.y = y;
        result.z = z;
        return result;
    }

    @Nullable
    public static Coordinate tryParse(String string) {
        try {
            return parse(string);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
