package org.econtact.zk.ext.filter;

import org.econtact.data.filter.Filter;
import org.econtact.zk.ext.util.ComponentUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Image;
import org.zkoss.zul.impl.InputElement;

import java.io.Serializable;

public abstract class AbstractFilterBox<T, E extends InputElement> implements Serializable {
    private static final long serialVersionUID = -8878693142604800217L;
    protected final String fieldName;
    private Image image = new Image(ComponentUtil.getImageUrl("/funnel.png"));

    public AbstractFilterBox(String fieldName) {
        this.fieldName = fieldName;
    }

    public void updateState() {
        image.setSrc(ComponentUtil.getImageUrl(isEmpty() ? "/funnel.png" : "/funnel_act.png"));
    }

    public Component getImageUi() {
        return image;
    }

    public abstract E getInputUi();

    public abstract void setValue(Object value);

    public abstract Filter<T> getFilter();

    protected abstract boolean isEmpty();
}
