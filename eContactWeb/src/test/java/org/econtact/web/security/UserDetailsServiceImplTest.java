package org.econtact.web.security;

import org.econtact.data.model.entity.account.PersonAccountEntity;
import org.econtact.data.model.entity.account.PersonEntity;
import org.econtact.data.service.AuthenticationService;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserDetailsServiceImplTest {

    private final static String EMPTY_USERNAME = "";
    private final static String EXIST_USERNAME = "admin";
    private final static String EXIST_PASSWORD = "password";
    private final static String NOT_EXIST_USERNAME = "blablabla";
    private final static String DUBLICATE_USERNAME = "user";

    private final static UserDetailsServiceImpl userDetailsService = new UserDetailsServiceImpl();
    private static PersonAccountEntity existPersonAccountStub = buildPersonAccount();

    @BeforeClass
    public static void setUp() {
        final List<PersonAccountEntity> existAccounts = new ArrayList<>();
        existAccounts.add(existPersonAccountStub);

        final List<PersonAccountEntity> notExistAccounts = mock(List.class);
        when(notExistAccounts.size()).thenReturn(0);

        final List<PersonAccountEntity> dublicateAccounts = mock(List.class);
        when(dublicateAccounts.size()).thenReturn(2);

        AuthenticationService authServiceMock = mock(AuthenticationService.class);
        when(authServiceMock.findUsersByLogin(EMPTY_USERNAME)).thenReturn(Collections.emptyList());
        when(authServiceMock.findUsersByLogin(EXIST_USERNAME)).thenReturn(existAccounts);
        when(authServiceMock.findUsersByLogin(NOT_EXIST_USERNAME)).thenReturn(notExistAccounts);
        when(authServiceMock.findUsersByLogin(DUBLICATE_USERNAME)).thenReturn(dublicateAccounts);
        userDetailsService.setAuthService(authServiceMock);
    }

    private static PersonAccountEntity buildPersonAccount() {
        PersonAccountEntity result = new PersonAccountEntity();
        result.setAccountNonExpired(true);
        result.setAccountNonLocked(true);
        result.setEnabled(true);
        result.setLogin(EXIST_USERNAME);
        result.setPassword(EXIST_PASSWORD);
        result.setPerson(new PersonEntity());
        return result;
    }

    @Test(expected = UsernameNotFoundException.class)
    public void testEmptyUsername() {
        userDetailsService.loadUserByUsername(EMPTY_USERNAME);
    }

    @Test(expected = UsernameNotFoundException.class)
    public void testNotExistUsername() {
        userDetailsService.loadUserByUsername(NOT_EXIST_USERNAME);
    }

    @Test(expected = UsernameNotFoundException.class)
    public void testDublicateUsername() {
        userDetailsService.loadUserByUsername(DUBLICATE_USERNAME);
    }

    @Test
    public void testExistUsername() {
        UserDetailsImpl result = (UserDetailsImpl) userDetailsService.loadUserByUsername(EXIST_USERNAME);

        Assert.assertNotNull(result);
        Assert.assertEquals(EXIST_USERNAME, result.getUsername());
        Assert.assertEquals(EXIST_PASSWORD, result.getPassword());
        Assert.assertEquals(true, result.isEnabled());
        Assert.assertEquals(true, result.isAccountNonExpired());
        Assert.assertEquals(true, result.isAccountNonLocked());
        Assert.assertEquals(existPersonAccountStub.getPerson(), result.getPerson());
    }

    @Test(expected = AssertionError.class)
    public void testSetAuthService() {
        userDetailsService.setAuthService(null);
    }
}
