package com.aksharapatel.fastcontract.contractbillingapi.services;

import com.aksharapatel.fastcontract.contractbillingapi.exception.RecordNotFoundException;
import com.aksharapatel.fastcontract.contractbillingapi.models.Contract;
import com.aksharapatel.fastcontract.contractbillingapi.models.Contractor;
import com.aksharapatel.fastcontract.contractbillingapi.models.Invoice;
import com.aksharapatel.fastcontract.contractbillingapi.models.Vendor;
import com.aksharapatel.fastcontract.contractbillingapi.repositories.ContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContractService {

    @Autowired
    private ContractRepository contractRepository;

    public Contract createContract(Contract contract) { return contractRepository.save(contract); }

    public List<Contract> getAllContracts() { return contractRepository.findAll(); }

    public List<Contract> getAllContractsByContractor(Contractor contractor) { return contractRepository.findByContractor(contractor); }

    public List<Contract> getAllContractsByVendorId(Vendor vendor) { return contractRepository.findByVendor(vendor); }

    public Contract getContractById(Long contractId) throws RecordNotFoundException {
        Optional<Contract> contract = contractRepository.findById(contractId);

        return contract.orElseThrow(() -> new RecordNotFoundException("No contract record exists for the given contract id"));
    }

    public Double getValueRemainingByContractId(Long contractId) throws RecordNotFoundException {
        Optional<Contract> contract = contractRepository.findById(contractId);
        if(contract.isPresent()) {
            return contract.get().getContractValue() - contract.get().getInvoices().stream().filter(invoice -> !invoice.getInvoiceVoid()).collect(Collectors.toList())
                    .stream().mapToDouble(Invoice::getInvoiceValue).sum();
        } else {
            throw new RecordNotFoundException("No contract record exists for the given contract id");
        }
    }
}
