package com.company.UgonnaGuadalupeCapstone.service;


import com.company.UgonnaGuadalupeCapstone.dao.*;
import com.company.UgonnaGuadalupeCapstone.model.*;
import com.company.UgonnaGuadalupeCapstone.controller.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PurchaseHandler implements IPurchaseHandler {
    private ProcessingDao _processingDao;
    private TaxDao _taxDao;
    private ConsoleDao _consoleDao;
    private GamesDao _gamesDao;
    private TshirtDao _tShirtDao;
    private InvoiceDao _invoiceDao;

    @Autowired
    public PurchaseHandler(ProcessingDao _processingDao, TaxDao _taxDao, ConsoleDao _consoleDao, GamesDao _gamesDao, TshirtDao _tShirtDao, InvoiceDao _invoiceDao) {
        this._processingDao = _processingDao;
        this._taxDao = _taxDao;
        this._consoleDao = _consoleDao;
        this._gamesDao = _gamesDao;
        this._tShirtDao = _tShirtDao;
        this._invoiceDao = _invoiceDao;
    }

    @Override
    public Invoice processPurchaseRequest(PurchaseRequest purchaseRequest) throws Exception {

        Invoice invoice = new Invoice();
        int itemID = purchaseRequest.getItemID();
        invoice.setName(purchaseRequest.getName());
        invoice.setStreet(purchaseRequest.getStreet());
        invoice.setCity(purchaseRequest.getCity());
        invoice.setState(purchaseRequest.getState());
        invoice.setZipcode(purchaseRequest.getZip());
        invoice.setItemType(purchaseRequest.getItemType());
        invoice.setItemId(itemID);
        invoice.setQuantity(purchaseRequest.getQuantity());
        invoice.setProcessingFee(getProcessingFee(purchaseRequest));

        IItem item = getItem(purchaseRequest);

        invoice.setUnitPrice(item.getPrice());
        //sets the subtotal by multiplying the quantity from the purchase request by the item price
        invoice.setSubtotal(item.getPrice().multiply(new BigDecimal(purchaseRequest.getQuantity())));

        //throws exception if state is invalid
        try {
            invoice.setTax(getTax(purchaseRequest)); //shouldn't this be purchaseRequest.getState()? setTax takes in two variables: State and tax.  getTax takes in only the state
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Invalid state");
        }
         //sets the total for the order by adding subtotal, tax and processing fee
        invoice.setTotal(invoice.getSubtotal().add(invoice.getTax()).add(invoice.getProcessingFee()));

        //throws exception if quantity of items ordered is less than 1
        if (purchaseRequest.getQuantity() < 1)
            throw new IllegalArgumentException("Invalid quantity");

        //throws exception if quantity requested is more than quantity in inventory
        if (purchaseRequest.getQuantity() > item.getQuantity())
            throw new IllegalArgumentException("Insufficient inventory");
            //throw new illegalArgumentException("Insufficient inventory");

        //adds invoice to invoice table
        _invoiceDao.addInvoice(invoice);

        item.setQuantity(item.getQuantity() - invoice.getQuantity());
        updateItem(item);

        return invoice;
    }

    //update item
    private void updateItem(IItem item) {
        if (item instanceof Console)
            _consoleDao.updateConsole((Console) item);
        if (item instanceof Tshirt)
            _tShirtDao.updateTshirt((Tshirt) item);
        if (item instanceof Games)
            _gamesDao.updateGame((Games) item);
    }

    //processing fee
    private BigDecimal getProcessingFee(PurchaseRequest purchaseRequest) {
        double additionalProcessingFee = purchaseRequest.getQuantity() > 10
                ? 15.49
                : 0;
        return _processingDao.getProcessingFee(purchaseRequest.getItemType()).add(BigDecimal.valueOf(additionalProcessingFee));
    }

    //gets the price of the item based on the purchase request
    private BigDecimal getTax(PurchaseRequest purchaseRequest) {
        BigDecimal itemPrice = getItem(purchaseRequest).getPrice();
        return _taxDao
                .getTax(purchaseRequest.getState())
                .multiply(itemPrice)
                .multiply(new BigDecimal(purchaseRequest.getQuantity()));
    }

    //gets item based on the item_type
    private IItem getItem(PurchaseRequest purchaseRequest) {
        IItem item = null;
        int itemID = purchaseRequest.getItemID();
        switch (purchaseRequest.getItemType()) {
            case Console.ITEM_TYPE:
                item = _consoleDao.getConsole(itemID);
                break;
            case Tshirt.ITEM_TYPE:
                item = _tShirtDao.getTshirt(itemID);
                break;
            case Games.ITEM_TYPE:
                item = _gamesDao.getGame(itemID);
                break;
        }
        return item;
    }

//    //find invoice
//    public Invoice findInvoice(int id){
//        //get invoice object first
//        Invoice invoice = _invoiceDao.getInvoice(id);
//
//        return buildInvoiceViewModel(invoice);
//    }
//
//   //console api
//    public Console saveConsole(Console console){
//        return _consoleDao.addConsole(console);
//    }
//    public Console findConsole(int id){
//        return _consoleDao.getConsole(id);
//    }
//    public List<Console> findAllConsoles(){
//        return _consoleDao.getAllConsoles();
//    }
//    public void updateConsole(Console console){
//        _consoleDao.updateConsole(console);
//    }
//    public void removeConsole(int id){
//        _consoleDao.deleteConsole(id);
//    }
//
//    //game api
//    public Games saveGame(Games games){
//        return _gamesDao.addGame(games);
//    }
//    public Games findGames(int id){
//        return _gamesDao.getGame(id);
//    }
//    public List<Games> findAllGames(){
//        return _gamesDao.getAllGames();
//    }
//    public void updateGame (Games games){
//        _gamesDao.updateGame(games);
//    }
//    public void removeGame (int id){
//        _gamesDao.deleteGame(id);
//    }
//
//    //t-shirt api
//    public Tshirt saveTshirt(Tshirt tshirt){
//        return _tShirtDao.addTshirt(tshirt);
//    }
//    public Tshirt findTshirt(int id){
//        return _tShirtDao.getTshirt(id);
//    }
//    public List<Tshirt> findAllTshirts(){
//        return _tShirtDao.getAllTshirt();
//    }
//    public void updateTshirt (Tshirt tshirt){
//        _tShirtDao.updateTshirt(tshirt);
//    }
//    public void removeTshirt (int id){
//        _tShirtDao.deleteTshirt(id);
//    }



}
