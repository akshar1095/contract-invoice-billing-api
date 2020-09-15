package com.aksharapatel.fastcontract.contractbillingapi.services;

import com.aksharapatel.fastcontract.contractbillingapi.exception.RecordNotFoundException;
import com.aksharapatel.fastcontract.contractbillingapi.models.Invoice;
import com.aksharapatel.fastcontract.contractbillingapi.repositories.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    public List<Invoice> getAllInvoices() { return invoiceRepository.findAll(); }

    public Invoice getInvoiceById(Long invoiceId) throws RecordNotFoundException {

        Optional<Invoice> invoice = invoiceRepository.findById(invoiceId);

        return invoice.orElseThrow(() -> new RecordNotFoundException("No invoice record exists for the given invoice id"));
    }
}
