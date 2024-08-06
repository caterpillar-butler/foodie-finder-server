package com.butler.server.service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class VerificationService {

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private AmazonSNS amazonSNS;

    private static final long VERIFICATION_CODE_TTL = 5; // 5분

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

    public void sendPhoneVerificationCode(String phone) {
        String verificationCode = generateRandomCode();
        redisTemplate.opsForValue().set(phone, verificationCode, VERIFICATION_CODE_TTL, TimeUnit.MINUTES);

        // SNS를 사용하여 SMS 전송
        String message = "Your verification code is: " + verificationCode;
        PublishRequest publishRequest = new PublishRequest()
                .withMessage(message)
                .withPhoneNumber(phone);
        PublishResult publishResult = amazonSNS.publish(publishRequest);

        log.info("SMS sent to {}: {}", phone, publishResult.getMessageId());
    }

    public boolean verifyPhoneCode(String phone, String code) {
        String storedCode = redisTemplate.opsForValue().get(phone);
        return storedCode != null && storedCode.equals(code);
    }
}