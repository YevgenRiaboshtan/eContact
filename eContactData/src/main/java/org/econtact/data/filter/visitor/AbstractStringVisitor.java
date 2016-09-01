package org.econtact.data.filter.visitor;

import org.econtact.data.filter.impl.AbstractStringFilter;

public abstract class AbstractStringVisitor<E extends AbstractStringFilter> extends AbstractVisitor<E> {

    protected static final char LIKE_ESCAPE_CHAR = '\\';
    protected static final String LIKE_ANY_CHARS = "%";

    protected AbstractStringVisitor(VisitorContext visitorContext) {
        super(visitorContext);
    }
}
