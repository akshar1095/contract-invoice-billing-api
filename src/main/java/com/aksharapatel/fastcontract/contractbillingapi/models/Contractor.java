package com.aksharapatel.fastcontract.contractbillingapi.models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@DiscriminatorValue("Contractor")
public class Contractor extends User {

    @OneToMany(mappedBy="contractor")
    private List<Contract> createdContracts;

    public List<Contract> getCreatedContracts() {
        return createdContracts;
    }
}
