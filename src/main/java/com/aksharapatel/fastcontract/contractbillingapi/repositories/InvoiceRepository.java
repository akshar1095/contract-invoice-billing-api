package com.aksharapatel.fastcontract.contractbillingapi.repositories;

import com.aksharapatel.fastcontract.contractbillingapi.models.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
}
