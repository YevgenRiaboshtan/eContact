package org.econtact.web.composer.audit;

import org.econtact.data.model.entity.audit.VisitLogEntity;
import org.econtact.web.model.AbstractQueriesListModel;
import org.econtact.zk.ext.DataBrowser;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Panel;
import org.zkoss.zul.theme.Themes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VisitLogComposer extends SelectorComposer<Panel> {
    private static final long serialVersionUID = -1784581791463910115L;

    @Wire
    private DataBrowser browser;

    @Override
    public void doAfterCompose(Panel comp) throws Exception {
        super.doAfterCompose(comp);
        List<VisitLogEntity> logs = new ArrayList<>();
        VisitLogEntity log = new VisitLogEntity();
        log.setIpAddress("12");
        log.setStartVisit(new Date());
        log.setEndVisit(new Date());
        logs.add(log);
        log = new VisitLogEntity();
        log.setEndVisit(new Date());
        log.setIpAddress("sddd");
        logs.add(log);
        AbstractQueriesListModel<VisitLogEntity> model = new AbstractQueriesListModel<VisitLogEntity>(logs);
        browser.setModel(model);
    }
}
