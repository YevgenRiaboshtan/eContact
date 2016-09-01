package org.econtact.data.filter.visitor;

import org.econtact.data.filter.impl.EqualFilter;

public class EqualVisitor extends AbstractVisitor<EqualFilter> {

    protected EqualVisitor(VisitorContext visitorContext) {
        super(visitorContext);
    }

    @Override
    public void processFilter(EqualFilter filter) {
        predicate = visitorContext.getCriteriaBuilder().equal(
                getPath(filter.getFieldName()),
                visitorContext.createFindParam(filter.getValue()));
    }
}
