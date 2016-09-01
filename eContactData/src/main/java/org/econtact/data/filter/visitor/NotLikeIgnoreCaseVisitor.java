package org.econtact.data.filter.visitor;

import org.econtact.data.filter.impl.NotLikeIgnoreCaseFilter;

public class NotLikeIgnoreCaseVisitor extends AbstractStringVisitor<NotLikeIgnoreCaseFilter> {
    protected NotLikeIgnoreCaseVisitor(VisitorContext visitorContext) {
        super(visitorContext);
    }

    @Override
    public void processFilter(NotLikeIgnoreCaseFilter filter) {
        predicate = visitorContext.getCriteriaBuilder().notLike(
                visitorContext.getCriteriaBuilder().lower(getPath(filter.getFieldName())),
                visitorContext.createFindParam(filter.getValue().toLowerCase()),
                LIKE_ESCAPE_CHAR);
    }
}
