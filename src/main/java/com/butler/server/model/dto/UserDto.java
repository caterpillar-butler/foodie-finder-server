package com.butler.server.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
  private String email;
  private String password;
  private String phone;
  private String birth;
  private Boolean gender;

  @Override
  public String toString() {
    return "UserDto [email=" + email + ", password=" + password + ", phone=" + phone + ", birth=" + birth + ", gender="
        + gender + "]";
  }
}
