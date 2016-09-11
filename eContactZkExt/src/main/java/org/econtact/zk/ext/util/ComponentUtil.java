package org.econtact.zk.ext.util;

import org.zkoss.zul.theme.Themes;

public final class ComponentUtil {

    private ComponentUtil() {
    }

    public static String getImageUrl(final String imageName) {
        return "~./img/" + Themes.getCurrentTheme() + imageName;
    }
}
