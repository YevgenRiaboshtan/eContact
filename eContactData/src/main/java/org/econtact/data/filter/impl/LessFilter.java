package org.econtact.data.filter.impl;

import org.econtact.data.filter.FilterType;

public class LessFilter<E> extends AbstractFilter<E> {
    private static final long serialVersionUID = 8454375212875823536L;

    public LessFilter(String fieldName, E value) {
        super(fieldName, value);
    }

    @Override
    public FilterType getType() {
        return FilterType.LESS;
    }
}
