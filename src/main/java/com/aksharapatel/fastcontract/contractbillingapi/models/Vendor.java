package com.aksharapatel.fastcontract.contractbillingapi.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="VENDORS")
public class Vendor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="vendor_id", updatable = false, nullable = false)
    private Long vendorId;

    @Column(name="vendor_name")
    private String vendorName;

    @OneToMany(mappedBy="assignedVendor")
    private List<Contract> assignedContracts;

    public Long getVendorId() { return vendorId; }

    public String getVendorName() { return vendorName; }

    public List<Contract> getAssignedContracts() {
        return assignedContracts;
    }
}
