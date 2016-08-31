package org.econtact.web.composer.audit;

import org.econtact.data.model.entity.audit.VisitLogEntity;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Grid;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Panel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VisitLogComposer extends SelectorComposer<Panel> {
    private static final long serialVersionUID = -1784581791463910115L;

    @Wire
    private Grid grid;

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
        ListModelList<VisitLogEntity> model = new ListModelList<>(logs);
        grid.setModel(model);
    }
}
