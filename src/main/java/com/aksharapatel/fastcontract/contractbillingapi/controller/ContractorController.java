package com.aksharapatel.fastcontract.contractbillingapi.controller;

import com.aksharapatel.fastcontract.contractbillingapi.exception.RecordNotFoundException;
import com.aksharapatel.fastcontract.contractbillingapi.models.Contractor;
import com.aksharapatel.fastcontract.contractbillingapi.services.ContractorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/contractors")
public class ContractorController {
    
    @Autowired
    ContractorService contractorService;

    @GetMapping
    public ResponseEntity<List<Contractor>> getAllContractors() {
        List<Contractor> contractorList = contractorService.getAllContractors();

        return new ResponseEntity<>(contractorList, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contractor> getContractorById(@PathVariable("id") Long contractorId) throws RecordNotFoundException {
        Contractor contractor = contractorService.getContractorById(contractorId);

        return new ResponseEntity<>(contractor, new HttpHeaders(), HttpStatus.OK);
    }
}
