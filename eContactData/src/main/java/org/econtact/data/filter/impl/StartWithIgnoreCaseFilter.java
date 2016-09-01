package org.econtact.data.filter.impl;

import org.econtact.data.filter.FilterType;

public class StartWithIgnoreCaseFilter extends AbstractStringFilter {
    private static final long serialVersionUID = 6045744970518665690L;

    protected StartWithIgnoreCaseFilter(String fieldName, String value) {
        super(fieldName, value);
    }

    @Override
    public FilterType getType() {
        return FilterType.START_WITH_IGNORE_CASE;
    }
}
