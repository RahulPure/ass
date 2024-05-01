package org.sample.assignment.model;

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

    private Integer pageNo;

    private Integer pageSize;

    private Integer pages;

    private Integer size;
}
