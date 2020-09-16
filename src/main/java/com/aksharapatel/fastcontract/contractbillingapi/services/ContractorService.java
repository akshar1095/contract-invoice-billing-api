package com.aksharapatel.fastcontract.contractbillingapi.services;

import com.aksharapatel.fastcontract.contractbillingapi.exception.RecordNotFoundException;
import com.aksharapatel.fastcontract.contractbillingapi.models.Contractor;
import com.aksharapatel.fastcontract.contractbillingapi.repositories.ContractorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContractorService {

    @Autowired
    private ContractorRepository contractorRepository;

    public Contractor createContractor(Contractor contractor) {
        return contractorRepository.save(contractor);
    }

    public List<Contractor> getAllContractors() {
        return contractorRepository.findAll();
    }

    public Contractor getContractorById(Long contractorId) throws RecordNotFoundException {
        Optional<Contractor> contractor = contractorRepository.findById(contractorId);
        return contractor.orElseThrow(() -> new RecordNotFoundException("Contractor with given " + contractorId + " not found"));
    }
}
