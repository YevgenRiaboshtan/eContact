package org.econtact.data.filter.impl;

import org.econtact.data.filter.FilterType;

public class EqualFilter<E> extends AbstractFilter<E> {

    private static final long serialVersionUID = 5757930811020882104L;

    public EqualFilter(String fieldName, E value) {
        super(fieldName, value);
    }

    @Override
    public FilterType getType() {
        return FilterType.EQUAL;
    }
}
