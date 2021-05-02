package com.company.UgonnaGuadalupeCapstone.service;

import com.company.UgonnaGuadalupeCapstone.dao.*;
import com.company.UgonnaGuadalupeCapstone.model.*;
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
    public void processPurchaseRequest_ValidPurchaseRequestForConsole_ShouldReturnInvoice() throws Exception {

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

        double additionalProcessingFee = request.getQuantity() > 10
                ? 15.49
                : 0;
        BigDecimal expectedTotal = expectedSubtotal
                .add(expectedTax)
                .add(_processingDao.getProcessingFee(Console.ITEM_TYPE))
                .add(BigDecimal.valueOf(additionalProcessingFee));

        Assert.assertEquals(console.getPrice(), invoice.getUnitPrice());
        Assert.assertEquals(expectedSubtotal, invoice.getSubtotal());
        Assert.assertEquals(expectedTax, invoice.getTax());
        Assert.assertEquals(expectedTotal, invoice.getTotal());

    }

    @Test
    //Sales tax applies only to the cost of the items.
    //Sales tax does not apply to any processing fees for an invoice.
    public void processPurchaseRequest_TaxApplyOnlyByCostOfItem_TaxNotApplyToProcessingFee() throws Exception {


        Tshirt tshirt = new Tshirt();
        tshirt.setSize("small");
        tshirt.setColor("blue");
        tshirt.setDescription("something");
        tshirt.setQuantity(5);
        tshirt.setPrice(new BigDecimal("34.50"));

        Tshirt storedTshirt = _tShirtDao.addTshirt(tshirt);

        PurchaseRequest request = new PurchaseRequest();
        request.setName("abname");
        request.setStreet("abstreet");
        request.setCity("abcity");
        request.setState("AL");
        request.setZip("abzip");
        request.setItemType(Tshirt.ITEM_TYPE);
        request.setItemID(storedTshirt.getTshirtId());
        request.setQuantity(2);

        Invoice ph = _purchaseHandler.processPurchaseRequest(request);
        BigDecimal expectedTax = _taxDao
                .getTax(request.getState())
                .multiply(tshirt.getPrice())
                .multiply(BigDecimal.valueOf(request.getQuantity()));
        Assert.assertEquals(expectedTax,ph.getTax());

    }

//    The processing fee is applied only once per order regardless
//    of the number of items in the order unless the number of items

    @Test
    public void processPurchaseRequest_OnlyApplyProcessingFeeOnce() throws Exception {
        Games game = new Games();
        game.setTitle("Game Title");
        game.setEsrbRating("4.55");
        game.setDescription("This is a test game");
        game.setPrice(new BigDecimal("3.44"));
        game.setStudio("studio test");
        game.setQuantity(50);

        Games storedGame = _gamesDao.addGame(game);

        PurchaseRequest request = new PurchaseRequest();
        request.setName("abname");
        request.setStreet("abstreet");
        request.setCity("abcity");
        request.setState("AL");
        request.setZip("abzip");
        request.setItemType(Games.ITEM_TYPE);
        request.setItemID(storedGame.getGameId());
        request.setQuantity(2);

        Invoice invoice = _purchaseHandler.processPurchaseRequest(request);

        Assert.assertEquals(_processingDao.getProcessingFee(Games.ITEM_TYPE), invoice.getProcessingFee());

    }

    //    on the order is greater than 10 in which case an additional processing
