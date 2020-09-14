package com.aksharapatel.fastcontract.contractbillingapi.models;

import javax.persistence.*;

@Entity
@Table(name="USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id", updatable = false, nullable = false)
    private Long userId;

    @Column(name="user_name")
    private String userName;

}
