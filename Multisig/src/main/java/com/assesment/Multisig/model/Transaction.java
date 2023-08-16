package com.assesment.Multisig.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="transaction")
public class Transaction {
    @Id
    @Column(name="transaction_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long transactionId;
    @Column(name="creator_id")
    private Long creatorId;
    private Boolean executed;
    private Boolean approved;

}
