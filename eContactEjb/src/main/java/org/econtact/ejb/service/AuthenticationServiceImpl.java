package org.econtact.ejb.service;

import org.econtact.data.model.entity.account.PersonAccountEntity;
import org.econtact.data.model.entity.account.PersonEntity;
import org.econtact.data.model.entity.audit.VisitLogEntity;
import org.econtact.data.service.AuthenticationService;
import org.econtact.ejb.EjbHelper;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

@Stateless
@Local(AuthenticationService.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class AuthenticationServiceImpl implements AuthenticationService {

    @PersistenceContext(unitName = EjbHelper.UNIT_NAME)
    private EntityManager em;

    @Override
    public List<PersonAccountEntity> findUsersByLogin(String login) {
        final Query query = em.createNamedQuery(PersonAccountEntity.BY_LOGIN_QUERY, PersonAccountEntity.class)
                .setParameter(PersonAccountEntity.LOGIN_A, login);
        final List<PersonAccountEntity> users = query.getResultList();
        return users;
    }

    @Override
    public void connectUser(PersonEntity person, String sessionId, String ipAddres, String deviceName) {
        final VisitLogEntity entity = VisitLogEntity.create(person, sessionId, ipAddres, deviceName);
        em.persist(entity);
    }

    @Override
    public void disconnectUser(String sessionId) {
        em.createNamedQuery(VisitLogEntity.DISCONNECT_PERSON_QUERY)
                .setParameter(VisitLogEntity.SESSION_ID_A, sessionId)
                .setParameter(VisitLogEntity.END_VISIT_A, new Date())
                .executeUpdate();
    }
}
