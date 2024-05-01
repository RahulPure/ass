package org.sample.assignment.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * @author RahulPure
 */
@Getter
@NoArgsConstructor
public class ResponseBody<T>
{
    private HttpStatus status;
    private T payload;

    public ResponseBody(HttpStatus status, T payload) {
        this.status = status;
        this.payload = payload;
    }

}
