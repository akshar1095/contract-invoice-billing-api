package com.aksharapatel.fastcontract.contractbillingapi.service;

import com.aksharapatel.fastcontract.contractbillingapi.exception.RecordNotFoundException;
import com.aksharapatel.fastcontract.contractbillingapi.models.Vendor;
import com.aksharapatel.fastcontract.contractbillingapi.services.VendorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class VendorServiceTest {

    @Autowired
    private VendorService vendorService;

    @Test
    public void testGetAllVendors() {
        List<Vendor> vendorList = vendorService.getAllVendors();

        Assertions.assertEquals(vendorList.size(), 2);
    }

    @Test
    public void testGetVendorByIdFound() throws Exception {
        Long successVendorId = 1L;

        Vendor vendor = vendorService.getVendorById(successVendorId);
        Assertions.assertEquals(vendor.getVendorName(), "Test Vendor 1");
    }

    @Test
    public void testGetVendorByIdNotFound() {
        Assertions.assertThrows(RecordNotFoundException.class, () -> {
            Long failVendorId = 4L;
            vendorService.getVendorById(failVendorId);
        });
    }
}
