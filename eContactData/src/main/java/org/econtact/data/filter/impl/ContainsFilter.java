package org.econtact.data.filter.impl;

import org.econtact.data.filter.FilterType;

public class ContainsFilter extends AbstractStringFilter {
    private static final long serialVersionUID = -3838840533084146328L;

    protected ContainsFilter(String fieldName, String value) {
        super(fieldName, value);
    }

    @Override
    public FilterType getType() {
        return FilterType.CONTAINS;
    }
}
