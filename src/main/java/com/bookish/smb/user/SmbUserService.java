package com.bookish.smb.user;

import com.bookish.smb.auth.token.VerificationToken;
import com.bookish.smb.auth.token.VerificationTokenService;
import com.bookish.smb.utils.Helpers;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SmbUserService implements UserDetailsService {
    private final SmbUserRepository smbUserRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final VerificationTokenService verificationTokenService;
    private final static String USER_NOT_FOUND_MSG = "user with email %s not found";

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return smbUserRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
    }

    public SmbUser signUpUser(SmbUser smbUser) {
        boolean existingUser = smbUserRepository.findByEmail(smbUser.getEmail()).isPresent();
        if(existingUser) throw new IllegalStateException("email already in use");

        String encodedPassword = passwordEncoder.encode(smbUser.getPassword());
        smbUser.setPassword(encodedPassword);

        SmbUser newUser = smbUserRepository.save(smbUser);

        String token = Helpers.generateOtp(4);
        VerificationToken verificationToken = new VerificationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                smbUser
        );

        verificationTokenService.saveToken(verificationToken);

        return newUser;
    }
}
