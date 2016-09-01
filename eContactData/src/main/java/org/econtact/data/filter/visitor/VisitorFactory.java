package org.econtact.data.filter.visitor;

import org.econtact.data.filter.FilterType;

public class VisitorFactory {

    public static AbstractVisitor getVisitor(final FilterType filterType, VisitorContext ctx) {
        switch (filterType) {
            case AND:
                return new AndVisitor(ctx);
            case OR:
                return new OrVisitor(ctx);

            case EQUAL:
                return new EqualVisitor(ctx);
            case NOT_EQUAL:
                return new NotEqualVisitor(ctx);
            case GREATER:
                return new GreaterVisitor(ctx);
            case GREATER_OR_EQUAL:
                return new GreaterOrEqualVisitor(ctx);
            case LESS:
                return new LessVisitor(ctx);
            case LESS_OR_EQUAL:
                return new LessOrEqualVisitor(ctx);
            case BETWEEN:
                return new BetweenVisitor(ctx);
            case IS_NULL:
                return new IsNullVisitor(ctx);
            case NOT_NULL:
                return new NotNullVisitor(ctx);



            default:
                throw new UnsupportedOperationException("Filter with type - " + filterType + "not supported.");
        }
    }
}
