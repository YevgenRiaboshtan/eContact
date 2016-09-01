package org.econtact.data.filter.impl;

import org.econtact.data.filter.FilterType;

public class BetweenFilter<E> extends AbstractFilter<E> {
    private static final long serialVersionUID = 2473909846533659597L;

    private E endValue;

    protected BetweenFilter(String fieldName, E value, E endValue) {
        super(fieldName, value);
        this.endValue = endValue;
    }

    public E getEndValue() {
        return endValue;
    }

    @Override
    public FilterType getType() {
        return FilterType.BETWEEN;
    }
}