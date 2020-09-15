package com.aksharapatel.fastcontract.contractbillingapi.controller;

import com.aksharapatel.fastcontract.contractbillingapi.exception.RecordNotFoundException;
import com.aksharapatel.fastcontract.contractbillingapi.models.Invoice;
import com.aksharapatel.fastcontract.contractbillingapi.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("invoices")
public class InvoiceController {

    @Autowired
    InvoiceService invoiceService;

    @GetMapping
    public ResponseEntity<List<Invoice>> getAllInvoices() {
        List<Invoice> invoiceList = invoiceService.getAllInvoices();

        return new ResponseEntity<>(invoiceList, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Invoice> getInvoiceById(@PathVariable("id") Long invoiceId) throws RecordNotFoundException {
        Invoice invoice = invoiceService.getInvoiceById(invoiceId);

        return new ResponseEntity<>(invoice, new HttpHeaders(), HttpStatus.OK);
    }

    @PutMapping(value="/invoice/{invoiceId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Invoice> setInvoiceIsVoid(@PathVariable("invoiceId") Long invoiceId,
                                                    @RequestBody Invoice updatedInvoice) throws RecordNotFoundException {
        Invoice invoice = invoiceService.setInvoiceIsVoid(invoiceId, updatedInvoice);

        return new ResponseEntity<>(invoice, new HttpHeaders(), HttpStatus.OK);
    }
}
