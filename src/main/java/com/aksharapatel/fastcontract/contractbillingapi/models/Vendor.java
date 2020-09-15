package com.aksharapatel.fastcontract.contractbillingapi.models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@DiscriminatorValue("Vendor")
public class Vendor extends User {

    @OneToMany(mappedBy="assignedVendor")
    private List<Contract> assignedContracts;

    public List<Contract> getAssignedContracts() {
        return assignedContracts;
    }
}
