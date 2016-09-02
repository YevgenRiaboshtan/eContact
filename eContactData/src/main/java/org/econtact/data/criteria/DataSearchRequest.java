package org.econtact.data.criteria;

import org.econtact.data.filter.Filter;
import org.econtact.data.model.AbstractView;

import java.io.Serializable;
import java.util.List;

public class DataSearchRequest<E extends AbstractView> implements Serializable {
    private static final long serialVersionUID = -971503309495866422L;

    private final Queries<E> query;
    private Filter filter;
    private List<SortingInfo> sortingInfos;

    public DataSearchRequest(Queries<E> queries) {
        this.query = queries;
    }

    public Filter getFilter() {
        return filter;
    }

    public DataSearchRequest setFilter(Filter filter) {
        this.filter = filter;
        return this;
    }

    public Queries<E> getQuery() {
        return query;
    }

    public List<SortingInfo> getSortingInfos() {
        return sortingInfos;
    }

    public DataSearchRequest setSortingInfos(List<SortingInfo> sortingInfos) {
        this.sortingInfos = sortingInfos;
        return this;
    }
}
