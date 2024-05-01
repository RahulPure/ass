package org.sample.assignment.representations;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Collection;

/**
 * @author RahulPure
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SearchResponseRepresentation <T> implements Serializable {
    private static final long serialVersionUID = 1111700000000000008L;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Pagination information")
    private Pageable pageable;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Collection of representation")
    private Collection<T> items;
}
