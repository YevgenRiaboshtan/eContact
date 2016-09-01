package org.econtact.data.filter.impl;

import org.econtact.data.filter.FilterType;

public class NotContainsIgnoreCaseFilter extends AbstractStringFilter {
    private static final long serialVersionUID = 3896158751040801802L;

    protected NotContainsIgnoreCaseFilter(String fieldName, String value) {
        super(fieldName, value);
    }

    @Override
    public FilterType getType() {
        return FilterType.NOT_CONTAINS_IGNORE_CASE;
    }
}
