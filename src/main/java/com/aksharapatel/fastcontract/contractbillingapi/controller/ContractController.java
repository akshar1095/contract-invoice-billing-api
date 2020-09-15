package com.aksharapatel.fastcontract.contractbillingapi.controller;

import com.aksharapatel.fastcontract.contractbillingapi.exception.RecordNotFoundException;
import com.aksharapatel.fastcontract.contractbillingapi.models.Contract;
import com.aksharapatel.fastcontract.contractbillingapi.models.Invoice;
import com.aksharapatel.fastcontract.contractbillingapi.services.ContractService;
import com.aksharapatel.fastcontract.contractbillingapi.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contracts")
public class ContractController {

    @Autowired
    ContractService contractService;

    @Autowired
    InvoiceService invoiceService;

    @GetMapping
    public ResponseEntity<List<Contract>> getAllContracts() {
        List<Contract> contractList = contractService.getAllContracts();

        return new ResponseEntity<>(contractList, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/contract/{id}")
    public ResponseEntity<Contract> getContractById(@PathVariable("id") Long contractId) throws RecordNotFoundException {
        Contract contract = contractService.getContractById(contractId);

        return new ResponseEntity<>(contract, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping(value = "/contract/{contractId}/invoices", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Invoice> createInvoice(@PathVariable("contractId") Long contractId,
                                                 @RequestBody Invoice newInvoice) throws RecordNotFoundException {
        Contract contract = contractService.getContractById(contractId);

        newInvoice.setContract(contract);

        Invoice invoice = invoiceService.createInvoice(newInvoice);

        return new ResponseEntity<>(invoice, new HttpHeaders(), HttpStatus.OK);
    }
}
