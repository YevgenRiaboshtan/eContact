package org.econtact.data.filter.impl;

import org.econtact.data.filter.FilterType;

public class NotNullFilter extends AbstractFilter {
    private static final long serialVersionUID = 1594899794608360177L;

    protected NotNullFilter(String fieldName) {
        super(fieldName, null);
    }

    @Override
    public FilterType getType() {
        return FilterType.NOT_NULL;
    }
}