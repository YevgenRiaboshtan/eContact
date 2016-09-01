package org.econtact.data.filter.visitor;

import org.econtact.data.filter.impl.NotNullFilter;

public class NotNullVisitor extends AbstractVisitor<NotNullFilter> {
    protected NotNullVisitor(VisitorContext visitorContext) {
        super(visitorContext);
    }

    @Override
    public void processFilter(NotNullFilter filter) {
        predicate = visitorContext.getCriteriaBuilder().isNotNull(
                getPath(filter.getFieldName()));
    }
}
