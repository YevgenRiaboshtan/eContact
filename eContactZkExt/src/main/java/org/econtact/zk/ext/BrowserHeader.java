package org.econtact.zk.ext;

import org.econtact.data.criteria.SortingInfo;
import org.econtact.zk.ext.event.ApplyFiltersEvent;
import org.econtact.zk.ext.event.SortInfoEvent;
import org.econtact.zk.ext.filter.AbstractFilterBox;
import org.econtact.zk.ext.filter.FilterType;
import org.econtact.zk.ext.util.SortDirect;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zul.Hlayout;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Vlayout;

public class BrowserHeader extends Listheader implements AfterCompose {
    private static final long serialVersionUID = -815520731478274464L;

    private String label;
    private String filterType;
    private String fieldName;
    private SortingInfo sortingInfo;

    @Override
    public void afterCompose() {
        appendChild(constructHeaderElements());
        sortingInfo = SortDirect.valueOf(getSortDirection()).getSortInfo(fieldName);
        addEventListener(Events.ON_SORT, event -> {
            SortDirect next = SortDirect.valueOf(getSortDirection()).getNextDirect();
            setSortDirection(next.name());
            sortingInfo = next.getSortInfo(fieldName);
            event.stopPropagation();
            Events.postEvent(getListbox(), new SortInfoEvent());
        });
    }

    private Component constructHeaderElements() {
        final Vlayout result = new Vlayout();
        final Hlayout topRow = new Hlayout();
        result.appendChild(topRow);

        if (hasFilter()) {
            AbstractFilterBox filterBox = FilterType.valueOf(filterType.toUpperCase()).getFilterBox(fieldName);
            if (filterBox != null) {
                final Hlayout bottomRow = new Hlayout();
                topRow.appendChild(filterBox.getImageUi());
                bottomRow.appendChild(filterBox.getInputUi());
                filterBox.getInputUi().addEventListener(Events.ON_OK, event -> {
                    Events.postEvent(new ApplyFiltersEvent((DataBrowser) getListbox()));
                });
                ((DataBrowser) getListbox()).addHeaderFilter(fieldName, filterBox);
                result.appendChild(bottomRow);
            }
        }
        topRow.appendChild(new Label(label));
        return result;
    }

    /**
     * Returns current info about sorting of this column.
     *
     * @return instance of {@link SortingInfo} class with info about sorting.
     */
    public SortingInfo getSortingInfo() {
        return sortingInfo;
    }

    public void setFilterType(String filterType) {
        this.filterType = filterType;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * Checks defined a filter.
     *
     * @return TRUE if filter defined, otherwise - FALSE.
     */
    public boolean hasFilter() {
        return filterType != null;
    }

}
