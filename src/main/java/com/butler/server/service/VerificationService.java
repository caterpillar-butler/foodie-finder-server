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

    private static final long VERIFICATION_CODE_TTL = 10; // 10분

    public void sendEmailVerificationCode(String email) {
        String verificationCode = generateRandomCode();
        redisTemplate.opsForValue().set(email, verificationCode, VERIFICATION_CODE_TTL, TimeUnit.MINUTES); // 코드 저장 및 TTL 설정
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Email Verification Code");
        message.setText("Your verification code is: " + verificationCode);
        emailSender.send(message);
    }

    public boolean verifyEmailCode(String email, String code) {
        String storedCode = redisTemplate.opsForValue().get(email);
        return storedCode != null && storedCode.equals(code);
    }

    private String generateRandomCode() {
        Random random = new Random();
        int code = 100000 + random.nextInt(900000);
        return String.valueOf(code);
    }
}