package org.sample.assignment.constants;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

/**
 * @author RahulPure
 */
@Getter
public enum SortOrder implements BaseEnum {
    ASC("asc"),
    DESC("desc");
    private final String title;

    SortOrder(String value) {
        this.title = value;
    }

    @Override
    public String title() {
        return this.title;
    }

    public static List<String> getAllSortOrder() {
        return Arrays.stream(SortOrder.values()).map(e -> e.title).toList();
    }
}
