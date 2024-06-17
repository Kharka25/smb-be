package com.bookish.smb.utils.constants;

import lombok.Getter;

@Getter
public enum Roles {
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");

    private final String role;

    Roles(String role) {
        this.role = role;
    }

}
