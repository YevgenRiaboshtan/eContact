package org.econtact.data.filter.impl;

import org.econtact.data.filter.FilterType;

public class IsNullFilter extends AbstractFilter {
    private static final long serialVersionUID = -3300217757663457007L;

    protected IsNullFilter(String fieldName) {
        super(fieldName, null);
    }

    @Override
    public FilterType getType() {
        return FilterType.IS_NULL;
    }
}
