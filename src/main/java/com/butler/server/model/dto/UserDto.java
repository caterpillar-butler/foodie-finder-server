package com.butler.server.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
  private Long id;
  private String username;
  private String password;
  private String socialSecurityNumber;
  private String name;
  private String phoneNumber;
}
