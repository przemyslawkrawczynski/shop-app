package com.tt.shop.domain.enumvalues;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {

    ROLE_ADMIN("ADMIN"), ROLE_USER("USER");
    private final String description;

    Role(String role) {
        this.description = role;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String getAuthority() {
        return description;
    }
}