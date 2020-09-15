package com.aksharapatel.fastcontract.contractbillingapi.controller;

import com.aksharapatel.fastcontract.contractbillingapi.exception.RecordNotFoundException;
import com.aksharapatel.fastcontract.contractbillingapi.models.Contractor;
import com.aksharapatel.fastcontract.contractbillingapi.services.ContractorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contractors")
public class ContractorController {
    
    @Autowired
    ContractorService contractorService;

    @PostMapping(value = "/contractor", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Contractor> createContractor(@RequestBody Contractor newContractor) {
        Contractor contractor = contractorService.createContractor(newContractor);

        return new ResponseEntity<>(contractor, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(value = "/contractor/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Contractor> getContractorById(@PathVariable("id") Long contractorId) throws RecordNotFoundException {
        Contractor contractor = contractorService.getContractorById(contractorId);

        return new ResponseEntity<>(contractor, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Contractor>> getAllContractors() {
        List<Contractor> contractorList = contractorService.getAllContractors();

        return new ResponseEntity<>(contractorList, new HttpHeaders(), HttpStatus.OK);
    }
}
