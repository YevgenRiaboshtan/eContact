package org.econtact.data.criteria;

import java.io.Serializable;

public class SortingInfo implements Serializable {
    private static final long serialVersionUID = -3347992632534318173L;

    private final String columnName;
    private final boolean asc;

    SortingInfo(final String columnName, final boolean asc) {
        assert (columnName != null && !columnName.isEmpty());
        this.columnName = columnName;
        this.asc = asc;
    }

    public String getColumnName() {
        return columnName;
    }

    public boolean isAscending() {
        return asc;
    }

    SortingInfo(String columnName) {
        this(columnName, true);
    }

    public static SortingInfo create(final String attrName, boolean asc) {
        return new SortingInfo(attrName, asc);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        SortingInfo that = (SortingInfo) obj;
        return columnName.equals(that.columnName);
    }

    @Override
    public int hashCode() {
        return columnName.hashCode();
    }
}
