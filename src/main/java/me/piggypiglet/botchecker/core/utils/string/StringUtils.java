package me.piggypiglet.botchecker.core.utils.string;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class StringUtils {
    public static boolean startsWith(String str, List<String> elements) {
        return lowercaseParallelStream(elements).anyMatch(str.toLowerCase()::startsWith);
    }

    public static boolean equalsIgnoreCase(String str, String... elements) {
        return lowercaseParallelStream(Arrays.asList(elements)).anyMatch(str::equalsIgnoreCase);
    }

    private static Stream<String> lowercaseParallelStream(List<String> list) {
        return list.stream().map(String::toLowerCase).parallel();
    }
}
