package org.econtact.data.filter.impl;

import org.junit.Assert;

public abstract class AbstractStringFilter extends AbstractFilter<String> {
    private static final long serialVersionUID = -8751658609717049628L;

    /**
     *
     * @param fieldName - filter field name
     * @param value - Not null, not empty
     */
    protected AbstractStringFilter(String fieldName, String value) {
        super(fieldName, value);
        Assert.assertNotNull(value);
        Assert.assertFalse(value.isEmpty());
    }
}
