package org.econtact.data.filter.visitor;

import org.econtact.data.filter.impl.NotContainsFilter;

public class NotContainsVisitor extends AbstractStringVisitor<NotContainsFilter> {

    protected NotContainsVisitor(VisitorContext visitorContext) {
        super(visitorContext);
    }

    @Override
    public void processFilter(NotContainsFilter filter) {
        predicate = visitorContext.getCriteriaBuilder().notLike(
                getPath(filter.getFieldName()),
                visitorContext.createFindParam(LIKE_ANY_CHARS + filter.getValue() + LIKE_ANY_CHARS),
                LIKE_ESCAPE_CHAR);
    }
}
