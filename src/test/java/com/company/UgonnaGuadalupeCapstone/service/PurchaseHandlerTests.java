package com.company.UgonnaGuadalupeCapstone.service;

import com.company.UgonnaGuadalupeCapstone.dao.*;
import com.company.UgonnaGuadalupeCapstone.model.Console;
import com.company.UgonnaGuadalupeCapstone.model.Invoice;
import com.company.UgonnaGuadalupeCapstone.model.PurchaseRequest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PurchaseHandlerTests {

    @Autowired
    private ProcessingDao _processingDao;
    @Autowired
    private TaxDao _taxDao;
    @Autowired
    private ConsoleDao _consoleDao;
    @Autowired
    private GamesDao _gamesDao;
    @Autowired
    private TshirtDao _tShirtDao;
    @Autowired
    private InvoiceDao _invoiceDao;
    @Autowired
    private IPurchaseHandler _purchaseHandler;

    @Test
    public void processPurchaseRequest_ValidPurchaseRequestForConsole_ShouldReturnInvoice() {

        Console console = new Console();
        console.setModel("Toyota");
        console.setManufacturer("Microsoft");
        console.setMemoryAmount("35");
        console.setProcessor("Intel");
        console.setPrice(new BigDecimal("34.50"));
        console.setQuantity(10);

        Console storedConsole = _consoleDao.addConsole(console);

        PurchaseRequest request = new PurchaseRequest();
        request.setName("abname");
        request.setStreet("abstreet");
        request.setCity("abcity");
        request.setState("AL");
        request.setZip("abzip");
        request.setItemType(Console.ITEM_TYPE);
        request.setItemID(storedConsole.getConsoleId());
        request.setQuantity(2);

        try {
            Invoice invoice = _purchaseHandler.processPurchaseRequest(request);
            Assert.assertEquals(request.getName(), invoice.getName());
            Assert.assertEquals(request.getStreet(), invoice.getStreet());
            Assert.assertEquals(request.getCity(), invoice.getCity());
            Assert.assertEquals(request.getState(), invoice.getState());
            Assert.assertEquals(request.getZip(), invoice.getZipcode());
            Assert.assertEquals(request.getItemType(), invoice.getItemType());
            Assert.assertEquals(request.getItemID(), invoice.getItemId());
            Assert.assertEquals(request.getQuantity(), invoice.getQuantity());

            BigDecimal expectedSubtotal = console.getPrice()
                    .multiply(BigDecimal.valueOf(request.getQuantity()));
            BigDecimal expectedTax = _taxDao.getTax(request.getState())
                    .multiply(BigDecimal.valueOf(request.getQuantity()))
                    .multiply(console.getPrice());
            BigDecimal expectedTotal = expectedSubtotal.add(expectedTax);

            Assert.assertEquals(expectedSubtotal, invoice.getSubtotal());
            Assert.assertEquals(expectedTax, invoice.getTax());
            Assert.assertEquals(expectedTotal, invoice.getTotal());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
