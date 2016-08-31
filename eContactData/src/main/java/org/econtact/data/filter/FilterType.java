package org.econtact.data.filter;

public enum FilterType {

    AND, OR,
    EQUAL, NOT_EQUAL, GE, LE, BETWEEN, LIKE, IS_NULL,
    CONTAINS, CONTAINS_IGNORE_CASE, NOT_CONTAINS, NOT_CONTAINS_IGNORE_CASE
}
