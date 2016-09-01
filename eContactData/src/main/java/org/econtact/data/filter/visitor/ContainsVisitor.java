package org.econtact.data.filter.visitor;

import org.econtact.data.filter.impl.ContainsFilter;

public class ContainsVisitor extends AbstractStringVisitor<ContainsFilter> {

    protected ContainsVisitor(VisitorContext visitorContext) {
        super(visitorContext);
    }

    @Override
    public void processFilter(ContainsFilter filter) {
        predicate = visitorContext.getCriteriaBuilder().like(
                getPath(filter.getFieldName()),
                visitorContext.createFindParam(LIKE_ANY_CHARS + filter.getValue() + LIKE_ANY_CHARS),
                LIKE_ESCAPE_CHAR);
    }
}
