package org.econtact.data.model.entity;

import org.econtact.data.DataModelHelper;
import org.econtact.data.model.AbstractView;
import org.hibernate.proxy.HibernateProxyHelper;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.persistence.Version;
import java.io.Serializable;

@MappedSuperclass
public abstract class AbstractEntity<PK extends Serializable> implements AbstractView<PK> {

    private static final long serialVersionUID = 1840274098479140724L;
    @Transient
    private Long uid;

    @Version
    @Column(name = "version", precision = 10, nullable = false)
    private Long version;

    private Long getUid() {
        if (uid == null) {
            uid = DataModelHelper.getUid();
        }
        return uid;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null
                || HibernateProxyHelper.getClassWithoutInitializingProxy(this) != HibernateProxyHelper.getClassWithoutInitializingProxy(obj)) {
            return false;
        }

        final AbstractEntity other = (AbstractEntity) obj;
        boolean isEquals;
        if (getId() == null && other.getId() == null) {
            isEquals = getUid().equals(other.getUid());
        } else {
            isEquals = getId() == null ? false : getId().equals(other.getId());
        }
        if (isEquals) {
            isEquals = version == null ? other.version == null : version.equals(other.version);
        }
        return isEquals;
    }

    @Override
    public int hashCode() {
        int result = getUid() != null ? getUid().hashCode() : 0;
        result = 31 * result + (version != null ? version.hashCode() : 0);
        return result;
    }
}
