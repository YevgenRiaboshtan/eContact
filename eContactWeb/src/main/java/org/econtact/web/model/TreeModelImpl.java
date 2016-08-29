package org.econtact.web.model;

import org.econtact.web.composer.layout.TreeItem;
import org.zkoss.zul.AbstractTreeModel;

public class TreeModelImpl extends AbstractTreeModel<TreeItem> {

    public TreeModelImpl(TreeItem root) {
        super(root);
    }

    @Override
    public boolean isLeaf(TreeItem node) {
        return node.isLeaf();
    }

    @Override
    public TreeItem getChild(TreeItem parent, int i) {
        return parent.getChildren().get(i);
    }

    @Override
    public int getChildCount(TreeItem treeItem) {
        return treeItem.getChildren().size();
    }
}
