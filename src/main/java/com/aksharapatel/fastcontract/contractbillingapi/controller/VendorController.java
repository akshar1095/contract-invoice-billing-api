package com.aksharapatel.fastcontract.contractbillingapi.controller;

import com.aksharapatel.fastcontract.contractbillingapi.exception.RecordNotFoundException;
import com.aksharapatel.fastcontract.contractbillingapi.models.Vendor;
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

    @PostMapping(value = "/vendor", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vendor> createVendor(@RequestBody Vendor newVendor) {
        Vendor vendor = vendorService.createVendor(newVendor);

        return new ResponseEntity<>(vendor, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Vendor>> getAllVendors() {
        List<Vendor> vendorList = vendorService.getAllVendors();

        return new ResponseEntity<>(vendorList, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vendor> getVendorById(@PathVariable("id") Long vendorId) throws RecordNotFoundException {
        Vendor vendor = vendorService.getVendorById(vendorId);

        return new ResponseEntity<>(vendor, new HttpHeaders(), HttpStatus.OK);
    }
}
