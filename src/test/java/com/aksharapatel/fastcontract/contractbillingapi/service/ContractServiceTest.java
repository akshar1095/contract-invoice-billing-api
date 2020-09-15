package com.aksharapatel.fastcontract.contractbillingapi.service;

import com.aksharapatel.fastcontract.contractbillingapi.exception.RecordNotFoundException;
import com.aksharapatel.fastcontract.contractbillingapi.models.Contract;
import com.aksharapatel.fastcontract.contractbillingapi.services.ContractService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ContractServiceTest {

    @Autowired
    private ContractService contractService;

    @Disabled("Disabled until create contract is created")
    @Test
    public void testGetAllContracts() {
        List<Contract> contractList = contractService.getAllContracts();

        Assertions.assertEquals(contractList.size(), 3);
    }

    @Disabled("Disabled until create contract is created")
    @Test
    public void testGetContractByIdFound() throws Exception {
        Long successContractId = 1L;

        Contract contract = contractService.getContractById(successContractId);
        Assertions.assertEquals(contract.getContractName(), "Test Contract 1");
        Assertions.assertEquals(contract.getContractDescription(), "Test Contract Description 1");
        Assertions.assertEquals(contract.getContractValue(), 100000);
    }

    @Test()
    public void testGetContractByIdNotFound() {
        Assertions.assertThrows(RecordNotFoundException.class, () -> {
            Long failContractId = 4L;
            contractService.getContractById(failContractId);
        });
    }
}
