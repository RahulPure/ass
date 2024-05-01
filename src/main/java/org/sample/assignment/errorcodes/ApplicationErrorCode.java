package org.sample.assignment.errorcodes;

import lombok.Getter;

/**
 * @author RahulPure
 */
@Getter
public enum ApplicationErrorCode
{
    REQUEST_HEADER_NOT_FOUND("RequestHeaderNotFound"),
    METHOD_NOT_ALLOWED("MethodNotAllowed"),
    REQUEST_BODY_NOT_FOUND("RequestBodyNotFound"),
    HTTP_METHOD_INVALID("MethodNotSupported"),
    CONSTRAINT_VIOLATION("ConstraintViolation"),
    FIELD_VALIDATION_ERROR("FieldValidationError"),
    REQUEST_HEADER_INVALID("RequestHeaderInvalid"),
    RESOURCE_NOT_FOUND("ResourceNotFound"),
    REQUEST_BODY_INVALID("RequestBodyInvalid"),
    RESOURCE_CONFLICT("ResourceConflict"),
    RESULTS_NOT_FOUND("ResultsNotFound"),
    UNEXPECTED_ERROR("UnexpectedError");


    private final String code;

    ApplicationErrorCode(String code) {
        this.code = code;
    }

}
