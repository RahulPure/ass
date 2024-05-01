package org.sample.assignment.representations;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author RahulPure
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DeleteRepresentation implements Serializable {
    private static final long serialVersionUID = 4968835416152934170L;

    @Schema(
            accessMode = Schema.AccessMode.READ_WRITE,
            description = "Resource Id",
            required = true,
            format = "string")
    private String id;

    @Schema(
            accessMode = Schema.AccessMode.READ_ONLY,
            description = "Response message",
            example = "Success")
    private String message;
}