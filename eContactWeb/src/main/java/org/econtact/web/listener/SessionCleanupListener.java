package org.econtact.web.listener;

import org.econtact.data.service.AuthenticationService;
import org.econtact.web.util.WebHelper;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.util.SessionCleanup;

import javax.servlet.http.HttpSession;

public class SessionCleanupListener implements SessionCleanup {
    @Override
    public void cleanup(Session session) throws Exception {
        HttpSession httpSession = (HttpSession) session.getNativeSession();
        WebHelper.getEjbBean(AuthenticationService.class).disconnectUser(httpSession.getId());
    }
}
