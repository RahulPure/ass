package org.sample.assignment.utils;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.sample.assignment.constants.Delimiter;
import org.sample.assignment.constants.Operators;
import org.sample.assignment.constants.ValidationConstants;
import org.sample.assignment.representations.SearchCriteria;

import java.util.function.Consumer;

/**
 * @author RahulPure
 */
@AllArgsConstructor
public class SearchCriteriaBuilder implements Consumer<SearchCriteria> {

    @Getter
    private Predicate predicate;
    private CriteriaBuilder builder;
    private Root<?> root;

    @Override
    public void accept(SearchCriteria criteria) {
        if (Operators.LIKE.title().equalsIgnoreCase(criteria.getOperator())) {
            predicate =
                    builder.or(
                            predicate,
                            builder.like(
                                    builder.lower(root.get(criteria.getAttribute())),
                                    "%" + criteria.getValue().replaceAll(Delimiter.UNDERSCORE.title(), ValidationConstants.SEARCH_ALLOWED_UNDERSCORE).toLowerCase() + "%"));
        } else if (Operators.STARTS_WITH.title().equalsIgnoreCase(criteria.getOperator())) {
            predicate =
                    builder.or(
                            predicate,
                            builder.like(
                                    builder.lower(root.get(criteria.getAttribute())),
                                    criteria.getValue().replaceAll(Delimiter.UNDERSCORE.title(),ValidationConstants.SEARCH_ALLOWED_UNDERSCORE).toLowerCase() + "%"));
        } else if (Operators.ENDS_WITH.title().equalsIgnoreCase(criteria.getOperator())) {
            predicate =
                    builder.or(
                            predicate,
                            builder.like(
                                    builder.lower(root.get(criteria.getAttribute())),
                                    "%" + criteria.getValue().replaceAll(Delimiter.UNDERSCORE.title(),ValidationConstants.SEARCH_ALLOWED_UNDERSCORE).toLowerCase()));
        } else if (Operators.EQUALS.title().equalsIgnoreCase(criteria.getOperator())) {
            predicate =
                    builder.or(
                            predicate,
                            builder.like(
                                    builder.lower(root.get(criteria.getAttribute())),
                                    criteria.getValue().replaceAll(Delimiter.UNDERSCORE.title(),ValidationConstants.SEARCH_ALLOWED_UNDERSCORE).toLowerCase()));
        } else if (Operators.NOT_EQUALS.title().equalsIgnoreCase(criteria.getOperator())) {
            String value = null;
            if (criteria.getValue().equalsIgnoreCase("EMPTY_STRING"))
                value = criteria.getValue().replace("EMPTY_STRING", "");
            else
                value = criteria.getValue();

            predicate =
                    builder.or(
                            predicate,
                            builder.notEqual(
                                    builder.lower(root.get(criteria.getAttribute())),
                                    value.toLowerCase()));
        } else {
            predicate =
                    builder.or(
                            predicate,
                            builder.equal(root.get(criteria.getAttribute()), criteria.getValue().toLowerCase()));
        }
    }
}
