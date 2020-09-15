package com.aksharapatel.fastcontract.contractbillingapi.controller;

import com.aksharapatel.fastcontract.contractbillingapi.exception.RecordNotFoundException;
import com.aksharapatel.fastcontract.contractbillingapi.models.Contract;
import com.aksharapatel.fastcontract.contractbillingapi.models.Contractor;
import com.aksharapatel.fastcontract.contractbillingapi.models.Vendor;
import com.aksharapatel.fastcontract.contractbillingapi.services.ContractService;
import com.aksharapatel.fastcontract.contractbillingapi.services.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendors")
public class VendorController {

    @Autowired
    VendorService vendorService;

    @Autowired
    ContractService contractService;

    @PostMapping(value = "/vendor", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vendor> createVendor(@RequestBody Vendor newVendor) {
        Vendor vendor = vendorService.createVendor(newVendor);

        return new ResponseEntity<>(vendor, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(value = "/vendor/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vendor> getVendorById(@PathVariable("id") Long vendorId) throws RecordNotFoundException {
        Vendor vendor = vendorService.getVendorById(vendorId);

        return new ResponseEntity<>(vendor, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Vendor>> getAllVendors() {
        List<Vendor> vendorList = vendorService.getAllVendors();

        return new ResponseEntity<>(vendorList, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(value = "/vendor/{vendorId}/contracts", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Contract>> getAllContractsByVendorId(@PathVariable("vendorId") Long vendorId) throws RecordNotFoundException {
        Vendor contractsVendor = vendorService.getVendorById(vendorId);

        List<Contract> contractList = contractService.getAllContractsByVendorId(contractsVendor);

        return new ResponseEntity<>(contractList, new HttpHeaders(), HttpStatus.OK);
    }
}
