package org.sample.assignment.constants;

/**
 * @author RahulPure
 */
public enum Delimiter implements BaseEnum{
    EMPTY_STRING(""),
    COLON(":"),
    SEMICOLON(";"),
    FWD_SLASH("/"),
    EQUAL("="),
    DOUBLE_FWD_SLASH("//"),
    UNDERSCORE("_"),
    SPACE(" "),
    COMMA(","),
    HYPHEN("-"),
    PERCENTAGE("%"),
    SINGLE_QUOTE("'"),
    DOUBLE_QUOTE("\""),
    DOT(".");


    private final String title;
    Delimiter(String title) {
        this.title = title;
    }

    @Override
    public String title() {
        return title;
    }

}
