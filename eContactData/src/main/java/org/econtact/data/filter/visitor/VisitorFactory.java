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

            case LIKE:
                return new LikeVisitor(ctx);
            case LIKE_IGNORE_CASE:
                return new LikeIgnoreCaseVisitor(ctx);
            case NOT_LIKE:
                return new NotLikeVisitor(ctx);
            case NOT_LIKE_IGNORE_CASE:
                return new NotLikeIgnoreCaseVisitor(ctx);

            case START_WITH:
                return new StartWithVisitor(ctx);
            case START_WITH_IGNORE_CASE:
                return new StartWithIgnoreCaseVisitor(ctx);
            case END_WITH:
                return new EndWithVisitor(ctx);
            case END_WITH_IGNORE_CASE:
                return new EndWithIgnoreCaseVisitor(ctx);

            case CONTAINS:
                return new ContainsVisitor(ctx);
            case CONTAINS_IGNORE_CASE:
                return new ContainsIgnoreCaseVisitor(ctx);
            case NOT_CONTAINS:
                return new NotContainsVisitor(ctx);
            case NOT_CONTAINS_IGNORE_CASE:
                return new NotContainsIgnoreCaseVisitor(ctx);

            default:
                throw new UnsupportedOperationException("Filter with type - " + filterType + "not supported.");
        }
    }
}
