package org.econtact.data.filter.visitor;

import org.econtact.data.filter.impl.BetweenFilter;

public class BetweenVisitor extends AbstractVisitor<BetweenFilter> {

    protected BetweenVisitor(VisitorContext visitorContext) {
        super(visitorContext);
    }

    @Override
    public void processFilter(BetweenFilter filter) {
        predicate = visitorContext.getCriteriaBuilder().between(
                getPath(filter.getFieldName()),
                visitorContext.createFindParam(filter.getValue()),
                visitorContext.createFindParam(filter.getEndValue()));
    }
}