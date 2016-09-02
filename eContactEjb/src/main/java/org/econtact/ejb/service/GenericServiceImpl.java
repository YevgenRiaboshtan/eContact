package org.econtact.ejb.service;

import org.econtact.data.service.GenericService;
import org.econtact.ejb.EjbHelper;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@Local(GenericService.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class GenericServiceImpl extends AbstractServiceBean {

    @PersistenceContext(unitName = EjbHelper.UNIT_NAME)
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
