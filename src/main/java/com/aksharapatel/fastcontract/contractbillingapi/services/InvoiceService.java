package com.aksharapatel.fastcontract.contractbillingapi.services;

import com.aksharapatel.fastcontract.contractbillingapi.exception.RecordNotFoundException;
import com.aksharapatel.fastcontract.contractbillingapi.models.Invoice;
import com.aksharapatel.fastcontract.contractbillingapi.repositories.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    public List<Invoice> getAllInvoices() {

        List<Invoice> invoiceList = invoiceRepository.findAll();

        if(invoiceList.size() > 0) {
            return invoiceList;
        } else {
            return new ArrayList<>();
        }
    }

    public Invoice getInvoiceById(Long invoiceId) throws RecordNotFoundException {

        Optional<Invoice> invoice = invoiceRepository.findById(invoiceId);

        if(invoice.isPresent()) {
            return invoice.get();
        } else {
            throw new RecordNotFoundException("No invoice record exists for the given id");
        }
    }
}
