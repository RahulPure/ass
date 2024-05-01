package org.sample.assignment.exception;

import lombok.Getter;
import org.sample.assignment.errorcodes.ApplicationErrorCode;
import org.springframework.http.HttpStatus;

@Getter
public class ResourceConflictException extends ApplicationException {

    public ResourceConflictException(String message, ApplicationErrorCode code) {
        super(message, code);
    }

    public ResourceConflictException(String message, ApplicationErrorCode code, HttpStatus status) {
        super(message, code, status);
    }
}
