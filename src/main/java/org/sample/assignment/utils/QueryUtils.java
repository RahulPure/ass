package org.sample.assignment.utils;

import org.sample.assignment.constants.QueryParams;
import org.sample.assignment.constants.SortOrder;

/**
 * @author RahulPure
 */
public class QueryUtils {
    private QueryUtils() {}
    /**
     * Calculates actual pages in paginated collection response
     *
     * @param pageSize
     * @param totalCount
     * @return
     */
    public static int calculatesPages(int pageSize, int totalCount) {
        return totalCount < pageSize ? 1 : (int) Math.ceil(totalCount / pageSize);
    }

    public static String extractSortBy(String sort) {
        try {
            String[] sortSeparated = sort.split(QueryParams.SORT_SEPARATOR);
            if (sortSeparated[0].equalsIgnoreCase(QueryParams.CREATED_TIMESTAMP)) {
                return QueryParams.CREATED_TIMESTAMP;
            }
            else if(sortSeparated[0].equalsIgnoreCase(QueryParams.UPDATED_TIMESTAMP)){
                return QueryParams.UPDATED_TIMESTAMP;
            } else if (sortSeparated[0].equalsIgnoreCase(QueryParams.NAME)) {
                return QueryParams.NAME;
            }
        } catch (Exception e) {
            return QueryParams.UPDATED_TIMESTAMP;
        }
        return QueryParams.UPDATED_TIMESTAMP;
    }

    public static String extractSortOrder(String sort) {
        try {
            String[] sortSeparated = sort.split(":");
            if (sortSeparated[1].equalsIgnoreCase(SortOrder.ASC.getTitle())) {
                return SortOrder.ASC.getTitle();
            }
        } catch (Exception var2) {
            return SortOrder.DESC.getTitle();
        }

        return SortOrder.DESC.getTitle();
    }
}
