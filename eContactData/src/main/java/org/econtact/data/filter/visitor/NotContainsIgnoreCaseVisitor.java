package org.econtact.data.filter.visitor;

import org.econtact.data.filter.impl.NotContainsIgnoreCaseFilter;

public class NotContainsIgnoreCaseVisitor extends AbstractStringVisitor<NotContainsIgnoreCaseFilter> {
    protected NotContainsIgnoreCaseVisitor(VisitorContext visitorContext) {
        super(visitorContext);
    }

    @Override
    public void processFilter(NotContainsIgnoreCaseFilter filter) {
        predicate = visitorContext.getCriteriaBuilder().notLike(
                visitorContext.getCriteriaBuilder().lower(getPath(filter.getFieldName())),
                visitorContext.createFindParam(LIKE_ANY_CHARS + filter.getValue().toLowerCase() + LIKE_ANY_CHARS),
                LIKE_ESCAPE_CHAR);
    }
}
