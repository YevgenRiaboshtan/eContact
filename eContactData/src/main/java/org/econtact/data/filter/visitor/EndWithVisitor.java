package org.econtact.data.filter.visitor;

import org.econtact.data.filter.impl.EndWithFilter;

public class EndWithVisitor extends AbstractStringVisitor<EndWithFilter> {

    protected EndWithVisitor(VisitorContext visitorContext) {
        super(visitorContext);
    }

    @Override
    public void processFilter(EndWithFilter filter) {
        predicate = visitorContext.getCriteriaBuilder().like(
                getPath(filter.getFieldName()),
                visitorContext.createFindParam(LIKE_ANY_CHARS + filter.getValue()),
                LIKE_ESCAPE_CHAR);
    }
}