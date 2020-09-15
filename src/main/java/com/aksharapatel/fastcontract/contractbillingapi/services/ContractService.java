package com.aksharapatel.fastcontract.contractbillingapi.services;

import com.aksharapatel.fastcontract.contractbillingapi.exception.RecordNotFoundException;
import com.aksharapatel.fastcontract.contractbillingapi.models.Contract;
import com.aksharapatel.fastcontract.contractbillingapi.repositories.ContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ContractService {

    @Autowired
    private ContractRepository contractRepository;

    public List<Contract> getAllContracts() {

        List<Contract> contractList = contractRepository.findAll();

        if(contractList.size() > 0) {
            return contractList;
        } else {
            return new ArrayList<>();
        }
    }

    public Contract getContractById(Long contractId) throws RecordNotFoundException {

        Optional<Contract> contract = contractRepository.findById(contractId);

        if(contract.isPresent()) {
            return contract.get();
        } else {
            throw new RecordNotFoundException("No contract record exists for the given contract id");
        }
    }
}
