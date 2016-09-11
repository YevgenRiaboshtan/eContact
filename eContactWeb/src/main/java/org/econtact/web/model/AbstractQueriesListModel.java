package org.econtact.web.model;

import org.econtact.data.criteria.SortingInfo;
import org.econtact.data.filter.Filter;
import org.econtact.data.model.AbstractView;
import org.econtact.zk.ext.ListModelExt;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.AbstractListModel;
import org.zkoss.zul.event.PagingEvent;
import org.zkoss.zul.event.PagingListener;

import java.util.ArrayList;
import java.util.List;

public class AbstractQueriesListModel<E extends AbstractView> extends AbstractListModel<E> implements ListModelExt<E> {
    private static final long serialVersionUID = 5288630375457962605L;

    private final List<E> data;

    public AbstractQueriesListModel(List<E> data) {
        this.data = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            this.data.addAll(data);
        }

        addPagingEventListener(new PagingListener() {
            @Override
            public void onEvent(Event event) throws Exception {
                PagingEvent paginEvent = (PagingEvent) event;
            }

            @Override
            public Object willClone(Component comp) {
                return null;
            }
        });
    }

    @Override
    public E getElementAt(int index) {
        return data.get(index);
    }

    @Override
    public int getSize() {
        return data.size();
    }

    @Override
    public int getTotalSize() {
        return getSize();
    }

    @Override
    public void syncModel(int page, int pageSize, boolean clearSelection, boolean force) {

    }

    @Override
    public void setImmutableFilter(Filter filter) {

    }

    @Override
    public void setUserFilter(Filter filter) {

    }

    @Override
    public void setSortingInfos(List<SortingInfo> sortingInfos) {

    }

    @Override
    public void setEmptyModel(boolean empty) {

    }
}
