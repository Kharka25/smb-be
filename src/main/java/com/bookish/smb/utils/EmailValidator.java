package com.bookish.smb.utils;

import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class EmailValidator {
    private final static String EMAIL_VALIDATION_REGEX = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,8}$";
    private final Pattern emailPattern = Pattern.compile(EMAIL_VALIDATION_REGEX, Pattern.CASE_INSENSITIVE);
    public boolean isValidEmail(String email) {
        return emailPattern.matcher(email).matches();
    }
}
