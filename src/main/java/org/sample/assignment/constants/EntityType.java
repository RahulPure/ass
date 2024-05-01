package org.sample.assignment.constants;

import java.util.Arrays;

/**
 * @author RahulPure
 */
public enum EntityType {
    EMPLOYEE("Employee", "emp"),
    ADDRESS("Address", "add");

    public final String title;
    public final String identifierPrefix;

    EntityType(String title, String identifierPrefix) {
        this.title = title;
        this.identifierPrefix = identifierPrefix;
    }

    public static EntityType getEnumByTitle(String title) {
        for (EntityType e : EntityType.values()) {
            if (e.title.equals(title)) return e;
        }
        return null;
    }

    public static EntityType getByFullIdentifier(String identifier) {
        return Arrays.stream(values())
                .filter(v -> identifier.startsWith(v.identifierPrefix))
                .findAny()
                .orElse(null);
    }

    @Override
    public String toString() {
        return title;
    }

    public String getIdentifierPrefix() {
        return identifierPrefix;
    }

    public String getTitle() {
        return title;
    }
}
