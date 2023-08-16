package com.assesment.Multisig.model;

import jakarta.persistence.*;


import lombok.Data;

@Data
@Entity
@Table(name="users")
public class User {
    @Id
    @Column(name="user_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long userId;
    private String username;
    private String mail;
    private String password;
}
