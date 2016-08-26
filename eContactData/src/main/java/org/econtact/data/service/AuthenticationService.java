package org.econtact.data.service;

import org.econtact.data.model.entity.account.PersonAccountEntity;
import org.econtact.data.model.entity.account.PersonEntity;

import java.util.List;

public interface AuthenticationService {

    List<PersonAccountEntity> findUsersByLogin(String login);

    void connectUser(PersonEntity person, String sessionId, String ipAddres, String deviceName);

    void disconnectUser(String sessionId);
}
