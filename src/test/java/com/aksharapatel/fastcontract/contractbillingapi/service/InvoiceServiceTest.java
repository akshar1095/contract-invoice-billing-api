package com.aksharapatel.fastcontract.contractbillingapi.service;

import com.aksharapatel.fastcontract.contractbillingapi.models.Invoice;
import com.aksharapatel.fastcontract.contractbillingapi.services.InvoiceService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class InvoiceServiceTest {

    @Autowired
    private InvoiceService invoiceService;

    @Test
    public void whenApplicationStarts_thenHibernateCreatesInitialInvoices() {
        List<Invoice> invoiceList = invoiceService.listAllInvoices();

        Assertions.assertEquals(invoiceList.size(), 3);
    }
}
