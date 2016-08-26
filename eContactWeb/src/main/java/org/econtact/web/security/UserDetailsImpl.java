package org.econtact.web.security;

import org.econtact.data.model.entity.account.PersonEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class UserDetailsImpl implements UserDetails {
    /**
     * The username presented to the <code>DaoAuthenticationProvider</code>.
     */
    private String username;
    /**
     * The password that should be presented to the <code>DaoAuthenticationProvider</code>.
     */
    private String password;
    /**
     * Set to <code>true</code> if the user is enabled.
     */
    private boolean enabled;
    /**
     * Set to <code>true</code> if the account has not expired.
     */
    private boolean accountNonExpired;
    /**
     * Set to <code>true</code> if the account is not locked.
     */
    private boolean accountNonLocked;
    /**
     * The authorities that should be granted to the caller if they presented the correct username and password
     * and  the user is enabled.
     */
    private Collection<GrantedAuthority> authorities;

    private PersonEntity person;

    public UserDetailsImpl(String username, String password,
                           boolean enabled, boolean accountNonExpired, boolean accountNonLocked) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        final List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(AuthenticationHelper.ROLE_ECONTACT));
        this.authorities = Collections.unmodifiableCollection(authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public PersonEntity getPerson() {
        return person;
    }

    public void setPerson(PersonEntity person) {
        this.person = person;
    }
}