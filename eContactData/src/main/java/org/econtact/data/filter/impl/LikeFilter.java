package org.econtact.data.filter.impl;

import org.econtact.data.filter.FilterType;

public class LikeFilter extends AbstractStringFilter {

    public LikeFilter(String fieldName, String value) {
        super(fieldName, value);
    }

    @Override
    public FilterType getType() {
        return FilterType.LIKE;
    }
}
