package org.econtact.data.filter.visitor;

import org.econtact.data.filter.impl.GreaterOrEqualFilter;

public class GreaterOrEqualVisitor extends AbstractVisitor<GreaterOrEqualFilter> {

    protected GreaterOrEqualVisitor(VisitorContext visitorContext) {
        super(visitorContext);
    }

    @Override
    public void processFilter(GreaterOrEqualFilter filter) {
        predicate = visitorContext.getCriteriaBuilder().greaterThanOrEqualTo(
                getPath(filter.getFieldName()),
                visitorContext.createFindParam(filter.getValue()));
    }
}
