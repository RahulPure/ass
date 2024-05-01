package org.sample.assignment.utils;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.sample.assignment.constants.Operators;
import org.sample.assignment.constants.ValidationConstants;
import org.sample.assignment.representations.FilterCriteria;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * @author RahulPure
 */
@AllArgsConstructor
public class FilterCriteriaBuilder implements Consumer<FilterCriteria> {
    private static final String ATTRIBUTE_SPLITTER = ",";
    @Getter
    private Predicate predicate;
    private CriteriaBuilder builder;
    private Root<?> root;

    @Override
    public void accept(FilterCriteria criteria) {

        if (Operators.EQUALS.title().equals(criteria.getOperator()))
        {
            predicate =
                    builder.and(
                            predicate,
                            builder.equal(
                                    root.get(criteria.getAttribute()),
                                    resolveData(criteria.getValue().toLowerCase(), Objects.nonNull(criteria.getDatatype())?criteria.getDatatype():"string")));
        }
        else if (Operators.IN.title().equals(criteria.getOperator())) {
            predicate =
                    builder.and(
                            predicate,
                            builder
                                    .lower(root.get(criteria.getAttribute()))
                                    .in(resolveArrayData(criteria.getValue().toLowerCase())));
        } else {
            predicate =
                    builder.and(
                            predicate,
                            builder.equal(
                                    builder.lower(root.get(criteria.getAttribute())),
                                    resolveData(criteria.getValue().toLowerCase(), Objects.nonNull(criteria.getDatatype())?criteria.getDatatype():"string")));
        }
    }

    private List<Object> resolveArrayData(String value) {
        return Arrays.asList(value.split(ATTRIBUTE_SPLITTER));
    }

    private Object resolveData(String value, String datatype) {
        switch (datatype) {
            case ValidationConstants.ALLOWED_DATATYPE_STRING:
                return value;
            case ValidationConstants.ALLOWED_DATATYPE_LONG:
                return Long.parseLong(value);
            default:
                return StringUtils.convertToString(value);
        }
    }
}
