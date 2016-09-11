package org.econtact.zk.ext.event;

import org.zkoss.zk.ui.event.Event;

public class SortInfoEvent extends Event {
    public static final String NAME = "onSortInfo";
    private static final long serialVersionUID = -8933613444280620523L;

    public SortInfoEvent() {
        super(NAME);
    }
}
