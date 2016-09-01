package org.econtact.data.filter.visitor;

import org.econtact.data.filter.impl.LessOrEqualFilter;

public class LessOrEqualVisitor extends AbstractVisitor<LessOrEqualFilter> {

    protected LessOrEqualVisitor(VisitorContext visitorContext) {
        super(visitorContext);
    }

    @Override
    public void processFilter(LessOrEqualFilter filter) {
        predicate = visitorContext.getCriteriaBuilder().lessThanOrEqualTo(
                getPath(filter.getFieldName()),
                visitorContext.createFindParam(filter.getValue()));
    }
}