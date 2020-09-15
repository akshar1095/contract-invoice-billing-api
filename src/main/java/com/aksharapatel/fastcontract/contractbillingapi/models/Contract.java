package com.aksharapatel.fastcontract.contractbillingapi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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

    @OneToMany(mappedBy="billedContract")
    private List<Invoice> billingInvoices;

    @ManyToOne
    @JoinColumn(name="contractor_id", nullable = false)
    private Contractor creatingContractor;

    @ManyToOne
    @JoinColumn(name="vendor_id", nullable = false)
    private Vendor assignedVendor;

    public Long getContractId() {
        return contractId;
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

    public List<Invoice> getBillingInvoices() {
        return billingInvoices;
    }
}
