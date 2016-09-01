package org.econtact.data.filter.visitor;

import org.econtact.data.filter.impl.LikeFilter;

public class LikeVisitor extends AbstractStringVisitor<LikeFilter> {

    protected LikeVisitor(VisitorContext visitorContext) {
        super(visitorContext);
    }

    @Override
    public void processFilter(LikeFilter filter) {
        predicate = visitorContext.getCriteriaBuilder().like(
                getPath(filter.getFieldName()),
                visitorContext.createFindParam(filter.getValue()),
                LIKE_ESCAPE_CHAR);
    }
}
