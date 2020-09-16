package com.aksharapatel.fastcontract.contractbillingapi.controller;

import com.aksharapatel.fastcontract.contractbillingapi.exception.RecordNotFoundException;
import com.aksharapatel.fastcontract.contractbillingapi.models.Contract;
import com.aksharapatel.fastcontract.contractbillingapi.models.Contractor;
import com.aksharapatel.fastcontract.contractbillingapi.models.Invoice;
import com.aksharapatel.fastcontract.contractbillingapi.models.Vendor;
import com.aksharapatel.fastcontract.contractbillingapi.services.ContractService;
import com.aksharapatel.fastcontract.contractbillingapi.services.ContractorService;
import com.aksharapatel.fastcontract.contractbillingapi.services.InvoiceService;
import com.aksharapatel.fastcontract.contractbillingapi.services.VendorService;
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
public class ContractController {

    @Autowired
    ContractService contractService;

    @Autowired
    InvoiceService invoiceService;

    @Autowired
    ContractorService contractorService;

    @Autowired
    VendorService vendorService;

    Logger logger = LoggerFactory.getLogger(ContractorController.class);

    @GetMapping(value = "/contracts", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Contract>> getAllContracts() {
        List<Contract> contractList = contractService.getAllContracts();
        logger.info("Successfully Retrieved Contracts List: {}", contractList);
        return new ResponseEntity<>(contractList, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(value = "/contracts/{contractId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Contract> getContractById(@PathVariable("contractId") Long contractId) throws RecordNotFoundException {
        Contract contract = contractService.getContractById(contractId);
        logger.info("Successfully Retrieved Contract: {}", contract);
        return new ResponseEntity<>(contract, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(value = "/contracts/{contractId}/invoices", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Invoice>> getAllNonVoidInvoicesByContractId(@PathVariable("contractId") Long contractId) throws RecordNotFoundException {
        Contract contract = contractService.getContractById(contractId);
        logger.info("Successfully Retrieved Contract: {}", contract);
        List<Invoice> invoiceList = invoiceService.getAllNonVoidInvoicesByContract(contract);
        logger.info("Successfully Retrieved Invoice List: {}", invoiceList);
        return new ResponseEntity<>(invoiceList, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(value = "/contracts/{contractId}/remaining", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Double> getValueRemainingByContractId(@PathVariable("contractId") Long contractId) throws RecordNotFoundException {
        Double remainingValue = contractService.getValueRemainingByContractId(contractId);
        logger.info("Successfully Remaining Value: {}", remainingValue);
        return new ResponseEntity<>(remainingValue, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping(value = "/contractors/{contractorId}/{vendorId}/contracts", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Contract> createContract(@PathVariable("contractorId") Long contractorId,
                                   @PathVariable("vendorId") Long vendorId,
                                   @RequestBody Contract newContract) throws RecordNotFoundException {
        Contractor contractor = contractorService.getContractorById(contractorId);
        logger.info("Successfully Retrieved Contractor: {}", contractor);
        Vendor vendor = vendorService.getVendorById(vendorId);
        logger.info("Successfully Retrieved Vendor: {}", vendor);
        newContract.setContractor(contractor);
        newContract.setVendor(vendor);
        Contract contract = contractService.createContract(newContract);
        logger.info("Successfully Created New Contract: {}", contract);
        return new ResponseEntity<>(contract, new HttpHeaders(), HttpStatus.OK);
    }
}
