package com.aksharapatel.fastcontract.contractbillingapi.repositories;

import com.aksharapatel.fastcontract.contractbillingapi.models.Contractor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractorRepository extends JpaRepository<Contractor, Long> {
}
