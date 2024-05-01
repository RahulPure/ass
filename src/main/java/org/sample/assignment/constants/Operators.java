package org.sample.assignment.constants;

import java.util.Arrays;
import java.util.List;

/**
 * @author RahulPure
 */
public enum Operators implements BaseEnum {
    LIKE("Like"),
    IN("In"),
    STARTS_WITH("StartsWith"),
    ENDS_WITH("EndsWith"),
    EQUALS("Equals"),
    NOT_EQUALS("NotEquals");
    // CONTAINS("Contains", ""),
    // GREATER_THAN("GreaterThan", ""),
    // LESS_THAN("LessThan", ""),
    // NOT_IN("NotIn", ""),
    private final String title;

    Operators(String operator) {
        this.title = operator;
    }

    @Override
    public String title() {
        return this.title;
    }

    public static List<String> getAllOperators() {
        return Arrays.stream(Operators.values()).map(e -> e.title).toList();
    }

    @Deprecated(forRemoval = true)
    public static List<String> getSearchOperators() {
        return Arrays.asList(Operators.IN.title(), Operators.EQUALS.title(), Operators.NOT_EQUALS.title());
    }

    @Deprecated(forRemoval = true)
    public static List<Operators> getFilterOperators() {
        return Arrays.asList(
                Operators.LIKE, Operators.EQUALS, Operators.STARTS_WITH, Operators.ENDS_WITH);
    }
}

