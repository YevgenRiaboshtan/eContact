package org.econtact.data.filter;

import java.io.Serializable;

public interface Filter<E> extends Serializable {

    FilterType getType();

    E getValue();

    String getFieldName();

    boolean isEmpty();

    default <T extends Filter> void createFindCriteria(FilterVisitor<T> visitor) {
        visitor.processFilter((T) this);
    }
}
