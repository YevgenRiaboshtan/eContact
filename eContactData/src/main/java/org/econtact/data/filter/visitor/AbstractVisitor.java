package org.econtact.data.filter.visitor;

import org.econtact.data.filter.Filter;
import org.econtact.data.filter.FilterVisitor;

import javax.persistence.criteria.From;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;

public abstract class AbstractVisitor<T extends Filter> implements FilterVisitor<T> {

    protected static final char LIKE_ESCAPE_CHAR = '\\';
    protected static final String LIKE_ANY_CHARS = "%";

    protected final VisitorContext visitorContext;
    protected Predicate predicate;

    public Predicate getPredicate() {
        return predicate;
    }

    protected AbstractVisitor(final VisitorContext visitorContext) {
        this.visitorContext = visitorContext;
    }

    protected Path getPath(final String fieldName) {
        From parent = visitorContext.getRoot();
        final String[] names = fieldName.split("\\.");
        From result;
        for (int index = 0; index < names.length - 1; index++) {
            final String name = names[index];
            result = visitorContext.getFromMap().get(name);
            if (result == null) {
                result = parent.join(name, JoinType.LEFT);
                visitorContext.getFromMap().put(name, result);
            }
            parent = result;
        }
        return parent.get(names[names.length - 1]);
    }
}
