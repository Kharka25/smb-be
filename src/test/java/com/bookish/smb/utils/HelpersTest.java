package com.bookish.smb.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Helper Functions Test")
public class HelpersTest {
    public final int otpLength = 4;

    @DisplayName("Generates OTP")
    @Test
    void shouldGenerateOtp() {;
        String otpCode = Helpers.generateOtp(otpLength);
        assertThat(otpCode).isNotNull();
    }

    @DisplayName("Generates OTP with specified length")
    @Test
    void shouldGenerateOtpWithSpecifiedLength(){
        String otp = Helpers.generateOtp(otpLength);
        assertThat(otp.length()).isEqualTo(otpLength);
    }

    @DisplayName("Generates random OTP")
    @Test
    void shouldGenerateRandomOtp() {
        String otp1 = Helpers.generateOtp(otpLength);
        String otp2 = Helpers.generateOtp(otpLength);

        assertThat(otp1).isNotEqualTo(otp2);
    }

    @DisplayName("Throws Exception when OTP length is less than 4")
    @Test
    void shouldThrowIllegalArgumentExceptionWhenOtpLengthIsLessThan4() {
        String expectedExceptionMsg = "OTP length is too short";

        IllegalArgumentException actualExceptionMsg = assertThrows(IllegalArgumentException.class, () -> {
            final String otp = Helpers.generateOtp(3);
        }, "OTP length less that 4 should throw Argument Exception");

        assertThat(expectedExceptionMsg).isEqualTo(actualExceptionMsg.getMessage());
    }

    @DisplayName("Email Validation")
    @Test
    void shouldReturnTrueIfEmailIsValid() {
        String email = "test@mail.com";
        boolean isValidEmail = Helpers.validateEmail(email);

        assertThat(isValidEmail).isTrue();
    }

    @DisplayName("Email Validation - invalid email")
    @Test
    void shouldReturnFalseIfEmailIsInvalid() {
        String email = "test.mail.com";
        boolean isValidEmail = Helpers.validateEmail(email);

        assertThat(isValidEmail).isFalse();
    }
}
