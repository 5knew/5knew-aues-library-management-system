package com.aues.usersms.model.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_LIBRARIAN, ROLE_STUDENT, ROLE_ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
