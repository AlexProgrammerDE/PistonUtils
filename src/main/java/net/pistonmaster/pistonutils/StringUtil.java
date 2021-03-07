package net.pistonmaster.pistonutils;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"unused"})
public class StringUtil {
    private StringUtil() {}

    /**
     * Very useful for tab completion
     * @param original The argument to compare with
     * @param options A list of options
     * @return A list with possible completions.
     */
    public static List<String> copyPartialMatches(String original, List<String> options) {
        List<String> list = new ArrayList<>();

        for (String str : options) {
            if (startsWithIgnoreCase(original, str))
                list.add(str);
        }

        return list;
    }

    /**
     * Check if it starts with with ignored case.
     * @param str1 The string to check
     * @param str2 The string that the first string starts with
     * @return Whether it starts with the second string
     */
    public static boolean startsWithIgnoreCase(String str1, String str2) {
        return str1.toLowerCase().startsWith(str2.toLowerCase());
    }
}
