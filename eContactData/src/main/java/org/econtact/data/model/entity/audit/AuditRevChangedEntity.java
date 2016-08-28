package org.econtact.data.model.entity.audit;

import org.econtact.data.DataModelHelper;
import org.econtact.data.model.AbstractView;

import javax.persistence.*;

@Entity
@Table(name = "AUDIT_REV_CHANGED", schema = DataModelHelper.ECONTACT_SCHEMA)
public class AuditRevChangedEntity implements AbstractView<Long> {

    public static final String AUDIT_REV_A = "auditRev";
    private static final String SEQ_NAME = "auditRevChangedSeq";
    @SequenceGenerator(name = SEQ_NAME, sequenceName = "S$AUDIT_REV_CHANGED", schema = DataModelHelper.ECONTACT_SCHEMA, allocationSize = 1)

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_NAME)
    @Column(name = "ID", nullable = false, precision = 38, scale = 0)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ID_AUDIT_REV_FK", nullable = false)
    private AuditRevEntity auditRev;

    @Column(name = "ENTITY_ID", nullable = true, length = 100)
    private String entityId;

    @Column(name = "ENTITY_NAME", nullable = false, length = 300)
    private String entityName;

    public static AuditRevChangedEntity create(final String entityName, Object entityId) {
        final AuditRevChangedEntity result = new AuditRevChangedEntity();
        result.setEntityName(entityName);
        result.setEntityId(entityId.toString());
        return result;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AuditRevEntity getAuditRev() {
        return auditRev;
    }

    public void setAuditRev(AuditRevEntity auditRev) {
        this.auditRev = auditRev;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }
}
