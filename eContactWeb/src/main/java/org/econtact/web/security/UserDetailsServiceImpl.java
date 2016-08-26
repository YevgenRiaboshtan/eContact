package org.econtact.web.security;

import org.econtact.data.model.entity.account.PersonAccountEntity;
import org.econtact.data.service.AuthenticationService;
import org.econtact.web.util.WebHelper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final AuthenticationService service = WebHelper.getEjbBean(AuthenticationService.class);
        final List<PersonAccountEntity> personAccounts = service.findUsersByLogin(username);
        if (personAccounts.size() == 1) {
            final PersonAccountEntity personAccount = personAccounts.get(0);
            final UserDetailsImpl result = new UserDetailsImpl(
                    username,
                    personAccount.getPassword(),
                    personAccount.getEnabled(),
                    personAccount.getAccountNonExpired(),
                    personAccount.getAccountNonLocked());
            result.setPerson(personAccount.getPerson());
            return result;
        }
        if (!personAccounts.isEmpty()) {
            throw new UsernameNotFoundException("Ambiguous user with username " + username);
        }
        return null;
    }
}
