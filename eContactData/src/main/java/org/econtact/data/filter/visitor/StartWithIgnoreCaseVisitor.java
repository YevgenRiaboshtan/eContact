package org.econtact.data.filter.visitor;

import org.econtact.data.filter.impl.StartWithIgnoreCaseFilter;

public class StartWithIgnoreCaseVisitor extends AbstractStringVisitor<StartWithIgnoreCaseFilter> {
    protected StartWithIgnoreCaseVisitor(VisitorContext visitorContext) {
        super(visitorContext);
    }

    @Override
    public void processFilter(StartWithIgnoreCaseFilter filter) {
        predicate = visitorContext.getCriteriaBuilder().like(
                visitorContext.getCriteriaBuilder().lower(getPath(filter.getFieldName())),
                visitorContext.createFindParam(filter.getValue().toLowerCase() + LIKE_ANY_CHARS),
                LIKE_ESCAPE_CHAR);
    }
}