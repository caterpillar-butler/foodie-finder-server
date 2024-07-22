package com.butler.server.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
  private String email;
  private String name;
  private String password;
  private String phone;
  private String birth;
  private String phoneVerificationCode;
  private String verificationCode;
  private Boolean gender;

  @Override
  public String toString() {
    return "UserDto [email=" + email + ", name=" + name + ", password=" + password + ", phone=" + phone + ", birth="
        + birth + ", phoneVerificationCode=" + phoneVerificationCode + ", verificationCode=" + verificationCode
        + ", gender=" + gender + "]";
  }

}
