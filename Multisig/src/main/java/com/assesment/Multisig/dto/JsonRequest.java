package com.assesment.Multisig.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class JsonRequest {
    private Long userId;
    private Long transactionId;
    private String comment;
    private Boolean accept;
}
