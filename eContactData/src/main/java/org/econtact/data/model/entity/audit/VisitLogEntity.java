package org.econtact.data.model.entity.audit;

import org.econtact.data.DataModelHelper;
import org.econtact.data.model.AbstractView;
import org.econtact.data.model.entity.account.PersonEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "VISIT_LOG", schema = DataModelHelper.ECONTACT_SCHEMA)
@Cacheable(value = false)
@NamedQueries(
        @NamedQuery(name = VisitLogEntity.DISCONNECT_PERSON_QUERY,
                query = "UPDATE VisitLogEntity vl SET vl.endVisit=:endVisit WHERE vl.sessionId=:sessionId"))
public class VisitLogEntity implements AbstractView<BigDecimal> {

    public static final String DISCONNECT_PERSON_QUERY = "VisitLogEntity.disconnectPerson";
    public static final String SESSION_ID_A = "sessionId";
    public static final String END_VISIT_A = "endVisit";

    private static final String SEQ_NAME = "visitLogSeq";
    @SequenceGenerator(name = SEQ_NAME, sequenceName = "S$VISIT_LOG_SEQ", schema = DataModelHelper.ECONTACT_SCHEMA)
    @Id
    @Column(name = "ID_VISIT_LOG", precision = 38, nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_NAME)
    private BigDecimal id;

    @Column(name = "SESSION_ID", length = 100, nullable = false)
    private String sessionId;

    @Column(name = "START_VISIT", nullable = false)
    private Date startVisit;

    @Column(name = "END_VISIT")
    private Date endVisit;

    @Column(name = "IP_ADDRESS", nullable = false)
    private String ipAddress;

    @Column(name = "DEVICE_NAME", nullable = false)
    private String deviceName;

    @ManyToOne
    @JoinColumn(name = "ID_PERSON_FK", nullable = false)
    private PersonEntity person;

    public static VisitLogEntity create(final PersonEntity person, final String sessionId,
                                        final String ipAddress, final String deviceName) {
        final VisitLogEntity result = new VisitLogEntity();
        result.setSessionId(sessionId);
        result.setStartVisit(new Date());
        result.setPerson(person);
        result.setIpAddress(ipAddress);
        result.setDeviceName(deviceName);
        return result;
    }

    @Override
    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Date getStartVisit() {
        return startVisit;
    }

    public void setStartVisit(Date startVisit) {
        this.startVisit = startVisit;
    }

    public Date getEndVisit() {
        return endVisit;
    }

    public void setEndVisit(Date endVisit) {
        this.endVisit = endVisit;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public PersonEntity getPerson() {
        return person;
    }

    public void setPerson(PersonEntity person) {
        this.person = person;
    }
}
