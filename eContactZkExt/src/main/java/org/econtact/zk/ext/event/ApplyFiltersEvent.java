package org.econtact.zk.ext.event;

import org.econtact.zk.ext.DataBrowser;
import org.zkoss.zk.ui.event.Event;

public class ApplyFiltersEvent extends Event {
    public static final String NAME = "onApplyFilters";
    private static final long serialVersionUID = 8427557060085692334L;

    public ApplyFiltersEvent(DataBrowser target) {
        super(NAME, target);
    }
}
