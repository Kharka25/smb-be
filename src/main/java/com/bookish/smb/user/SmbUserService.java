package com.bookish.smb.user;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SmbUserService implements UserDetailsService {
    private final SmbUserRepository smbUserRepository;
    private final static String USER_NOT_FOUND_MSG = "user with email %s not found";

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return smbUserRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
    }

    public SmbUser signUpUser(SmbUser smbUser) {
        boolean existingUser = smbUserRepository.findByEmail(smbUser.getEmail()).isPresent();
        if(existingUser) throw new IllegalStateException("email already in use");

        return smbUserRepository.save(smbUser);
    }
}
