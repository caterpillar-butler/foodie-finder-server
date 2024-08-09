package com.butler.server.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MailVerificationDto {
    private String email;
    private String code;
}
