package org.econtact.data.filter.visitor;

import org.econtact.data.filter.impl.ContainsIgnoreCaseFilter;

public class ContainsIgnoreCaseVisitor extends AbstractStringVisitor<ContainsIgnoreCaseFilter> {

    protected ContainsIgnoreCaseVisitor(VisitorContext visitorContext) {
        super(visitorContext);
    }

    @Override
    public void processFilter(ContainsIgnoreCaseFilter filter) {
        predicate = visitorContext.getCriteriaBuilder().like(
                visitorContext.getCriteriaBuilder().lower(getPath(filter.getFieldName())),
                visitorContext.createFindParam(LIKE_ANY_CHARS + filter.getValue().toLowerCase() + LIKE_ANY_CHARS),
                LIKE_ESCAPE_CHAR);
    }
}
