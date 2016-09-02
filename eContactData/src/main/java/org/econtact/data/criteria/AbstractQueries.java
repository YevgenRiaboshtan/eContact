package org.econtact.data.criteria;

import org.econtact.data.filter.Filter;
import org.econtact.data.filter.visitor.AbstractVisitor;
import org.econtact.data.filter.visitor.VisitorContext;
import org.econtact.data.filter.visitor.VisitorFactory;
import org.econtact.data.model.AbstractView;
import org.hibernate.query.criteria.internal.OrderImpl;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class AbstractQueries<PK extends Serializable, T extends AbstractView<PK>> implements Queries<T> {
    private static final long serialVersionUID = -3937580497901561671L;

    @Override
    public TypedQuery<Long> getRowCountQuery(EntityManager em, Filter filter) {
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        final Root root = cq.from(getEntityClass());
        final VisitorContext visitorContext = new VisitorContext(cb, root);
        cq.select(cb.count(root));
        createWhereClause(visitorContext, cq, filter);
        final TypedQuery<Long> result = em.createQuery(cq);
        visitorContext.getParams().forEach(result::setParameter);
        return result;
    }

    @Override
    public TypedQuery<T> getSelectQuery(EntityManager em, Filter filter, Collection<SortingInfo> sortingInfos) {
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery cq = cb.createQuery(getEntityClass());
        final Root root = cq.from(getEntityClass());
        final VisitorContext visitorContext = new VisitorContext(cb, root);
        updateSelectQuery(visitorContext);
        createWhereClause(visitorContext, cq, filter);
        addSortInfo(visitorContext, cq, sortingInfos);
        specifySelect(cq, visitorContext);
        final TypedQuery<T> result = em.createQuery(cq);
        visitorContext.getParams().forEach(result::setParameter);
        return result;
    }

    /**
     * Returns entity class to create root of selection.
     *
     * @return class of entity
     */
    protected abstract <T extends AbstractView<PK>> Class<T> getEntityClass();

    /**
     * Updates select query to add hints, fetches etc.
     */
    protected void updateSelectQuery(final VisitorContext visitorCtx) {
    }

    /**
     * Specify custom select expression.
     * Root element by default.
     * @param cq
     * @param visitorCtx
     */
    protected void specifySelect(CriteriaQuery cq, VisitorContext visitorCtx) {
        cq.select(visitorCtx.getRoot());
    }

    protected Predicate getAdditionalPredicate(VisitorContext visitorCtx, CriteriaQuery cq) {
        return null;
    }

    private void createWhereClause(final VisitorContext visitorCtx, final CriteriaQuery cq, Filter filter) {
        Predicate predicate = null;
        if (filter != null) {
            final AbstractVisitor filterVisitor = VisitorFactory.getVisitor(filter.getType(), visitorCtx);
            filter.createFindCriteria(filterVisitor);
            predicate = filterVisitor.getPredicate();
        }
        final Predicate addPredicate = getAdditionalPredicate(visitorCtx, cq);
        if (addPredicate != null) {
            if (predicate != null) {
                predicate = visitorCtx.getCriteriaBuilder().and(addPredicate, predicate);
            } else {
                predicate = addPredicate;
            }
        }
        if (predicate != null) {
            cq.where(predicate);
        }
    }

    private void addSortInfo(final VisitorContext visitorCtx, final CriteriaQuery cq,
                             final Collection<SortingInfo> sortingInfos) {
        final List<Order> orders = new ArrayList<>();
        for (SortingInfo sortingInfo : sortingInfos) {
            From parent = visitorCtx.getRoot();
            final String[] names = sortingInfo.getColumnName().split("\\.");
            From result;
            for (int index = 0; index < names.length - 1; index++) {
                final String name = names[index];
                result = visitorCtx.getFromMap().get(name);
                if (result == null) {
                    result = parent.join(name, JoinType.LEFT);
                    visitorCtx.getFromMap().put(name, result);
                }
                parent = result;
            }
            orders.add(new OrderImpl(parent.get(names[names.length - 1]), sortingInfo.isAscending()));
        }
        if (!orders.isEmpty()) {
            cq.orderBy(orders);
        }
    }
}