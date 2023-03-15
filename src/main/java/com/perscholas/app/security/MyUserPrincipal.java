

package com.perscholas.app.security;

import com.perscholas.app.model.AuthGroup;
import com.perscholas.app.model.UserRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class MyUserPrincipal implements UserDetails {

    private UserRegistration userRegistration;
    private List<AuthGroup> authGroup;
    @Autowired
    public MyUserPrincipal(UserRegistration userRegistration, List<AuthGroup> authGroup) {
        this.userRegistration = userRegistration;
        this.authGroup = authGroup;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authGroup.stream().map(auth -> new SimpleGrantedAuthority(auth.getRole())).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return userRegistration.getPassword();
    }

    @Override
    public String getUsername() {
        return userRegistration.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}


