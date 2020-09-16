package com.aksharapatel.fastcontract.contractbillingapi.controller;

import com.aksharapatel.fastcontract.contractbillingapi.exception.RecordNotFoundException;
import com.aksharapatel.fastcontract.contractbillingapi.models.Contract;
import com.aksharapatel.fastcontract.contractbillingapi.models.Vendor;
import com.aksharapatel.fastcontract.contractbillingapi.services.ContractService;
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
public class VendorController {

    @Autowired
    VendorService vendorService;

    @Autowired
    ContractService contractService;

    Logger logger = LoggerFactory.getLogger(VendorController.class);

    @PostMapping(value = "/vendors", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vendor> createVendor(@RequestBody Vendor newVendor) {
        Vendor vendor = vendorService.createVendor(newVendor);
        logger.info("Successfully Created New Vendor: {}", vendor);
        return new ResponseEntity<>(vendor, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(value = "/vendors", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Vendor>> getAllVendors() {
        List<Vendor> vendorList = vendorService.getAllVendors();
        logger.info("Successfully Retrieved Vendor List: {}", vendorList);
        return new ResponseEntity<>(vendorList, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(value = "/vendors/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vendor> getVendorById(@PathVariable("id") Long vendorId) throws RecordNotFoundException {
        Vendor vendor = vendorService.getVendorById(vendorId);
        logger.info("Successfully Retrieved Vendor: {}", vendor);
        return new ResponseEntity<>(vendor, new HttpHeaders(), HttpStatus.OK);
    }


    @GetMapping(value = "/vendors/{vendorId}/contracts", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Contract>> getAllContractsByVendorId(@PathVariable("vendorId") Long vendorId) throws RecordNotFoundException {
        Vendor vendor = vendorService.getVendorById(vendorId);
        logger.info("Successfully Retrieved Vendor: {}", vendor);
        List<Contract> contractList = contractService.getAllContractsByVendorId(vendor);
        logger.info("Successfully Retrieved Vendor List: {}", contractList);
        return new ResponseEntity<>(contractList, new HttpHeaders(), HttpStatus.OK);
    }
}
