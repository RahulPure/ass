package org.sample.assignment.exception;

import lombok.Getter;
import org.sample.assignment.errorcodes.ApplicationErrorCode;
import org.springframework.http.HttpStatus;

import java.io.Serial;
@Getter
public class AuthenticationException extends ApplicationException {
    @Serial
    private static final long serialVersionUID = 239846720984447L;

    public AuthenticationException(String message, ApplicationErrorCode code) {
        super(message, code);
    }

    public AuthenticationException(String message, ApplicationErrorCode code, HttpStatus status) {
        super(message, code, status);
    }
}
