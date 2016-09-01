package org.econtact.data.filter.visitor;

import org.econtact.data.filter.impl.GreaterFilter;

public class GreaterVisitor extends AbstractVisitor<GreaterFilter> {

    protected GreaterVisitor(VisitorContext visitorContext) {
        super(visitorContext);
    }

    @Override
    public void processFilter(GreaterFilter filter) {
        predicate = visitorContext.getCriteriaBuilder().greaterThan(
                getPath(filter.getFieldName()),
                visitorContext.createFindParam(filter.getValue()));
    }
}
