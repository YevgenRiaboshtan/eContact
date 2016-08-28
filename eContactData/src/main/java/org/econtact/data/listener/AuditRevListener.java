package org.econtact.data.listener;

import org.econtact.data.context.EjbContext;
import org.econtact.data.model.entity.audit.AuditRevChangedEntity;
import org.econtact.data.model.entity.audit.AuditRevEntity;
import org.hibernate.envers.EntityTrackingRevisionListener;
import org.hibernate.envers.RevisionType;

import java.io.Serializable;
import java.util.Date;

public class AuditRevListener implements EntityTrackingRevisionListener {
    @Override
    public void entityChanged(Class aClass, String entityName, Serializable entityId, RevisionType revisionType, Object revisionObj) {
        final AuditRevEntity revEntity = (AuditRevEntity) revisionObj;
        final AuditRevChangedEntity changedEntity = AuditRevChangedEntity.create(entityName, entityId);
        revEntity.addChangesEntity(changedEntity);
    }

    @Override
    public void newRevision(Object revisionObj) {
        final AuditRevEntity revEntity = (AuditRevEntity) revisionObj;
        revEntity.setPerson(EjbContext.get().getPerson());
        revEntity.setDateEv(new Date());
        revEntity.setNameEv(EjbContext.get().getEnversContext().getNameEv());
        revEntity.setNote(EjbContext.get().getEnversContext().getNote());
    }
}
