package org.econtact.web.util;

import org.junit.Assert;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Path;

import java.util.Map;

public final class PageManager {
    private static final char PATH_DELIMITER = '/';
    private static final String WORKPLACE_ID = "workplace";

    private PageManager() {
    }

    public static Component setWorkplace(final String uri, final Map<String, ?> params) {
        Assert.assertNotNull(uri);
        final Component parent = Path.getComponent(PATH_DELIMITER + WORKPLACE_ID);
        parent.getChildren().clear();
        final Component result = Executions.createComponents(uri, parent, params);
        return result;
    }
}
