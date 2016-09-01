package org.econtact.data.filter.impl;

import org.econtact.data.filter.FilterType;

public class NotEqualFilter<E> extends AbstractFilter<E> {
    private static final long serialVersionUID = -8180223548003515477L;

    public NotEqualFilter(String fieldName, E value) {
        super(fieldName, value);
    }

    @Override
    public FilterType getType() {
        return FilterType.NOT_EQUAL;
    }
}
