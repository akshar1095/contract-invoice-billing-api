package com.aksharapatel.fastcontract.contractbillingapi.service;

import com.aksharapatel.fastcontract.contractbillingapi.exception.RecordNotFoundException;
import com.aksharapatel.fastcontract.contractbillingapi.models.Invoice;
import com.aksharapatel.fastcontract.contractbillingapi.services.InvoiceService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class InvoiceServiceTest {

    @Autowired
    private InvoiceService invoiceService;

    @Disabled("Disabled until create invoice is created")
    @Test
    public void testGetAllInvoices() {
        List<Invoice> invoiceList = invoiceService.getAllInvoices();

        Assertions.assertEquals(invoiceList.size(), 3);
    }

    @Disabled("Disabled until create invoice is created")
    @Test
    public void testGetInvoiceByIdFound() throws Exception {
        Long successInvoiceId = 1L;

        Invoice invoice = invoiceService.getInvoiceById(successInvoiceId);
        Assertions.assertEquals(invoice.getInvoiceValue(), 10000);
        Assertions.assertEquals(invoice.getInvoiceVoid(), false);
    }

    @Test
    public void testGetInvoiceByIdNotFound() {
        Assertions.assertThrows(RecordNotFoundException.class, () -> {
           Long failInvoiceId = 4L;
           invoiceService.getInvoiceById(failInvoiceId);
        });
    }
}
