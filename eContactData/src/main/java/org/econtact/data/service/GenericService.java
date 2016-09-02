package org.econtact.data.service;

import org.econtact.data.context.UserContext;
import org.econtact.data.criteria.DataSearchRequest;
import org.econtact.data.model.AbstractView;
import org.econtact.data.model.entity.AbstractEntity;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public interface GenericService {

    <PK extends Serializable, T extends AbstractView<PK>> T find(Class<T> findClass, PK id);

    <PK extends Serializable, T extends AbstractView<PK>> List<T> findAll(DataSearchRequest<T> dataSearchRequest);

    <PK extends Serializable, T extends AbstractView<PK>> List<T> findAll(DataSearchRequest<T> dataSearchRequest, Integer from, Integer count);

    <PK extends Serializable, T extends AbstractView<PK>> T findSingle(DataSearchRequest<T> dataSearchRequest);

    <PK extends Serializable, T extends AbstractView<PK>> Long getRowCount(DataSearchRequest<T> dataSearchRequest);

    <PK extends Serializable, T extends AbstractEntity<PK>> T saveOrUpdate(T entity, UserContext userContext);

    <PK extends Serializable, T extends AbstractEntity<PK>> void saveOrUpdate(Collection<T> entities, UserContext userContext);

    <PK extends Serializable, T extends AbstractEntity<PK>> void remove(T entity, UserContext userContext);

    <PK extends Serializable, T extends AbstractEntity<PK>> void remove(Collection<T> entities, UserContext userContext);
}
