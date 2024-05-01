package org.sample.assignment.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.sample.assignment.utils.StringUtils;

import java.text.MessageFormat;
import java.util.Objects;
import java.util.regex.Pattern;

import static org.sample.assignment.constants.ValidationMessages.REQUIRED_VALIDATION_ERR_MSG;

public class StringValueValidator implements ConstraintValidator<ValidateStringValue, String> {

    int nameColumnLength =20;

    private Integer allowedLength;
    private boolean nullCheck;
    private String field;

    private String regexp;
    private String regexpMessage;

    @Override
    public void initialize(ValidateStringValue constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        String column = constraintAnnotation.column();
        field = constraintAnnotation.field();
        nullCheck = constraintAnnotation.nullCheck();
        regexp = constraintAnnotation.regexp();
        regexpMessage = constraintAnnotation.regexpMessage();

         if("name".equalsIgnoreCase(column) ){
            allowedLength = nameColumnLength;
        }
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();
        if (StringUtils.isNull(value)) {
                context
                        .buildConstraintViolationWithTemplate(
                                MessageFormat.format(
                                        REQUIRED_VALIDATION_ERR_MSG,
                                        org.apache.commons.lang3.StringUtils.capitalize(field)))
                        .addConstraintViolation();
            return !nullCheck;
        }

        if (Objects.isNull(allowedLength)) return true;

        if (!regexp.isBlank() && !Pattern.matches(regexp.toLowerCase(), value.toLowerCase())) {
            context
                    .buildConstraintViolationWithTemplate(MessageFormat.format(regexpMessage, value))
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
