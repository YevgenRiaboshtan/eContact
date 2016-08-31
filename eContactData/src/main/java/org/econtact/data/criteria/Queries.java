package org.econtact.data.criteria;

import org.econtact.data.filter.Filter;
import org.econtact.data.model.AbstractView;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.Collection;

public interface Queries<T extends AbstractView> extends Serializable {

    TypedQuery<Long> getRowCountQuery(EntityManager em, Filter filter);

    TypedQuery<T> getSelectQuery(EntityManager em, Filter filter, Collection<SortingInfo> sortingInfos);
}
