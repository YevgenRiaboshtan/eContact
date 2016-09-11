package org.econtact.zk.ext.filter;

public enum FilterType {
    NONE,
    STRING;

    public AbstractFilterBox getFilterBox(final String fieldName) {
        switch (this) {
            case STRING:
                return new StringFilterBox(fieldName);
            case NONE:
            default:
                return null;
        }
    }
}
