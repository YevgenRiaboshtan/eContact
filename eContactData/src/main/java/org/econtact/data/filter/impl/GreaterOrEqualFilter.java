package org.econtact.data.filter.impl;

import org.econtact.data.filter.FilterType;

public class GreaterOrEqualFilter<E> extends AbstractFilter<E> {

    private static final long serialVersionUID = -7747436442221607138L;

    protected GreaterOrEqualFilter(String fieldName, E value) {
        super(fieldName, value);
    }

    @Override
    public FilterType getType() {
        return FilterType.GREATER_OR_EQUAL;
    }
}
