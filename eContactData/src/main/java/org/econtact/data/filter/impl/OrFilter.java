package org.econtact.data.filter.impl;

import org.econtact.data.filter.Filter;
import org.econtact.data.filter.FilterType;

public class OrFilter extends AbstractFilters {

    private static final long serialVersionUID = 3957073481839152540L;

    public OrFilter or(Filter... filters) {
        addFilters(filters);
        return this;
    }

    @Override
    public FilterType getType() {
        return FilterType.OR;
    }
}
