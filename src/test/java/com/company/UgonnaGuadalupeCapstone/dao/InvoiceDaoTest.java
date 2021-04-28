package com.company.UgonnaGuadalupeCapstone.dao;

import com.company.UgonnaGuadalupeCapstone.model.Console;
import com.company.UgonnaGuadalupeCapstone.model.Games;
import com.company.UgonnaGuadalupeCapstone.model.Invoice;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InvoiceDaoTest {

    @Autowired
    InvoiceDao invoiceDao;

    @Test
    public void getInvoice() {
        Invoice invoice = new Invoice();
        invoice.setName("Jack");
        invoice.setStreet("WD");
        invoice.setCity("Fsted");
        invoice.setState("VI");
        invoice.setZipcode("80051");
        invoice.setItemType("idk");
        invoice.setItemId(1);
        invoice.setUnitPrice(1);
        invoice.setQuantity(1);
        invoice.setSubtotal(1);
        invoice.setTax(1);
        invoice.setProcessingFee(1);
        invoice.setTotal(1);

        invoiceDao.addInvoice(invoice);

        Invoice retrievedInvoice = invoiceDao.getInvoice(invoice.getInvoiceID());

        Assert.assertEquals(invoice.getName(), retrievedInvoice.getName());
        Assert.assertEquals(invoice.getStreet(), retrievedInvoice.getStreet());
        Assert.assertEquals(invoice.getCity(), retrievedInvoice.getCity());
        Assert.assertEquals(invoice.getState(), retrievedInvoice.getState());
        Assert.assertEquals(invoice.getZipcode(), retrievedInvoice.getZipcode());
        Assert.assertEquals(invoice.getItemType(), retrievedInvoice.getItemType());
        Assert.assertEquals(invoice.getItemId(), retrievedInvoice.getItemId());
        Assert.assertEquals(invoice.getUnitPrice(), retrievedInvoice.getUnitPrice(), 0);
        Assert.assertEquals(invoice.getQuantity(), retrievedInvoice.getQuantity());
        Assert.assertEquals(invoice.getSubtotal(), retrievedInvoice.getSubtotal(), 0);
        Assert.assertEquals(invoice.getTax(), retrievedInvoice.getTax(), 0);
        Assert.assertEquals(invoice.getProcessingFee(), retrievedInvoice.getProcessingFee(), 0);
        Assert.assertEquals(invoice.getTotal(), retrievedInvoice.getTotal(), 0);
        Assert.assertEquals(invoice.getInvoiceID(), retrievedInvoice.getInvoiceID());

    }


}
