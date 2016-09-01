package org.econtact.data.filter.visitor;

import org.econtact.data.filter.impl.LikeIgnoreCaseFilter;

public class LikeIgnoreCaseVisitor extends AbstractStringVisitor<LikeIgnoreCaseFilter> {

    protected LikeIgnoreCaseVisitor(VisitorContext visitorContext) {
        super(visitorContext);
    }

    @Override
    public void processFilter(LikeIgnoreCaseFilter filter) {
        predicate = visitorContext.getCriteriaBuilder().notLike(
                visitorContext.getCriteriaBuilder().lower(getPath(filter.getFieldName())),
                visitorContext.createFindParam(filter.getValue().toLowerCase()),
                LIKE_ESCAPE_CHAR);
    }
}
