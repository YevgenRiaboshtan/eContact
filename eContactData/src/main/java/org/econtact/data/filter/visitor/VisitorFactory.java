package org.econtact.data.filter.visitor;

import org.econtact.data.filter.FilterType;

public class VisitorFactory {

    public static AbstractVisitor getVisitor(final FilterType filterType, VisitorContext ctx) {
        switch (filterType) {
            case AND:
                return new AndVisitor(ctx);
            case OR:
                return new OrVisitor(ctx);

            default:
                throw new UnsupportedOperationException("Filter with type - " + filterType + "not supported.");
        }
    }
}
