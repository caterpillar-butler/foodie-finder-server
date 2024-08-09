package com.butler.server.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.butler.server.model.dto.MailVerificationDto;
import com.butler.server.model.dto.PhoneVerificationDto;
import com.butler.server.model.dto.UserDto;
import com.butler.server.service.UserService;
import com.butler.server.service.VerificationService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/auth")
public class AuthController {

  @Autowired
  private UserService userService;

  @Autowired
  private VerificationService verificationService;

  @PostMapping("/register")
  public ResponseEntity<String> createUser(@RequestBody UserDto userDto) {
    log.info("AuthController: " + userDto.toString());

    userService.createUser(userDto);
    return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
  }

  @PostMapping("/email-verification")
  public ResponseEntity<String> generateMailVerificationCode(@RequestBody MailVerificationDto mailVerificationDto) {

    try {
      if (userService.isEmailExist(mailVerificationDto.getEmail())) {
        return new ResponseEntity<>("Email already exists", HttpStatus.BAD_REQUEST);
      }
      verificationService.sendEmailVerificationCode(mailVerificationDto.getEmail());
      return new ResponseEntity<>("Email verification code sent", HttpStatus.OK);
    } catch (Exception e) {
      log.error("Error during email verification process", e);
      return new ResponseEntity<>("An error occurred while sending the email verification code",
          HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PostMapping("/email-verification/code")
  public ResponseEntity<String> verifyEmailCode(@RequestBody MailVerificationDto mailVerificationDto) {
    try {
      boolean isValid = verificationService.verifyEmailCode(mailVerificationDto.getEmail(),
          mailVerificationDto.getCode());
      if (isValid) {
        return new ResponseEntity<>("Verification successful", HttpStatus.OK);
      } else {
        return new ResponseEntity<>("Invalid verification code", HttpStatus.BAD_REQUEST);
      }
    } catch (Exception e) {
      log.error("Error during email code verification process", e);
      return new ResponseEntity<>("An error occurred while verifying the email code", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PostMapping("/phone-verification")
  public ResponseEntity<String> generatePhoneVerificationCode(@RequestBody PhoneVerificationDto phoneVerificationDto) {
    try {
      if (userService.isPhoneExist(phoneVerificationDto.getPhone())) {
        return new ResponseEntity<>("Phone number already exists", HttpStatus.BAD_REQUEST);
      }
      verificationService.sendPhoneVerificationCode(phoneVerificationDto.getPhone());
      return new ResponseEntity<>("Phone verification code sent", HttpStatus.OK);
    } catch (Exception e) {
      log.error("Error during phone verification process", e);
      return new ResponseEntity<>("An error occurred while sending the phone verification code", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PostMapping("/phone-verification/code")
  public ResponseEntity<String> verifyPhoneCode(@RequestBody PhoneVerificationDto phoneVerificationDto) {
    try {
      boolean isValid = verificationService.verifyPhoneCode(phoneVerificationDto.getPhone(),
          phoneVerificationDto.getCode());
      if (isValid) {
        return new ResponseEntity<>("Verification successful", HttpStatus.OK);
      } else {
        return new ResponseEntity<>("Invalid verification code", HttpStatus.BAD_REQUEST);
      }
    } catch (Exception e) {
      log.error("Error during phone code verification process", e);
      return new ResponseEntity<>("An error occurred while verifying the phone code", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}