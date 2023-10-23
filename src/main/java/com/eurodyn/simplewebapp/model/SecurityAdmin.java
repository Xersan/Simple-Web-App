package com.eurodyn.simplewebapp.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;

public class SecurityAdmin implements UserDetails {

    @SuppressWarnings("FieldMayBeFinal")
    private Admin admin;

    public SecurityAdmin(Admin admin) {
        this.admin = admin;
    }

    @Override
    public String getUsername() {
        return admin.getUsername();
    }

    @Override
    public String getPassword() {
        return admin.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.stream(admin
                .getRoles()
                .split(","))
                .map(SimpleGrantedAuthority::new)
                .toList();
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
