package org.econtact.data.filter.impl;

import org.econtact.data.filter.Filter;
import org.econtact.data.filter.FilterType;

public class AndFilter extends AbstractFilters {
    private static final long serialVersionUID = 7793842303648991501L;

    public AndFilter and(Filter... filters) {
        addFilters(filters);
        return this;
    }

    @Override
    public FilterType getType() {
        return FilterType.AND;
    }
}
