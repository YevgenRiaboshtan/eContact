package org.econtact.data.filter.impl;

import org.econtact.data.filter.FilterType;

public class EndWithIgnoreCaseFilter extends AbstractStringFilter {
    private static final long serialVersionUID = -5338993572934626701L;

    protected EndWithIgnoreCaseFilter(String fieldName, String value) {
        super(fieldName, value);
    }

    @Override
    public FilterType getType() {
        return FilterType.END_WITH_IGNORE_CASE;
    }
}
