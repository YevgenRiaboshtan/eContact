package org.econtact.data.filter.impl;

import org.econtact.data.filter.FilterType;

public class LessOrEqualFilter<E> extends AbstractFilter<E> {
    private static final long serialVersionUID = -4302994244624541301L;

    protected LessOrEqualFilter(String fieldName, E value) {
        super(fieldName, value);
    }

    @Override
    public FilterType getType() {
        return FilterType.LESS_OR_EQUAL;
    }
}
