package com.aksharapatel.fastcontract.contractbillingapi.services;

import com.aksharapatel.fastcontract.contractbillingapi.exception.RecordNotFoundException;
import com.aksharapatel.fastcontract.contractbillingapi.exception.ValueExceedsLimitException;
import com.aksharapatel.fastcontract.contractbillingapi.models.Contract;
import com.aksharapatel.fastcontract.contractbillingapi.models.Invoice;
import com.aksharapatel.fastcontract.contractbillingapi.repositories.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    public Invoice createInvoice(Invoice invoice) { return invoiceRepository.save(invoice); }

    public Invoice setInvoiceIsVoid(Long invoiceId,Invoice updatedInvoice) throws RecordNotFoundException {
        return invoiceRepository.findById(invoiceId).map(invoice -> {
            invoice.setInvoiceVoid(updatedInvoice.getInvoiceVoid());
            return invoiceRepository.save(invoice);
        }).orElseThrow(() -> new RecordNotFoundException("Invoice with given " + invoiceId + " not found"));
    }

    public List<Invoice> getAllInvoices() { return invoiceRepository.findAll(); }

    public List<Invoice> getAllNonVoidInvoicesByContract(Contract contract) {
        return invoiceRepository.findByContract(contract);
    }

    public Invoice getInvoiceById(Long invoiceId) throws RecordNotFoundException {

        Optional<Invoice> invoice = invoiceRepository.findById(invoiceId);

        return invoice.orElseThrow(() -> new RecordNotFoundException("Invoice with given " + invoiceId + " not found"));
    }
}
