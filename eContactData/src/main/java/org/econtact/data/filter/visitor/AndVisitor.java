package org.econtact.data.filter.visitor;

import org.econtact.data.filter.impl.AndFilter;

import javax.persistence.criteria.Predicate;

public class AndVisitor extends AbstractCollectVisitor<AndFilter> {

    AndVisitor(VisitorContext visitorContext) {
        super(visitorContext);
    }

    @Override
    public Predicate getPredicate() {
        if (predicates.isEmpty()) {
            return null;
        } else if (predicates.size() == 1) {
            return predicates.get(0);
        } else {
            return visitorContext.getCriteriaBuilder().and(predicates.toArray(new Predicate[predicates.size()]));
        }
    }
}
