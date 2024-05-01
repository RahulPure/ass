package org.sample.assignment.representations;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author RahulPure
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FilterCriteria implements Serializable {
    private static final long serialVersionUID = 9205849481678705890L;
    private String attribute;
    private String operator;
    private String datatype;

    private String value;

    @Schema(hidden = true)
    @JsonIgnore
    public boolean isValid() {
        return Objects.nonNull(attribute) && Objects.nonNull(operator) && Objects.nonNull(datatype);
    }
}
