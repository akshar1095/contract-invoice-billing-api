package com.aksharapatel.fastcontract.contractbillingapi.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name="VENDORS")
public class Vendor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="vendor_id", nullable = false, updatable = false)
    private Long vendorId;

    @NotNull
    @Column(name="vendor_name", nullable = false)
    private String vendorName;

    @OneToMany(mappedBy="vendor")
    private List<Contract> contracts;

    public Vendor(){}

    public Long getVendorId() { return vendorId; }

    public String getVendorName() { return vendorName; }

    public List<Contract> getContracts() { return contracts; }

    public void setVendorName(String vendorName) { this.vendorName = vendorName; }

    public void setContracts(List<Contract> contracts) { this.contracts = contracts; }
}
