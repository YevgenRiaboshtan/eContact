package org.econtact.data.filter.impl;

import org.econtact.data.filter.FilterType;

public class GreaterFilter<E> extends AbstractFilter<E> {
    private static final long serialVersionUID = -646198894118304281L;

    public GreaterFilter(String fieldName, E value) {
        super(fieldName, value);
    }

    @Override
    public FilterType getType() {
        return FilterType.GREATER;
    }
}
