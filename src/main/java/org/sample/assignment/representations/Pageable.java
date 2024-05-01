package org.sample.assignment.representations;

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
@AllArgsConstructor
@NoArgsConstructor
public class Pageable implements Serializable {
    private static final long serialVersionUID = 5784755658375539810L;

    @Schema(
            accessMode = Schema.AccessMode.READ_ONLY,
            description = "Current page number",
            example = "1")
    private Integer pageNo;

    @Schema(
            accessMode = Schema.AccessMode.READ_ONLY,
            description = "Total number of records in current page",
            example = "100")
    private Integer pageSize;

    @Schema(
            accessMode = Schema.AccessMode.READ_ONLY,
            description = "Total number of pages",
            example = "10")
    private Integer pages;

    @Schema(
            accessMode = Schema.AccessMode.READ_ONLY,
            description = "Total number of records",
            example = "1000")
    private Integer size;
}

