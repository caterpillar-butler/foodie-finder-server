package com.butler.server.service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class VerificationService {

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private static final long VERIFICATION_CODE_TTL = 5; // 5분

    /**
     * Sends an email verification code to the specified email address.
     *
     * @param email the email address to send the verification code to
     */
    public void sendEmailVerificationCode(String email) {
        String verificationCode = generateRandomCode();
        redisTemplate.opsForValue().set(email, verificationCode, VERIFICATION_CODE_TTL, TimeUnit.MINUTES); // 코드 저장 및
                                                                                                           // TTL 설정
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Email Verification Code");
        message.setText("Your verification code is: " + verificationCode);
        emailSender.send(message);
    }

    /**
     * Verifies the email code against the stored code for the specified email.
     *
     * @param email the email address to verify
     * @param code  the verification code to check
     * @return true if the code matches, false otherwise
     */
    public boolean verifyEmailCode(String email, String code) {
        String storedCode = redisTemplate.opsForValue().get(email);
        return storedCode != null && storedCode.equals(code);
    }

    /**
     * Generates a random 6-digit verification code.
     *
     * @return a randomly generated verification code as a String
     */
    private String generateRandomCode() {
        Random random = new Random();
        int code = 100000 + random.nextInt(900000);
        return String.valueOf(code);
    }

    /**
     * Formats the given phone number to the international format.
     *
     * @param phoneNumber the phone number to format
     * @return the formatted phone number
     * @throws IllegalArgumentException if the phone number is invalid
     */
    public String formatPhoneNumber(String phoneNumber) throws IllegalArgumentException {
        if (phoneNumber == null || (phoneNumber.length() != 11 && phoneNumber.length() != 10)) {
            throw new IllegalArgumentException("Invalid phone number format");
        }
        phoneNumber = phoneNumber.replaceAll("[^0-9]", "");
        if (phoneNumber.startsWith("0")) {
            return "82" + phoneNumber.substring(1);
        } else {
            return "82" + phoneNumber;
        }
    }

    /**
     * Sends a phone verification code to the specified phone number.
     *
     * @param phone the phone number to send the verification code to
     */
    public void sendPhoneVerificationCode(String phone) {
        phone = formatPhoneNumber(phone);
        log.info("phone: {}", phone);
        String verificationCode = generateRandomCode();
        redisTemplate.opsForValue().set(phone, verificationCode, VERIFICATION_CODE_TTL, TimeUnit.MINUTES);
    }

    /**
     * Verifies the phone code against the stored code for the specified phone
     * number.
     *
     * @param phone the phone number to verify
     * @param code  the verification code to check
     * @return true if the code matches, false otherwise
     */
    public boolean verifyPhoneCode(String phone, String code) {
        String storedCode = redisTemplate.opsForValue().get(phone);
        return storedCode != null && storedCode.equals(code);
    }
}