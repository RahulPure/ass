package org.sample.assignment.exception;

import lombok.Getter;
import org.sample.assignment.errorcodes.ApplicationErrorCode;
import org.springframework.http.HttpStatus;

/**
 * @author RahulPure
 */
@Getter
public class DataFormatException extends ApplicationException {

    public DataFormatException(String message, ApplicationErrorCode code) {
        super(message, code);
    }

    public DataFormatException(String message, ApplicationErrorCode code, HttpStatus status) {
        super(message, code, status);
    }
}
