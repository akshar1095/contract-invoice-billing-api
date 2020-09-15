package com.aksharapatel.fastcontract.contractbillingapi.service;

import com.aksharapatel.fastcontract.contractbillingapi.exception.RecordNotFoundException;
import com.aksharapatel.fastcontract.contractbillingapi.models.Contractor;
import com.aksharapatel.fastcontract.contractbillingapi.services.ContractorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ContractorServiceTest {

    @Autowired
    private ContractorService contractorService;

    @Test
    public void testGetAllContractors() {
        List<Contractor> contractorList = contractorService.getAllContractors();

        Assertions.assertEquals(contractorList.size(), 3);
    }

    @Test
    public void testGetContractorByIdFound() throws Exception {
        Long successContractorId = 1L;

        Contractor contractor = contractorService.getContractorById(successContractorId);
        Assertions.assertEquals(contractor.getContractorName(), "Test Contractor 1");
    }

    @Test
    public void testGetContractorByIdNotFound() {
        Assertions.assertThrows(RecordNotFoundException.class, () -> {
            Long failContractorId = 4L;
            contractorService.getContractorById(failContractorId);
        });
    }
}
