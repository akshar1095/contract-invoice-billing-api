package com.aksharapatel.fastcontract.contractbillingapi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="CONTRACTS")
public class Contract implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="contract_id", updatable = false, nullable = false)
    private Long contractId;

    @NotNull
    @Column(name="contract_name", nullable = false)
    private String contractName;

    @Column(name="contract_description")
    private String contractDescription;

    @NotNull
    @Column(name="contract_value", nullable = false)
    private Double contractValue;

    @OneToMany(mappedBy="contract")
    private List<Invoice> invoices;

    @ManyToOne
    @JoinColumn(name="contractor_id", nullable = false)
    @JsonIgnore
    private Contractor contractor;

    @ManyToOne
    @JoinColumn(name="vendor_id", nullable = false)
    @JsonIgnore
    private Vendor vendor;

    public Contract() {}

    public Long getContractId() { return contractId; }

    public String getContractName() { return contractName; }

    public String getContractDescription() { return contractDescription; }

    public Double getContractValue() { return contractValue; }

    public List<Invoice> getInvoices() { return invoices; }

    public Contractor getContractor() { return contractor; }

    public Vendor getVendor() { return vendor; }

    public void setContractName(String contractName) { this.contractName = contractName; }

    public void setContractDescription(String contractDescription) { this.contractDescription = contractDescription; }

    public void setContractValue(Double contractValue) { this.contractValue = contractValue; }

    public void setInvoices(List<Invoice> invoices) { this.invoices = invoices; }

    public void setContractor(Contractor contractor) { this.contractor = contractor; }

    public void setVendor(Vendor vendor) { this.vendor = vendor; }
}
