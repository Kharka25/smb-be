package com.bookish.smb.utils;

import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;
import java.util.regex.Pattern;

@Service
public abstract class Helpers {

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
}
