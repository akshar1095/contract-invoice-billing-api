package com.aksharapatel.fastcontract.contractbillingapi.services;

import com.aksharapatel.fastcontract.contractbillingapi.exception.RecordNotFoundException;
import com.aksharapatel.fastcontract.contractbillingapi.models.Contract;
import com.aksharapatel.fastcontract.contractbillingapi.repositories.ContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContractService {

    @Autowired
    private ContractRepository contractRepository;

    public Contract createContract(Contract contract) { return contractRepository.save(contract); }

    public List<Contract> getAllContracts() { return contractRepository.findAll(); }

    public Contract getContractById(Long contractId) throws RecordNotFoundException {

        Optional<Contract> contract = contractRepository.findById(contractId);

        return contract.orElseThrow(() -> new RecordNotFoundException("No contract record exists for the given contract id"));
    }
}
