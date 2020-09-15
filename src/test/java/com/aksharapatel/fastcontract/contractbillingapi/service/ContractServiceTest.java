package com.aksharapatel.fastcontract.contractbillingapi.service;

import com.aksharapatel.fastcontract.contractbillingapi.models.Contract;
import com.aksharapatel.fastcontract.contractbillingapi.services.ContractService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ContractServiceTest {

    @Autowired
    private ContractService contractService;

    @Test
    public void whenApplicationStarts_thenHibernateCreatesInitialContracts() {
        List<Contract> contractList = contractService.listAllContracts();

        Assertions.assertEquals(contractList.size(), 3);
    }
}
