package org.econtact.data.filter.impl;

import org.econtact.data.filter.FilterType;

public class EndWithFilter extends AbstractStringFilter {
    private static final long serialVersionUID = 1505419994556549353L;

    protected EndWithFilter(String fieldName, String value) {
        super(fieldName, value);
    }

    @Override
    public FilterType getType() {
        return FilterType.END_WITH;
    }
}
