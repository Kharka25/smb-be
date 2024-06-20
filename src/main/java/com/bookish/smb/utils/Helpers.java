package com.bookish.smb.utils;

import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;
import java.util.regex.Pattern;

@Service
public abstract class Helpers {
    private final static String EMAIL_VALIDATION_REGEX = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,8}$";
    private static final Pattern emailPattern = Pattern.compile(EMAIL_VALIDATION_REGEX, Pattern.CASE_INSENSITIVE);

    public static String generateOtp(int otpLength) {
        StringBuilder otp = new StringBuilder();
        Random random = new Random();

        if(otpLength <= 3) throw new IllegalArgumentException("OTP length is too short");

        for(int i = 1; i <= otpLength; i++) {
            int randomNumber = random.nextInt(1, 9);
            otp.append(randomNumber);
        }

        return otp.toString();
    }

    public static String generateToken() {
        return UUID.randomUUID().toString();
    }

    public static boolean validateEmail(String email) {
        return emailPattern.matcher(email).matches();
    };
}
