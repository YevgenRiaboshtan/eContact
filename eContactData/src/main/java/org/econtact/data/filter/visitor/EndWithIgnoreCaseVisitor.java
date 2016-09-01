package org.econtact.data.filter.visitor;

import org.econtact.data.filter.impl.EndWithIgnoreCaseFilter;

public class EndWithIgnoreCaseVisitor extends AbstractStringVisitor<EndWithIgnoreCaseFilter> {

    protected EndWithIgnoreCaseVisitor(VisitorContext visitorContext) {
        super(visitorContext);
    }

    @Override
    public void processFilter(EndWithIgnoreCaseFilter filter) {
        predicate = visitorContext.getCriteriaBuilder().like(
                visitorContext.getCriteriaBuilder().lower(getPath(filter.getFieldName())),
                visitorContext.createFindParam(LIKE_ANY_CHARS + filter.getValue().toLowerCase()),
                LIKE_ESCAPE_CHAR);
    }
}
