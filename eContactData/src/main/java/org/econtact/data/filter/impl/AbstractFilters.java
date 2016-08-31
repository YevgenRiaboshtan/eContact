package org.econtact.data.filter.impl;

import org.econtact.data.filter.Filter;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractFilters implements Filter {
    private static final long serialVersionUID = -8179488366766967781L;

    private List<Filter> filters = new ArrayList<>();

    protected AbstractFilters() {
    }

    protected void addFilters(Filter... filters) {
        for (Filter filter : filters) {
            if (filter != null) {
                this.filters.add(filter);
            }
        }
    }

    public List<Filter> getFilters() {
        return filters;
    }

    @Override
    public Object getValue() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getFieldName() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isEmpty() {
        return filters.isEmpty();
    }

    @Override
    public String toString() {
        return new StringBuilder(getClass().getSimpleName())
                .append(": type=").append(getType())
                .append(", filters=").append(filters)
                .toString();
    }
}