//    fee of $15.49 is applied to the order.

    @Test
    public void processPurchaseRequest_OnlyApplyAdditionalProcessingFee() throws Exception {
        Games game = new Games();
        game.setTitle("Game Title");
        game.setEsrbRating("4.55");
        game.setDescription("This is a test game");
        game.setPrice(new BigDecimal("3.44"));
        game.setStudio("studio test");
        game.setQuantity(50);

        Games storedGame = _gamesDao.addGame(game);

        PurchaseRequest request = new PurchaseRequest();
        request.setName("abname");
        request.setStreet("abstreet");
        request.setCity("abcity");
        request.setState("AL");
        request.setZip("abzip");
        request.setItemType(Games.ITEM_TYPE);
        request.setItemID(storedGame.getGameId());
        request.setQuantity(11);

        Invoice invoice = _purchaseHandler.processPurchaseRequest(request);

        double additionalProcessingFee = request.getQuantity() > 10
                ? 15.49
                : 0;
        BigDecimal expectedProcessingFee = _processingDao
                .getProcessingFee(Games.ITEM_TYPE)
                .add(BigDecimal.valueOf(additionalProcessingFee));
        Assert.assertEquals(expectedProcessingFee, invoice.getProcessingFee());

    }

    //The order process logic must properly update the quantity on hand for the item in the order.

    @Test
    public void processPurchaseRequest_ShouldUpdateQuantity() throws Exception {
        Games game = new Games();
        game.setTitle("Game Title");
        game.setEsrbRating("4.55");
        game.setDescription("This is a test game");
        game.setPrice(new BigDecimal("3.44"));
        game.setStudio("studio test");
        game.setQuantity(50);

        Games storedGame = _gamesDao.addGame(game);

        PurchaseRequest request = new PurchaseRequest();
        request.setName("abname");
        request.setStreet("abstreet");
        request.setCity("abcity");
        request.setState("AL");
        request.setZip("abzip");
        request.setItemType(Games.ITEM_TYPE);
        request.setItemID(storedGame.getGameId());
        request.setQuantity(11);

        _purchaseHandler.processPurchaseRequest(request);


        Assert.assertEquals(game.getQuantity() - request.getQuantity(), _gamesDao.getGame(storedGame.getGameId()).getQuantity());

    }

//    Order quantity must be greater than zero.
    @Test
    public void processPurchaseRequest_QuantityNotGreaterThan0_ShouldThrowException() {
        Games game = new Games();
        game.setTitle("Game Title");
        game.setEsrbRating("4.55");
        game.setDescription("This is a test game");
        game.setPrice(new BigDecimal("3.44"));
        game.setStudio("studio test");
        game.setQuantity(50);

        Games storedGame = _gamesDao.addGame(game);

        PurchaseRequest request = new PurchaseRequest();
        request.setName("abname");
        request.setStreet("abstreet");
        request.setCity("abcity");
        request.setState("AL");
        request.setZip("abzip");
        request.setItemType(Games.ITEM_TYPE);
        request.setItemID(storedGame.getGameId());
        request.setQuantity(0);

        try {
            _purchaseHandler.processPurchaseRequest(request);
            Assert.fail("Should have thrown an exception since quantity less than 1");
        } catch (Exception e) {
            Assert.assertEquals("Invalid quantity", e.getMessage());
        }
    }


//    Order quantity must be less than or equal to the number of items on hand in inventory.
@Test
public void processPurchaseRequest_InventoryQuantityLessThanOrderQuantity_ShouldThrowException() {
    Games game = new Games();
    game.setTitle("Game Title");
    game.setEsrbRating("4.55");
    game.setDescription("This is a test game");
    game.setPrice(new BigDecimal("3.44"));
    game.setStudio("studio test");
    game.setQuantity(5);

    Games storedGame = _gamesDao.addGame(game);

    PurchaseRequest request = new PurchaseRequest();
    request.setName("abname");
    request.setStreet("abstreet");
    request.setCity("abcity");
    request.setState("AL");
    request.setZip("abzip");
    request.setItemType(Games.ITEM_TYPE);
    request.setItemID(storedGame.getGameId());
    request.setQuantity(50);

    try {
        _purchaseHandler.processPurchaseRequest(request);
        Assert.fail("Should have thrown an exception since quantity less");
    } catch (Exception e) {
        Assert.assertEquals("Insufficient inventory", e.getMessage());
    }
}

//    Order must contain a valid state code.
@Test
public void processPurchaseRequest_InvalidState_ShouldThrowException() {
    Games game = new Games();
    game.setTitle("Game Title");
    game.setEsrbRating("4.55");
    game.setDescription("This is a test game");
    game.setPrice(new BigDecimal("3.44"));
    game.setStudio("studio test");
    game.setQuantity(50);

    Games storedGame = _gamesDao.addGame(game);

    PurchaseRequest request = new PurchaseRequest();
    request.setName("abname");
    request.setStreet("abstreet");
    request.setCity("abcity");
    request.setState("blah");
    request.setZip("abzip");
    request.setItemType(Games.ITEM_TYPE);
    request.setItemID(storedGame.getGameId());
    request.setQuantity(10);

    try {
        _purchaseHandler.processPurchaseRequest(request);
        Assert.fail("Should have thrown an exception since State is invalid");
    } catch (Exception e) {
        Assert.assertEquals("Invalid state", e.getMessage());
    }
}




}
