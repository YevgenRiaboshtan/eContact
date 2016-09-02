package org.econtact.data.criteria;

import org.econtact.data.model.AbstractView;

import java.io.Serializable;

public class GenericQueries<PK extends Serializable, T extends AbstractView<PK>> extends AbstractQueries<PK, T> {
    private static final long serialVersionUID = -9208439094052046332L;
    private final Class<T> viewClass;

    public GenericQueries(Class<T> viewClass) {
        this.viewClass = viewClass;
    }

    @Override
    protected Class<T> getEntityClass() {
        return viewClass;
    }
}
