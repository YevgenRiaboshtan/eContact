package org.econtact.data.filter.visitor;

import org.econtact.data.filter.impl.NotEqualFilter;

public class NotEqualVisitor extends AbstractVisitor<NotEqualFilter> {

    protected NotEqualVisitor(VisitorContext visitorContext) {
        super(visitorContext);
    }

    @Override
    public void processFilter(NotEqualFilter filter) {
        predicate = visitorContext.getCriteriaBuilder().notEqual(
                getPath(filter.getFieldName()),
                visitorContext.createFindParam(filter.getValue()));
    }
}
