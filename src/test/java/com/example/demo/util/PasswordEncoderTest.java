package com.example.demo.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordEncoderTest {

    @Test
    void encode() {
        // GIVEN
        String rawPassword = "qwe123";

        // WHEN
        String encodedPassword = PasswordEncoder.encode(rawPassword);

        // THEN
        assertNotEquals(rawPassword, encodedPassword);
    }

    @Test
    void matches() {
        // GIVEN
        String rawPassword = "asd123";
        String encodedPassword = PasswordEncoder.encode(rawPassword);

        // WHEN
        boolean isMatches = PasswordEncoder.matches(rawPassword, encodedPassword);

        // THEN
        assertTrue(isMatches);
    }

    @Test
    void mismatchPassword() {
        // GIVEN
        String rawPassword = "zxc123";
        String otherPassword = "qwe123";
        String encodedWrongPassword = PasswordEncoder.encode(otherPassword);

        // WHEN
        boolean isMismatches = PasswordEncoder.matches(rawPassword, encodedWrongPassword);

        // THEN
        assertFalse(isMismatches);
    }
}