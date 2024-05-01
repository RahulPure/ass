package org.sample.assignment.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Retention(RUNTIME)
@Target({FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Repeatable(ValidateStringValues.class)
@Constraint(validatedBy = StringValueValidator.class)
public @interface ValidateStringValue {
    String message() default "Maximum character limit exceeded.";

    boolean nullCheck() default false;

    String column() default "Name";

    Class<?>[] groups() default {};

    String field() default "Name";

    String regexp() default "";

    String regexpMessage() default "";

    int minLength() default 0;

    Class<? extends Payload>[] payload() default {};
}
