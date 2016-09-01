package org.econtact.data.filter.visitor;

import org.econtact.data.filter.impl.NotLikeFilter;

public class NotLikeVisitor extends AbstractStringVisitor<NotLikeFilter> {

    protected NotLikeVisitor(VisitorContext visitorContext) {
        super(visitorContext);
    }

    @Override
    public void processFilter(NotLikeFilter filter) {
        predicate = visitorContext.getCriteriaBuilder().notLike(
                getPath(filter.getFieldName()),
                visitorContext.createFindParam(filter.getValue()),
                LIKE_ESCAPE_CHAR);
    }
}
