package org.econtact.data.model.entity.audit;

import org.econtact.data.DataModelHelper;
import org.econtact.data.listener.AuditRevListener;
import org.econtact.data.model.AbstractView;
import org.econtact.data.model.entity.account.PersonEntity;
import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RevisionEntity(AuditRevListener.class)
@Entity
@Table(name = "AUDIT_REV", schema = DataModelHelper.ECONTACT_SCHEMA)
@Cacheable(value = false)
public class AuditRevEntity implements AbstractView<Long> {
    private static final String SEQ_NAME = "auditRevSeq";
    @SequenceGenerator(name = SEQ_NAME, sequenceName = "S$AUDIT_REV", schema = DataModelHelper.ECONTACT_SCHEMA, allocationSize = 1)

    @Id
    @RevisionNumber
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_NAME)
    @Column(name = "ID", nullable = false)
    private Long id;

    @RevisionTimestamp
    @Column(name = "TIME_STAMP")
    private long timestamp;

    @Column(name = "DATE_EV", nullable = false)
    private Date dateEv;

    @Column(name = "NAME_EV", nullable = true, length = 300)
    private String nameEv;

    @Column(name = "NOTE", nullable = true, length = 1000)
    private String note;

    @ManyToOne
    @JoinColumn(name = "ID_PERSON_FK", nullable = false)
    private PersonEntity person;

    @OneToMany(mappedBy = AuditRevChangedEntity.AUDIT_REV_A, cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    private List<AuditRevChangedEntity> changesEntities = new ArrayList<>();

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public Date getDateEv() {
        return dateEv;
    }

    public void setDateEv(Date dateEv) {
        this.dateEv = dateEv;
    }

    public String getNameEv() {
        return nameEv;
    }

    public void setNameEv(String nameEv) {
        this.nameEv = nameEv;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public PersonEntity getPerson() {
        return person;
    }

    public void setPerson(PersonEntity person) {
        this.person = person;
    }

    public void addChangesEntity(final AuditRevChangedEntity changesEntity) {
        if (!changesEntities.contains(changesEntity)) {
            changesEntity.setAuditRev(this);
            changesEntities.add(changesEntity);
        }
    }
}
