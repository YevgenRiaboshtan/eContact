package org.econtact.data.filter.impl;

import org.econtact.data.filter.FilterType;

public class LikeIgnoreCaseFilter extends AbstractStringFilter {
    private static final long serialVersionUID = 6630210590869107603L;

    protected LikeIgnoreCaseFilter(String fieldName, String value) {
        super(fieldName, value);
    }

    @Override
    public FilterType getType() {
        return FilterType.LIKE_IGNORE_CASE;
    }
}
