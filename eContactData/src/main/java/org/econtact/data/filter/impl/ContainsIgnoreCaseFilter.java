package org.econtact.data.filter.impl;

import org.econtact.data.filter.FilterType;

public class ContainsIgnoreCaseFilter extends AbstractStringFilter {
    private static final long serialVersionUID = -2800228207787494632L;

    protected ContainsIgnoreCaseFilter(String fieldName, String value) {
        super(fieldName, value);
    }

    @Override
    public FilterType getType() {
        return FilterType.CONTAINS_IGNORE_CASE;
    }
}
