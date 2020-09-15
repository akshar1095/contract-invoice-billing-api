package com.aksharapatel.fastcontract.contractbillingapi.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name="CONTRACTORS")
public class Contractor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="contractor_id", updatable = false, nullable = false)
    private Long contractorId;

    @NotNull
    @Column(name="contractor_name")
    private String contractorName;

    @OneToMany(mappedBy="creatingContractor")
    private List<Contract> createdContracts;

    public Long getContractorId() { return contractorId; }

    public String getContractorName() { return contractorName; }

    public List<Contract> getCreatedContracts() {
        return createdContracts;
    }
}
