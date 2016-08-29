package org.econtact.web.composer.layout;

import org.econtact.data.config.action.ActionConfig;
import org.econtact.data.config.navigation.RootConfig;
import org.econtact.data.service.ConfigService;
import org.econtact.web.model.TreeModelImpl;
import org.econtact.web.util.PageManager;
import org.econtact.web.util.WebHelper;
import org.zkoss.xel.ExpressionFactory;
import org.zkoss.xel.Expressions;
import org.zkoss.xel.util.SimpleXelContext;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.xel.impl.ExecutionResolver;
import org.zkoss.zul.Tree;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.TreeitemRenderer;
import org.zkoss.zul.Treerow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class NavigationTreeComposer extends SelectorComposer<Tree> {

    @Wire
    private Tree navigationTree;

    @Override
    public void doAfterCompose(Tree comp) throws Exception {
        super.doAfterCompose(comp);
        final RootItem root = new RootItem();
        final TreeModelImpl model = new TreeModelImpl(root);
        model.setMultiple(false);
        navigationTree.setModel(model);
        navigationTree.setItemRenderer(new ItemRenderImpl());
        if (!root.getChildren().isEmpty()) {
            model.setSelection(Arrays.asList(root.getChildren().iterator().next()));
            Events.postEvent(navigationTree, new Event(Events.ON_CLICK, navigationTree, null));
        }
    }

    @Listen("onClick=#navigationTree")
    public void onNavigate(Event event) {
        final TreeModelImpl model = (TreeModelImpl) getSelf().<TreeItem>getModel();
        if (!model.getSelection().isEmpty()) {
            NavigationItemImpl selected = (NavigationItemImpl) model.getSelection().iterator().next();
            final String outcome = selected.getOutcome() == null ? "/app/to-do.zul" : selected.getOutcome();
            PageManager.setWorkplace(outcome, selected.getParameters());
        }
    }

    private class RootItem implements TreeItem {

        private String root;
        private List<TreeItem> children;

        public RootItem() {
            root = "ROOT";
        }

        @Override
        public String getImageUrl() {
            return null;
        }

        @Override
        public String getLabel() {
            return root;
        }

        @Override
        public String getTooltip() {
            return root;
        }

        @Override
        public boolean isLeaf() {
            return false;
        }

        @Override
        public List<TreeItem> getChildren() {
            if (children == null) {
                children = initChildren();
            }
            return children;
        }

        private List<TreeItem> initChildren() {
            final List<TreeItem> result = new ArrayList<>();
            final List<RootConfig> rootConfigs = WebHelper.getEjbBean(ConfigService.class).getNavigationConfig();
            for (RootConfig rootConfig : rootConfigs) {
                final boolean rootRenderer = WebHelper.resolveExecutionExpression(rootConfig.getRendered(), Boolean.class);
                if (rootRenderer) {
                    for (ActionConfig actionConfig : rootConfig.getActions()) {
                        final boolean actionRendered = WebHelper.resolveExecutionExpression(actionConfig.getRendered(), Boolean.class);
                        if (actionRendered) {
                            addItem(actionConfig, result);
                        }
                    }
                }
            }
            return result;
        }

        private void addItem(final ActionConfig actionConfig, final List<TreeItem> items) {
            final NavigationItemImpl item = new NavigationItemImpl(
                    WebHelper.resolveExecutionExpression(actionConfig.getLabel(), String.class))
                    .setImageUrl(actionConfig.getImage())
                    .setOutcome(actionConfig.getOutcome())
                    .setTooltip(actionConfig.getTooltip())
                    .setParameters(actionConfig.getParameters());
            items.add(item);
            for (ActionConfig childConfig : actionConfig.getActions()) {
                addItem(childConfig, item.getChildren());
            }
        }
    }

    private class NavigationItemImpl implements TreeItem {
        private String imageUrl;
        private String label;
        private String tooltip;
        private String outcome;
        private List<TreeItem> childrenItems;
        private Map<String, String> parameters;

        public NavigationItemImpl(String label) {
            this.label = label;
        }

        @Override
        public String getImageUrl() {
            return imageUrl;
        }

        public NavigationItemImpl setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        @Override
        public String getLabel() {
            return label;
        }

        @Override
        public String getTooltip() {
            return tooltip == null ? label : tooltip;
        }

        public NavigationItemImpl setTooltip(String tooltip) {
            this.tooltip = tooltip;
            return this;
        }

        public String getOutcome() {
            return outcome;
        }

        public NavigationItemImpl setOutcome(String outcome) {
            this.outcome = outcome;
            return this;
        }

        public Map<String, String> getParameters() {
            return parameters;
        }

        public NavigationItemImpl setParameters(Map<String, String> parameters) {
            this.parameters = parameters;
            return this;
        }

        @Override
        public boolean isLeaf() {
            return childrenItems == null || childrenItems.isEmpty();
        }

        @Override
        public List<TreeItem> getChildren() {
            if (childrenItems == null) {
                childrenItems = new ArrayList<>();
            }
            return childrenItems;
        }
    }

    private class ItemRenderImpl implements TreeitemRenderer<NavigationItemImpl> {

        @Override
        public void render(Treeitem item, NavigationItemImpl data, int index) throws Exception {
            item.setValue(data);
            final Treerow row = new Treerow();
            row.setImage(data.getImageUrl());
            row.setLabel(data.getLabel());
            row.setTooltiptext(data.getTooltip());
            item.appendChild(row);
        }
    }
}
