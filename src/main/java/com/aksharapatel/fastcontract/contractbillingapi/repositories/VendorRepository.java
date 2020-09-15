package com.aksharapatel.fastcontract.contractbillingapi.repositories;

import com.aksharapatel.fastcontract.contractbillingapi.models.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Long> {
}
