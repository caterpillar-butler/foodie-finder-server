package com.butler.server.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhoneVerificationDto {
    private String phone;
    private String code;
}
