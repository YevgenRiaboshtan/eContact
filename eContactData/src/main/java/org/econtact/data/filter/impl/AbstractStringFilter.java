package org.econtact.data.filter.impl;

import org.apache.commons.lang.StringUtils;
import org.junit.Assert;

public abstract class AbstractStringFilter extends AbstractFilter<String> {
    private static final long serialVersionUID = -8751658609717049628L;

    protected AbstractStringFilter(String fieldName, String value) {
        super(fieldName, value);
        Assert.assertTrue(StringUtils.isNotBlank(value));
    }
}
