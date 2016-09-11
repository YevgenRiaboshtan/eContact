package org.econtact.zk.ext.util;

import org.econtact.data.criteria.SortingInfo;

public enum SortDirect {
    natural, ascending, descending;

    public SortDirect getNextDirect() {
        final int nextOrdinal = ordinal() + 1;
        final SortDirect next = SortDirect.values()[nextOrdinal == 3 ? 0 : nextOrdinal];
        return next;
    }

    public SortingInfo getSortInfo(final String fieldName) {
        if (natural == this) {
            return null;
        } else  {
            return SortingInfo.create(fieldName, this == ascending);
        }
    }
}
