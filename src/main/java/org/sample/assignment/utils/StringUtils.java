package org.sample.assignment.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.Objects;

@Slf4j
public class StringUtils {
    public static final String EMPTY_STRING = "";
    private StringUtils() {}
    public static boolean isNotBlank(Object string) {
        return Objects.nonNull(string) && !convertToString(string).equals(EMPTY_STRING);
    }

    public static boolean isEmpty(final CharSequence input) {
        return input == null || input.toString().trim().length() == 0;
    }

    public static boolean isNullOrEmpty(String string) {
        return convertToString(string).equals(EMPTY_STRING);
    }

    public static boolean isNull(String value) {
        return value == null || value.isBlank();
    }

    public static boolean isWhitespace(final CharSequence input) {
        if (input == null) {
            return false;
        }
        final int sz = input.length();
        for (int i = 0; i < sz; i++) {
            if (!Character.isWhitespace(input.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static String convertToString(Object object) {
        if (Objects.isNull(object)) return EMPTY_STRING;
        return String.valueOf(object);
    }

    public static boolean isBlank(Object string) {
        return convertToString(string).equals(EMPTY_STRING);
    }

    public static boolean isNotEmpty(Collection<?> collection) {
        return Objects.nonNull(collection) && !collection.isEmpty();
    }

    public static boolean isValidString(String string) {
        return string != null && !"null".equalsIgnoreCase(string.trim()) && string.trim().length() > 0;
    }

    public static boolean isNotEmptyString(String string) {
        return string != null && !"null".equalsIgnoreCase(string.trim()) && string.length() > 0;
    }

}
