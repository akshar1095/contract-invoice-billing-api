package com.aksharapatel.fastcontract.contractbillingapi.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name="CONTRACTORS")
public class Contractor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="contractor_id", nullable = false, updatable = false)
    private Long contractorId;

    @NotNull
    @Column(name="contractor_name", nullable = false)
    private String contractorName;

    @OneToMany(mappedBy="contractor")
    private List<Contract> contracts;

    public Contractor() {}

    public Long getContractorId() { return contractorId; }

    public String getContractorName() { return contractorName; }

    public List<Contract> getContracts() {
        return contracts;
    }

    public void setContractorName(String contractorName) { this.contractorName = contractorName; }

    public void setContracts(List<Contract> contracts) { this.contracts = contracts; }
}
