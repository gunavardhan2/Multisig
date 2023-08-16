package com.assesment.Multisig.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="sign_request")
public class SignRequest {
    @Id
    @Column(name="request_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long requestId;
    @Column(name="creator_id")
    private Long creatorId;
    @Column(name="transaction_id")
    private Long transactionId;
    @Column(name="user_id")
    private Long userId;
    private String comment;
    private Boolean approved;
    private Byte image;
    private Boolean visible;
    @Lob
    private byte[] data;

}
