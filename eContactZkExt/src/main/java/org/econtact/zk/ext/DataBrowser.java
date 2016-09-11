package org.econtact.zk.ext;

import org.econtact.data.criteria.SortingInfo;
import org.econtact.data.filter.Filter;
import org.econtact.data.filter.impl.AndFilter;
import org.econtact.zk.ext.event.ApplyFiltersEvent;
import org.econtact.zk.ext.event.SortInfoEvent;
import org.econtact.zk.ext.filter.AbstractFilterBox;
import org.econtact.zk.ext.util.ComponentUtil;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.event.SerializableEventListener;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zul.Image;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Paging;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataBrowser extends Listbox implements AfterCompose {
    private static final long serialVersionUID = -5884126660242380517L;

   // private Paging paging;
    private boolean showTools = true;
    private boolean showPrintItem = true;
    private Map<String, AbstractFilterBox> userFilters = new HashMap<>();
    private Image totalFilterImage = new Image();

    public DataBrowser() {
        super.setMold("paging");
        super.setAutopaging(true);
        setEmptyMessage(Labels.getLabel("data.browser.emptyMessage"));
    }

    @Override
    public void afterCompose() {
        totalFilterImage.setTooltiptext(Labels.getLabel("browser.apply.filter.tooltip"));
        addEventListener(ApplyFiltersEvent.NAME, new ApplyFiltersListener());
        addEventListener(SortInfoEvent.NAME, new SortInfoListener());
    }

    @Override
    public void setModel(ListModel<?> model) {
        if (model != null) {
            if (model instanceof ListModelExt) {
                ListModelExt listModelExt = (ListModelExt) model;
                listModelExt.setUserFilter(getUserFilter());
                listModelExt.setSortingInfos(getSortingInfos());
                //paging.setTotalSize(listModelExt.getTotalSize());
            } else {
                throw new UiException(model.getClass() + " not supported");
            }
        }
        super.setModel(model);
    }

    public void addHeaderFilter(final String fieldName, AbstractFilterBox filterBox) {
        userFilters.put(fieldName, filterBox);
    }

    private Filter getUserFilter() {
        final AndFilter result = new AndFilter();
        userFilters.forEach((s, abstractFilterBox) -> {
            abstractFilterBox.updateState();
            result.and(abstractFilterBox.getFilter());
        });
        totalFilterImage.setSrc(ComponentUtil.getImageUrl(result.isEmpty() ? "/funnel.png" : "/funnel_act.png"));
        return result.isEmpty() ? null : result;
    }

    private List<SortingInfo> getSortingInfos() {
        final List<SortingInfo> result = new ArrayList<>();
        getListhead().getChildren().forEach(component -> {
            if (component instanceof BrowserHeader) {
                final SortingInfo sortingInfo = ((BrowserHeader) component).getSortingInfo();
                if (sortingInfo != null) {
                    result.add(sortingInfo);
                }
            }
        });
        return result;
    }

    private class ApplyFiltersListener implements SerializableEventListener<ApplyFiltersEvent> {

        @Override
        public void onEvent(ApplyFiltersEvent event) throws Exception {
            final Filter filter = getUserFilter();
            //paging.setActivePage(0);
            ListModelExt model = (ListModelExt) getModel();
            model.setUserFilter(filter);
            model.syncModel(0, DataBrowser.this.getPageSize(), false, true);
        }
    }

    private class SortInfoListener implements SerializableEventListener<SortInfoEvent> {

        @Override
        public void onEvent(SortInfoEvent event) throws Exception {
            ListModelExt model = (ListModelExt) getModel();
            model.setSortingInfos(getSortingInfos());
            model.syncModel(0, DataBrowser.this.getPageSize(), false, true);
        }
    }
}
