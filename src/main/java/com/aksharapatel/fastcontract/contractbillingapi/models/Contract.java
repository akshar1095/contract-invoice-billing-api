package com.aksharapatel.fastcontract.contractbillingapi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="CONTRACTS")
public class Contract implements Serializable {

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
    @JsonIgnore
    private Contractor contractor;

    @ManyToOne
    @JoinColumn(name="vendor_id", nullable = false)
    @JsonIgnore
    private Vendor vendor;

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

    public Contractor getContractor() {
        return contractor;
    }

    public void setContractor(Contractor contractor) {
        this.contractor = contractor;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public List<Invoice> getBillingInvoices() {
        return billingInvoices;
    }
}
