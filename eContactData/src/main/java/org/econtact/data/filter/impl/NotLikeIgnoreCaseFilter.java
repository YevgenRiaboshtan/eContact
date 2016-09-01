package org.econtact.data.filter.impl;

import org.econtact.data.filter.FilterType;

public class NotLikeIgnoreCaseFilter extends AbstractStringFilter {
    private static final long serialVersionUID = -7752194987150823950L;

    protected NotLikeIgnoreCaseFilter(String fieldName, String value) {
        super(fieldName, value);
    }

    @Override
    public FilterType getType() {
        return FilterType.NOT_LIKE_IGNORE_CASE;
    }
}
