package com.aksharapatel.fastcontract.contractbillingapi.controller;

import com.aksharapatel.fastcontract.contractbillingapi.exception.RecordNotFoundException;
import com.aksharapatel.fastcontract.contractbillingapi.models.Contract;
import com.aksharapatel.fastcontract.contractbillingapi.models.Contractor;
import com.aksharapatel.fastcontract.contractbillingapi.services.ContractService;
import com.aksharapatel.fastcontract.contractbillingapi.services.ContractorService;
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
public class ContractorController {
    
    @Autowired
    ContractorService contractorService;

    @Autowired
    ContractService contractService;

    Logger logger = LoggerFactory.getLogger(ContractorController.class);

    @PostMapping(value = "/contractors", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Contractor> createContractor(@RequestBody Contractor newContractor) {
        Contractor contractor = contractorService.createContractor(newContractor);
        logger.info("Successfully Created New Contractor: {}", contractor);
        return new ResponseEntity<>(contractor, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(value = "/contractors", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Contractor>> getAllContractors() {
        List<Contractor> contractorList = contractorService.getAllContractors();
        logger.info("Successfully Retrieved Contractor List: {}", contractorList);
        return new ResponseEntity<>(contractorList, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(value = "/contractors/{contractorId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Contractor> getContractorById(@PathVariable("contractorId") Long contractorId) throws RecordNotFoundException {
        Contractor contractor = contractorService.getContractorById(contractorId);
        logger.info("Successfully Retrieved Contractor: {}", contractor);
        return new ResponseEntity<>(contractor, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(value = "/contractors/{contractorId}/contracts", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Contract>> getAllContractsByContractorId(@PathVariable("contractorId") Long contractorId) throws RecordNotFoundException {
        Contractor contractor = contractorService.getContractorById(contractorId);
        logger.info("Successfully Retrieved Contractor: {}", contractor);
        List<Contract> contractList = contractService.getAllContractsByContractorId(contractor);
        logger.info("Successfully Retrieved Contracts List: {}", contractor);
        return new ResponseEntity<>(contractList, new HttpHeaders(), HttpStatus.OK);
    }
}
