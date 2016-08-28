package org.econtact.web.security;

import org.econtact.data.model.entity.account.PersonAccountEntity;
import org.econtact.data.service.AuthenticationService;
import org.junit.Assert;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public class UserDetailsServiceImpl implements UserDetailsService {

    private AuthenticationService authService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final List<PersonAccountEntity> personAccounts = authService.findUsersByLogin(username);
        if (personAccounts.size() == 1) {
            return buildUserDetails(personAccounts.get(0));
        }
        throw generateException(personAccounts);
    }

    private UserDetailsImpl buildUserDetails(PersonAccountEntity personAccount) {
        final UserDetailsImpl result = new UserDetailsImpl(
                personAccount.getLogin(),
                personAccount.getPassword(),
                personAccount.getEnabled(),
                personAccount.getAccountNonExpired(),
                personAccount.getAccountNonLocked());
        result.setPerson(personAccount.getPerson());
        return result;
    }

    private UsernameNotFoundException generateException(List accounts) {
        final String message;
        if (accounts.isEmpty()) {
            message = "User not found";
        } else {
            message = "Ambiguous username";
        }
        return new UsernameNotFoundException(message);
    }

    public void setAuthService(AuthenticationService authService) {
        Assert.assertNotNull(authService);
        this.authService = authService;
    }
}
