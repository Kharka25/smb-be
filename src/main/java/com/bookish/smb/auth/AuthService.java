package com.bookish.smb.auth;

import com.bookish.smb.user.SmbUser;
import com.bookish.smb.user.SmbUserService;
import com.bookish.smb.utils.Helpers;
import com.bookish.smb.utils.constants.Roles;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthService {
    private final SmbUserService smbUserService;

    SmbUser register(AuthRequest authRequest) {
        boolean isValidEmail = Helpers.validateEmail(authRequest.getEmail());
        if(!isValidEmail) throw new IllegalStateException("email is not valid");

        return smbUserService.signUpUser(new SmbUser(
                authRequest.getFirstName(),
                authRequest.getLastName(),
                authRequest.getEmail(),
                authRequest.getPassword(),
                Roles.USER
        ));
    }
}
