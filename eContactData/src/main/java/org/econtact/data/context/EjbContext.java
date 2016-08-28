package org.econtact.data.context;

import org.econtact.data.model.entity.account.PersonEntity;

import java.util.TimeZone;

public final class EjbContext {
    private static ThreadLocal<EjbContext> instance = new ThreadLocal<EjbContext>() {

        @Override
        protected EjbContext initialValue() {
            return new EjbContext();
        }
    };
    private UserContext userContext;
    private EnversContext enversContext;

    private EjbContext() {
    }

    public static EjbContext get() {
        return instance.get();
    }

    public void setUserContext(UserContext userContext) {
        this.userContext = userContext;
    }

    public PersonEntity getPerson() {
        return userContext.getPerson();
    }

    public TimeZone getUserTimeZone() {
        return userContext.getUserTimeZone();
    }

    public EnversContext getEnversContext() {
        return enversContext;
    }

    public void setEnversContext(EnversContext enversContext) {
        this.enversContext = enversContext;
    }
}
