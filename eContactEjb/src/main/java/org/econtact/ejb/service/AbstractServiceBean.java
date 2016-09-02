package org.econtact.ejb.service;

import org.econtact.data.context.EjbContext;
import org.econtact.data.context.EnversContext;
import org.econtact.data.context.UserContext;
import org.econtact.data.criteria.DataSearchRequest;
import org.econtact.data.model.AbstractView;
import org.econtact.data.model.entity.AbstractEntity;
import org.econtact.data.service.GenericService;
import org.hibernate.annotations.QueryHints;
import org.junit.Assert;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@TransactionAttribute(TransactionAttributeType.REQUIRED)
public abstract class AbstractServiceBean implements GenericService {

    @Override
    public <PK extends Serializable, T extends AbstractView<PK>> T find(Class<T> findClass, PK id) {
        final T result = getEntityManager().find(findClass, id);
        return result;
    }

    @Override
    public <PK extends Serializable, T extends AbstractView<PK>> List<T> findAll(DataSearchRequest<T> dataSearchRequest) {
        return findAll(dataSearchRequest, null, null);
    }

    @Override
    public <PK extends Serializable, T extends AbstractView<PK>> List<T> findAll(DataSearchRequest<T> dataSearchRequest, Integer from, Integer count) {
        final TypedQuery<T> query = dataSearchRequest.getQuery().getSelectQuery(
                getEntityManager(),
                dataSearchRequest.getFilter(),
                dataSearchRequest.getSortingInfos());
        if (from != null) {
            query.setFirstResult(from);
        }
        if (count != null) {
            query.setMaxResults(count);
        }
        final List<T> result = query.getResultList();
        return result;
    }

    @Override
    public <PK extends Serializable, T extends AbstractView<PK>> T findSingle(DataSearchRequest<T> dataSearchRequest) {
        final TypedQuery<T> query = dataSearchRequest.getQuery().getSelectQuery(
                getEntityManager(),
                dataSearchRequest.getFilter(),
                dataSearchRequest.getSortingInfos());
        final List<T> result = query.getResultList();
        if (result.size() > 1) {
            throw new NonUniqueResultException();
        }
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public <PK extends Serializable, T extends AbstractView<PK>> Long getRowCount(DataSearchRequest<T> dataSearchRequest) {
        final TypedQuery<Long> query = dataSearchRequest.getQuery().getRowCountQuery(
                getEntityManager(),
                dataSearchRequest.getFilter());
        query.setHint(QueryHints.READ_ONLY, Boolean.TRUE);
        final Long result = query.getSingleResult();
        return result;
    }

    //TODO add handle optimistick lock and violation exceptions
    @Override
    public <PK extends Serializable, T extends AbstractEntity<PK>> T saveOrUpdate(T entity, final UserContext userContext) {
        Assert.assertNotNull(entity);
        prepareEjbContext(entity, userContext, entity.getId() == null ? "create" : "update");
        final T result = getEntityManager().merge(entity);
        return result;
    }

    @Override
    public <PK extends Serializable, T extends AbstractEntity<PK>> void saveOrUpdate(Collection<T> entities, final UserContext userContext) {
        entities.forEach(entity -> {
            saveOrUpdate(entity, userContext);
        });
    }

    @Override
    public <PK extends Serializable, T extends AbstractEntity<PK>> void remove(T entity, final UserContext userContext) {
        Assert.assertNotNull(entity);
        prepareEjbContext(entity, userContext, "remove");
        getEntityManager().refresh(entity);
        getEntityManager().remove(entity);
    }

    @Override
    public <PK extends Serializable, T extends AbstractEntity<PK>> void remove(Collection<T> entities, final UserContext userContext) {
        entities.forEach(entity -> {
            remove(entity, userContext);
        });
    }

    private <PK extends Serializable, T extends AbstractEntity<PK>> void prepareEjbContext(T entity, final UserContext userContext, final String note) {
        final EjbContext ejbContext = EjbContext.get();
        ejbContext.setUserContext(userContext);
        ejbContext.setEnversContext(EnversContext.create(entity.getClass().getSimpleName(), note));
    }

    protected abstract EntityManager getEntityManager();
}
