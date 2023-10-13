package com.rv.society.domain;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER, ADMIN, MODERATOR, GUEST;

    @Override
    public String getAuthority() {
        return name();
    }
}
