package com.aksharapatel.fastcontract.contractbillingapi.models;

import javax.persistence.*;
import java.util.List;

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

    @OneToMany(mappedBy="contract")
    private List<Invoice> billedInvoices;

    @ManyToOne
    @JoinColumn(name="contract_id", nullable = false)
    private Contractor creatingContractor;

    @ManyToOne
    @JoinColumn(name="vendor_id", nullable = false)
    private Vendor assignedVendor;

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public String getContractDescription() {
        return contractDescription;
    }

    public void setContractDescription(String contractDescription) {
        this.contractDescription = contractDescription;
    }

    public Double getContractValue() {
        return contractValue;
    }

    public void setContractValue(Double contractValue) {
        this.contractValue = contractValue;
    }

    public Contractor getCreatingContractor() {
        return creatingContractor;
    }

    public void setCreatingContractor(Contractor creatingContractor) {
        this.creatingContractor = creatingContractor;
    }

    public Vendor getAssignedVendor() {
        return assignedVendor;
    }

    public void setAssignedVendor(Vendor assignedVendor) {
        this.assignedVendor = assignedVendor;
    }

    public List<Invoice> getBilledInvoices() {
        return billedInvoices;
    }
}
