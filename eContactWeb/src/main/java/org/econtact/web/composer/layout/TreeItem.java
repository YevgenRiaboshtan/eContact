package org.econtact.web.composer.layout;

import java.io.Serializable;
import java.util.List;

public interface TreeItem extends Serializable {

    String getImageUrl();

    String getLabel();

    String getTooltip();

    boolean isLeaf();

    List<TreeItem> getChildren();
}
