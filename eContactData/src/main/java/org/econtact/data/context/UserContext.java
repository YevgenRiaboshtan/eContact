package org.econtact.data.context;

import org.econtact.data.model.entity.account.PersonEntity;

import java.io.Serializable;
import java.util.TimeZone;

public class UserContext implements Serializable {

    private static final long serialVersionUID = 1900023554603860042L;
    private PersonEntity person;
    private TimeZone userTimeZone;

    private UserContext() {
    }

    public static UserContext create(PersonEntity person, TimeZone userTimeZone) {
        final UserContext result = new UserContext();
        result.person = person;
        result.userTimeZone = userTimeZone;
        return result;
    }

    public PersonEntity getPerson() {
        return person;
    }

    public TimeZone getUserTimeZone() {
        return userTimeZone;
    }
}
