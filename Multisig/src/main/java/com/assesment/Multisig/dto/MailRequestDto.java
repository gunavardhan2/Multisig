package com.assesment.Multisig.dto;

import lombok.Data;

@Data
public class MailRequestDto {
    private String to;
    private String subject;
    private String text;
}
