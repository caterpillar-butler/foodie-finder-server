package com.butler.server.service;

import org.springframework.stereotype.Service;

@Service
public class VerificationService {

  public boolean verifyResidentRegistrationNumber(String rrn) {
    // 주민등록번호 검증 로직 구현
    // 실제 검증 로직은 생략하고, 예시로 항상 true를 반환
    return true;
  }

  public boolean verifyPhoneNumber(String phoneNumber, String name, String rrn) {
    // 휴대전화 번호 검증 로직 구현
    // 실제 검증 로직은 생략하고, 예시로 항상 true를 반환
    return true;
  }
}