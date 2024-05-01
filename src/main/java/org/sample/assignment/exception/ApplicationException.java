package org.sample.assignment.exception;

import lombok.Getter;
import org.sample.assignment.errorcodes.ApplicationErrorCode;
import org.springframework.http.HttpStatus;

import java.io.Serial;
import java.util.List;

/**
 * @author RahulPure
 */
@Getter
public class ApplicationException extends  RuntimeException
{
    @Serial
    private static final long serialVersionUID = 239846720984447L;
    private final ApplicationErrorCode code;
    private final HttpStatus status;
    private final List<FieldValidation> messages;

    public ApplicationException(String message, ApplicationErrorCode code) {
        super(message);
        this.code = code;
        status = null;
        messages = null;
    }

    public ApplicationException(String message, ApplicationErrorCode code, HttpStatus status) {
        super(message);
        this.code = code;
        this.status = status;
        messages = null;
    }

    public ApplicationException(List<FieldValidation> messages, ApplicationErrorCode code) {
        this.code = code;
        this.messages = messages;
        status = null;
    }

    public ApplicationException(
            List<FieldValidation> messages, ApplicationErrorCode code, HttpStatus status) {
        this.code = code;
        this.messages = messages;
        this.status = status;
    }
}
