package org.econtact.data.filter.visitor;

import org.econtact.data.filter.impl.IsNullFilter;

public class IsNullVisitor extends AbstractVisitor<IsNullFilter> {

    protected IsNullVisitor(VisitorContext visitorContext) {
        super(visitorContext);
    }

    @Override
    public void processFilter(IsNullFilter filter) {
        predicate = visitorContext.getCriteriaBuilder().isNull(
                getPath(filter.getFieldName()));
    }
}