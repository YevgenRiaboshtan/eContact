package org.econtact.data.filter.impl;

import org.econtact.data.filter.FilterType;

public class NotContainsFilter extends AbstractStringFilter {
    private static final long serialVersionUID = -2652014597270000630L;

    protected NotContainsFilter(String fieldName, String value) {
        super(fieldName, value);
    }

    @Override
    public FilterType getType() {
        return FilterType.NOT_CONTAINS;
    }
}
