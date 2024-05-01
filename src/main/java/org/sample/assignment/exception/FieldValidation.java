package org.sample.assignment.exception;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author RahulPure
 */
@Getter
@Setter
public class FieldValidation implements Serializable
{
    private static final long serialVersionUID = 9999667543234434L;
    private String fieldName;
    private String violation;

    public FieldValidation(String fieldName, String violation) {
        this.fieldName = fieldName;
        this.violation = violation;
    }
}
