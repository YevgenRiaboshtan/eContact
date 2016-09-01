package org.econtact.data.filter.visitor;

import org.econtact.data.filter.impl.StartWithFilter;

public class StartWithVisitor extends AbstractStringVisitor<StartWithFilter> {

    protected StartWithVisitor(VisitorContext visitorContext) {
        super(visitorContext);
    }

    @Override
    public void processFilter(StartWithFilter filter) {
        predicate = visitorContext.getCriteriaBuilder().like(
                getPath(filter.getFieldName()),
                visitorContext.createFindParam(filter.getValue() + LIKE_ANY_CHARS),
                LIKE_ESCAPE_CHAR);
    }
}