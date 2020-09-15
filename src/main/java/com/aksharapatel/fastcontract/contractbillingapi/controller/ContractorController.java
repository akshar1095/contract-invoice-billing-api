package com.aksharapatel.fastcontract.contractbillingapi.controller;

import com.aksharapatel.fastcontract.contractbillingapi.exception.RecordNotFoundException;
import com.aksharapatel.fastcontract.contractbillingapi.models.Contract;
import com.aksharapatel.fastcontract.contractbillingapi.models.Contractor;
import com.aksharapatel.fastcontract.contractbillingapi.models.Vendor;
import com.aksharapatel.fastcontract.contractbillingapi.services.ContractService;
import com.aksharapatel.fastcontract.contractbillingapi.services.ContractorService;
import com.aksharapatel.fastcontract.contractbillingapi.services.VendorService;
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

    @Autowired
    ContractService contractService;

    @Autowired
    VendorService vendorService;

    @PostMapping(value = "/contractor", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Contractor> createContractor(@RequestBody Contractor newContractor) {
        Contractor contractor = contractorService.createContractor(newContractor);

        return new ResponseEntity<>(contractor, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(value = "/contractor/{contractorId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Contractor> getContractorById(@PathVariable("contractorId") Long contractorId) throws RecordNotFoundException {
        Contractor contractor = contractorService.getContractorById(contractorId);

        return new ResponseEntity<>(contractor, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Contractor>> getAllContractors() {
        List<Contractor> contractorList = contractorService.getAllContractors();

        return new ResponseEntity<>(contractorList, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping(value = "/contractor/{contractorId}/{vendorId}/contracts", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Contract> createContract(@PathVariable("contractorId") Long contractorId,
                                   @PathVariable("vendorId") Long vendorId,
                                   @RequestBody Contract newContract) throws RecordNotFoundException {

        Contractor creatingContractor = contractorService.getContractorById(contractorId);
        Vendor assignedVendor = vendorService.getVendorById(vendorId);

        newContract.setContractor(creatingContractor);
        newContract.setVendor(assignedVendor);

        Contract contract = contractService.createContract(newContract);

        return new ResponseEntity<>(contract, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(value = "/contractor/{contractorId}/contracts", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Contract>> getAllContractsByContractorId(@PathVariable("contractorId") Long contractorId) throws RecordNotFoundException {
        Contractor contractsContractor = contractorService.getContractorById(contractorId);

        List<Contract> contractList = contractService.getAllContractsByContractorId(contractsContractor);

        return new ResponseEntity<>(contractList, new HttpHeaders(), HttpStatus.OK);
    }
}
