package com.aksharapatel.fastcontract.contractbillingapi.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name="CONTRACTORS")
public class Contractor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contractorId;

    @NotNull
    @Column(name="contractor_name")
    private String contractorName;

    @OneToMany(mappedBy="contractor", cascade = CascadeType.ALL)
    private List<Contract> contracts;

    public Long getContractorId() { return contractorId; }

    public String getContractorName() { return contractorName; }

    public List<Contract> getContracts() {
        return contracts;
    }
}
