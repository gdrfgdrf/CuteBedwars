package io.github.gdrfgdrf.cutebedwars.utils;

/**
 * @author gdrfgdrf
 */
public class StringUtils {
    private StringUtils() {}

    public static String fieldNameToJsonKey(String str) {
        String uncapitalize = uncapitalize(str);
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < uncapitalize.length(); i++) {
            char charAt = uncapitalize.charAt(i);
            if (Character.isUpperCase(charAt)) {
                stringBuilder.append("-");
            }
            stringBuilder.append(Character.toLowerCase(charAt));
        }

        return stringBuilder.toString();
    }

    public static String jsonKeyToFieldName(String str) {
        String uncapitalize = uncapitalize(str);
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < uncapitalize.length(); i++) {
            char charAt = uncapitalize.charAt(i);
            if (charAt == '-') {
                i++;
                char charAt2 = uncapitalize.charAt(i);
                stringBuilder.append(Character.toUpperCase(charAt2));
            } else {
                stringBuilder.append(charAt);
            }
        }

        return stringBuilder.toString();
    }

    public static String uncapitalize(String str) {
        if (str == null || str.isEmpty()) {
            return "";
        }
        if (str.length() == 1) {
            return str.toLowerCase();
        }

        String temp = str.substring(1);
        char charAt = str.charAt(0);
        return Character.toLowerCase(charAt) + temp;
    }
}
