package org.sample.assignment.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author RahulPure
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponseBody<T> {
    private String code;
    private T message;
    private T messages;

    public ErrorResponseBody(String code, T message) {
        this.code = code;
        this.message = message;
    }
}
