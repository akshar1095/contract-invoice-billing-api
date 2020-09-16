package com.aksharapatel.fastcontract.contractbillingapi.controller;

import com.aksharapatel.fastcontract.contractbillingapi.exception.RecordNotFoundException;
import com.aksharapatel.fastcontract.contractbillingapi.exception.ValueExceedsLimitException;
import com.aksharapatel.fastcontract.contractbillingapi.models.Contract;
import com.aksharapatel.fastcontract.contractbillingapi.models.Invoice;
import com.aksharapatel.fastcontract.contractbillingapi.services.ContractService;
import com.aksharapatel.fastcontract.contractbillingapi.services.InvoiceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InvoiceController {

    @Autowired
    InvoiceService invoiceService;

    @Autowired
    ContractService contractService;

    Logger logger = LoggerFactory.getLogger(ContractorController.class);

    @PostMapping(value = "/contract/{contractId}/invoices", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Invoice> createInvoice(@PathVariable("contractId") Long contractId,
                                                 @RequestBody Invoice newInvoice) throws RecordNotFoundException, ValueExceedsLimitException {
        Contract contract = contractService.getContractById(contractId);
        logger.info("Successfully Retrieved Contract: {}", contract);
        Double remainingValue = contractService.getValueRemainingByContractId(contractId);
        logger.info("Successfully Remaining Value: {}", remainingValue);

        if(newInvoice.getInvoiceValue() > remainingValue) {
            throw new ValueExceedsLimitException("Invoice Value: " + newInvoice.getInvoiceValue() + " Exceeds Value Remaining on Contract: " + remainingValue);
        } else {
            newInvoice.setContract(contract);
            Invoice invoice = invoiceService.createInvoice(newInvoice);
            logger.info("Successfully Created New Invoice: {}", invoice);
            return new ResponseEntity<>(invoice, new HttpHeaders(), HttpStatus.OK);
        }
    }

    @GetMapping(value = "/invoices", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Invoice>> getAllInvoices() {
        List<Invoice> invoiceList = invoiceService.getAllInvoices();
        logger.info("Successfully Retrieved Invoice List: {}", invoiceList);
        return new ResponseEntity<>(invoiceList, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(value = "/invoices/{invoiceId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Invoice> getInvoiceById(@PathVariable("invoiceId") Long invoiceId) throws RecordNotFoundException {
        Invoice invoice = invoiceService.getInvoiceById(invoiceId);
        logger.info("Successfully Retrieved Invoice: {}", invoice);
        return new ResponseEntity<>(invoice, new HttpHeaders(), HttpStatus.OK);
    }

    @PutMapping(value="/invoice/{invoiceId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Invoice> setInvoiceIsVoid(@PathVariable("invoiceId") Long invoiceId,
                                                    @RequestBody Invoice updatedInvoice) throws RecordNotFoundException {
        Invoice invoice = invoiceService.setInvoiceIsVoid(invoiceId, updatedInvoice);
        logger.info("Successfully Updated Invoice Is Void: {}", invoice);
        return new ResponseEntity<>(invoice, new HttpHeaders(), HttpStatus.OK);
    }
}
