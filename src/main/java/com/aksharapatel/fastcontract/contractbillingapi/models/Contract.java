package com.aksharapatel.fastcontract.contractbillingapi.models;

import javax.persistence.*;

@Entity
@Table(name="CONTRACTS")
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="contract_id", updatable = false, nullable = false)
    private Long contractId;

    @Column(name="contract_name")
    private String contractName;

    @Column(name="contract_description")
    private String contractDescription;

    @Column(name="contract_value")
    private Double contractValue;
}
