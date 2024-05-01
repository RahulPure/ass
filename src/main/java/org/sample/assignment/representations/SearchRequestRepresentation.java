package org.sample.assignment.representations;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @author RahulPure
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Valid
public class SearchRequestRepresentation  implements Serializable {
    private static final long serialVersionUID = 7708858481661144731L;
    public SearchRequestRepresentation(List<SearchCriteria> search, List<FilterCriteria> filter) {
        this.search = search;
        this.filter = filter;
    }
    @ArraySchema(
            schema =
            @Schema(
                    accessMode = Schema.AccessMode.WRITE_ONLY,
                    description = "Search criteria",
                    implementation = SearchCriteria.class))
    private List<SearchCriteria> search;

    @ArraySchema(
            schema =
            @Schema(
                    accessMode = Schema.AccessMode.WRITE_ONLY,
                    description = "Filter criteria",
                    implementation = FilterCriteria.class))
    private List<FilterCriteria> filter;
}
