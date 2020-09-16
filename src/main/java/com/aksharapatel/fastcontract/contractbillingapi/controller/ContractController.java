package com.aksharapatel.fastcontract.contractbillingapi.controller;

import com.aksharapatel.fastcontract.contractbillingapi.exception.RecordNotFoundException;
import com.aksharapatel.fastcontract.contractbillingapi.exception.ValueExceedsLimitException;
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

import javax.print.attribute.standard.Media;
import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping(value = "/contract/{contractId}/invoices/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Invoice>> getAllNonVoidInvoicesByContractId(@PathVariable("contractId") Long contractId) throws RecordNotFoundException {
        Contract invoicesContract = contractService.getContractById(contractId);

        List<Invoice> invoiceList = invoicesContract.getInvoices().stream().filter(invoice -> !invoice.getInvoiceVoid()).collect(Collectors.toList());

        return new ResponseEntity<>(invoiceList, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(value = "/contract/{contractId}/remaining")
    public ResponseEntity<Double> getValueRemainingByContractId(@PathVariable("contractId") Long contractId) throws RecordNotFoundException {
        Contract invoicesContract = contractService.getContractById(contractId);

        Double remainingValue = invoicesContract.getContractValue() - invoicesContract.getInvoices().stream().filter(invoice -> !invoice.getInvoiceVoid()).collect(Collectors.toList())
                .stream().mapToDouble(Invoice::getInvoiceValue).sum();

        return new ResponseEntity<>(remainingValue, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping(value = "/contract/{contractId}/invoices", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Invoice> createInvoice(@PathVariable("contractId") Long contractId,
                                                 @RequestBody Invoice newInvoice) throws RecordNotFoundException, ValueExceedsLimitException {
        Contract contract = contractService.getContractById(contractId);

        Double remainingValue = contract.getContractValue() - contract.getInvoices().stream().filter(invoice -> !invoice.getInvoiceVoid()).collect(Collectors.toList())
                .stream().mapToDouble(Invoice::getInvoiceValue).sum();

        if(newInvoice.getInvoiceValue() > remainingValue) {
            throw new ValueExceedsLimitException("Invoice Value: " + newInvoice.getInvoiceValue() + " Exceeds Value Remaining on Contract: " + remainingValue);
        } else {
            newInvoice.setContract(contract);

            Invoice invoice = invoiceService.createInvoice(newInvoice);

            return new ResponseEntity<>(invoice, new HttpHeaders(), HttpStatus.OK);
        }
    }
}
