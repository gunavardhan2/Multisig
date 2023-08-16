package com.assesment.Multisig.dto;

import lombok.Data;

import java.util.List;

@Data
public class SignRequestDto {
    private Long creatorId;
    private List<Long> userIds;
    private List<Boolean> visible;
}
