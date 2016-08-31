package org.econtact.data.filter.visitor;

import org.econtact.data.filter.Filter;
import org.econtact.data.filter.impl.AbstractFilters;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractCollectVisitor<T extends AbstractFilters> extends AbstractVisitor<T> {

    protected final List<Predicate> predicates = new ArrayList<>();

    protected AbstractCollectVisitor(VisitorContext visitorContext) {
        super(visitorContext);
    }

    public void processFilter(T collectFilter) {
        final List<Filter> filters = collectFilter.getFilters();
        for (Filter filter : filters) {
            final AbstractVisitor visitor = VisitorFactory.getVisitor(filter.getType(), visitorContext);
            filter.createFindCriteria(visitor);
            final Predicate result = visitor.getPredicate();
            if (result != null) {
                predicates.add(result);
            }
        }
    }
}
