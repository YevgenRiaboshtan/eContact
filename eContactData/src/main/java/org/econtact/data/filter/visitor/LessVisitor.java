package org.econtact.data.filter.visitor;

import org.econtact.data.filter.impl.LessFilter;

public class LessVisitor extends AbstractVisitor<LessFilter> {

    protected LessVisitor(VisitorContext visitorContext) {
        super(visitorContext);
    }

    @Override
    public void processFilter(LessFilter filter) {
        predicate = visitorContext.getCriteriaBuilder().lessThan(
                getPath(filter.getFieldName()),
                visitorContext.createFindParam(filter.getValue()));
    }
}
