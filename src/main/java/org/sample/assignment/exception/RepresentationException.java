package org.sample.assignment.exception;

import lombok.Getter;
import org.sample.assignment.errorcodes.ApplicationErrorCode;
import org.springframework.http.HttpStatus;

@Getter
public class RepresentationException extends ApplicationException {

    public RepresentationException(String message, ApplicationErrorCode code) {
        super(message, code);
    }

    public RepresentationException(String message, ApplicationErrorCode code, HttpStatus status) {
        super(message, code, status);
    }
}
