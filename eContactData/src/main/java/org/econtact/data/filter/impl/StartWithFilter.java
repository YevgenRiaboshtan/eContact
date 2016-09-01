package org.econtact.data.filter.impl;

import org.econtact.data.filter.FilterType;

public class StartWithFilter extends AbstractStringFilter {
    private static final long serialVersionUID = -450649942901950507L;

    protected StartWithFilter(String fieldName, String value) {
        super(fieldName, value);
    }

    @Override
    public FilterType getType() {
        return FilterType.START_WITH;
    }
}
