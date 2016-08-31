package org.econtact.data.filter.impl;

import org.econtact.data.filter.Filter;

public abstract class AbstractFilter<E> implements Filter<E> {

    private static final long serialVersionUID = 3428451623529570871L;
    String fieldName;
    E value;

    protected AbstractFilter(String fieldName, E value) {
        this.fieldName = fieldName;
        this.value = value;
    }

    @Override
    public E getValue() {
        return value;
    }

    @Override
    public String getFieldName() {
        return fieldName;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public String toString() {
        return new StringBuilder(getClass().getSimpleName())
                .append(": type=").append(getType())
                .append(", fieldName=").append(fieldName)
                .append(", value=").append(value)
                .toString();
    }
}
