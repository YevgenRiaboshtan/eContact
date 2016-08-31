package org.econtact.data.filter.visitor;

import org.econtact.data.filter.impl.OrFilter;

import javax.persistence.criteria.Predicate;

public class OrVisitor extends AbstractCollectVisitor<OrFilter> {

    OrVisitor(VisitorContext visitorContext) {
        super(visitorContext);
    }

    @Override
    public Predicate getPredicate() {
        if (predicates.isEmpty()) {
            return null;
        } else if (predicates.size() == 1) {
            return predicates.get(0);
        } else {
            return visitorContext.getCriteriaBuilder().or(predicates.toArray(new Predicate[predicates.size()]));
        }
    }
}
