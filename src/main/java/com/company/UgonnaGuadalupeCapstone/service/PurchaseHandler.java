package com.company.UgonnaGuadalupeCapstone.service;


import com.company.UgonnaGuadalupeCapstone.dao.*;
import com.company.UgonnaGuadalupeCapstone.model.Invoice;
import com.company.UgonnaGuadalupeCapstone.model.PurchaseRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Invoice processPurchaseRequest(PurchaseRequest purchaseRequest) {

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
        invoice.setTax(getTax(purchaseRequest));
        invoice.setSubtotal(purchaseRequest.getQuantity() * getItemPrice(purchaseRequest));

        _invoiceDao.addInvoice(invoice);

        return invoice;
    }

    private double getProcessingFee(PurchaseRequest purchaseRequest) {
        double additionalProcessingFee = purchaseRequest.getQuantity() > 10
                ? 15.49
                : 0;
        return _processingDao.getProcessingFee(purchaseRequest.getItemType()) + additionalProcessingFee;
    }

    private double getTax(PurchaseRequest purchaseRequest) {
        double itemPrice = getItemPrice(purchaseRequest);
        return _taxDao.getTax(purchaseRequest.getState()) * itemPrice * purchaseRequest.getQuantity();
    }

    private double getItemPrice(PurchaseRequest purchaseRequest) {
        double itemPrice = 0;
        int itemID = purchaseRequest.getItemID();
        switch (purchaseRequest.getItemType()) {
            case "Consoles":
                itemPrice = _consoleDao.getConsole(itemID).getPrice().doubleValue();
                break;
            case "T-Shirts":
                itemPrice = _tShirtDao.getTshirt(itemID).getPrice().doubleValue();
                break;
            case "Games":
                itemPrice = _gamesDao.getGame(itemID).getPrice().doubleValue();
                break;
        }
        return itemPrice;
    }
}
