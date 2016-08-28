package org.econtact.data.model.entity.account;

import org.econtact.data.DataModelHelper;
import org.econtact.data.model.entity.AbstractEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

import static org.econtact.data.model.entity.account.PersonAccountEntity.BY_LOGIN_QUERY;
import static org.econtact.data.model.entity.account.PersonAccountEntity.LOGIN_A;

@Entity
@Table(name = "PERSON_ACCOUNT", schema = DataModelHelper.ECONTACT_SCHEMA)
@SQLDelete(sql = "UPDATE PERSON_ACCOUNT SET SIGN = ID+PERSON_ACCOUNT WHERE ID_PERSON_ACCOUNT = ? AND VERSION = ?")
@Cache(usage = CacheConcurrencyStrategy.NONE)
@Audited
@AuditTable(value = "PERSON_ACCOUNT_AUDIT", schema = DataModelHelper.ECONTACT_SCHEMA)
@NamedQueries(
        @NamedQuery(name = BY_LOGIN_QUERY, query = "SELECT account FROM PersonAccountEntity account WHERE account.login=:login"))
public class PersonAccountEntity extends AbstractEntity<BigDecimal> {

    public static final String BY_LOGIN_QUERY = "PersonAccountEntity.byLogin";
    public static final String LOGIN_A = "login";
    private static final String SEQ_NAME = "personAccountSeq";

    @SequenceGenerator(name = SEQ_NAME, sequenceName = "S$PERSON_ACCOUNT_SEQ", schema = DataModelHelper.ECONTACT_SCHEMA)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_NAME)
    @Column(name = "ID_PERSON_ACCOUNT", unique = true, nullable = false, precision = 38)
    private BigDecimal id;

    @OneToOne
    @JoinColumn(name = "ID_PERSON_FK", nullable = false, updatable = false)
    private PersonEntity person;

    @Column(name = "LOGIN", nullable = false, length = 100, unique = true)
    private String login;

    @Column(name = "PASSWORD", nullable = false, length = 60)
    private String password;

    @Column(name = "ACCOUNT_NON_EXPIRED", nullable = false, precision = 1)
    private Boolean accountNonExpired;

    @Column(name = "ACCOUNT_NON_LOCKED", nullable = false, precision = 1)
    private Boolean accountNonLocked;

    @Column(name = "ENABLED", nullable = false, precision = 1)
    private Boolean enabled;

    @Column(name = "UPDATE_AUTHOR", nullable = false, length = 100)
    private String updateAuthor;

    @Column(name = "UPDATE_DATE", nullable = false)
    private Date updateDate;

    @Column(name = "SIGN", nullable = false, precision = 38)
    private BigDecimal sign;

    @Override
    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public PersonEntity getPerson() {
        return person;
    }

    public void setPerson(PersonEntity person) {
        this.person = person;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(Boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public Boolean getAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(Boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getUpdateAuthor() {
        return updateAuthor;
    }

    public void setUpdateAuthor(String updateAuthor) {
        this.updateAuthor = updateAuthor;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public BigDecimal getSign() {
        return sign;
    }

    public void setSign(BigDecimal sign) {
        this.sign = sign;
    }

    public void prePersist() {
        sign = DataModelHelper.ACTUAL_SIGN;
        updateDate = new Date();
    }
}
