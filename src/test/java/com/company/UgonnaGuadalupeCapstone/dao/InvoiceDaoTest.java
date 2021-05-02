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
import org.springframework.boot.test.mock.mockito.MockBean;
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

    @MockBean
    GamesDao gamesDao;

    @Test
    public void add_get_invoice_test() {

        Invoice invoice = new Invoice();
        invoice.setName("Jack");
        invoice.setStreet("WD");
        invoice.setCity("Fsted");
        invoice.setState("VI");
        invoice.setZipcode("80051");
        invoice.setItemType("idk");
        invoice.setItemId(1);
        invoice.setUnitPrice(new BigDecimal("1.00"));
        invoice.setQuantity(1);
        invoice.setSubtotal(new BigDecimal("1.00"));
        invoice.setTax(new BigDecimal("1.00"));
        invoice.setProcessingFee(new BigDecimal("1.00"));
        //invoice.setProcessingFee(BigDecimal.valueOf(1.00));
        invoice.setTotal(new BigDecimal("1.00"));

        invoiceDao.addInvoice(invoice);

        Invoice retrievedInvoice = invoiceDao.getInvoice(invoice.getInvoiceID());

        Assert.assertEquals(invoice.getName(), retrievedInvoice.getName());
        Assert.assertEquals(invoice.getStreet(), retrievedInvoice.getStreet());
        Assert.assertEquals(invoice.getCity(), retrievedInvoice.getCity());
        Assert.assertEquals(invoice.getState(), retrievedInvoice.getState());
        Assert.assertEquals(invoice.getZipcode(), retrievedInvoice.getZipcode());
        Assert.assertEquals(invoice.getItemType(), retrievedInvoice.getItemType());
        Assert.assertEquals(invoice.getItemId(), retrievedInvoice.getItemId());
        Assert.assertEquals(invoice.getUnitPrice(), retrievedInvoice.getUnitPrice());
        Assert.assertEquals(invoice.getQuantity(), retrievedInvoice.getQuantity());
        Assert.assertEquals(invoice.getSubtotal(), retrievedInvoice.getSubtotal());
        Assert.assertEquals(invoice.getTax(), retrievedInvoice.getTax());
        Assert.assertEquals(invoice.getProcessingFee(), retrievedInvoice.getProcessingFee());
        Assert.assertEquals(invoice.getTotal(), retrievedInvoice.getTotal());
        Assert.assertEquals(invoice.getInvoiceID(), retrievedInvoice.getInvoiceID());

    }


}
