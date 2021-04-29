package com.company.UgonnaGuadalupeCapstone.service;


import com.company.UgonnaGuadalupeCapstone.dao.*;
import com.company.UgonnaGuadalupeCapstone.model.*;
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
        invoice.setSubtotal(item.getPrice().multiply(new BigDecimal(purchaseRequest.getQuantity())));

        try {
            invoice.setTax(getTax(purchaseRequest));
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Invalid state");
        }
        invoice.setTotal(invoice.getSubtotal().add(invoice.getTax()).add(invoice.getProcessingFee()));

        if (purchaseRequest.getQuantity() < 1)
            throw new Exception("Invalid quantity");

        if (purchaseRequest.getQuantity() > item.getQuantity())
            throw new Exception("Insufficient inventory");

        _invoiceDao.addInvoice(invoice);

        updateItem(item);

        return invoice;
    }

    private void updateItem(IItem item) {
        if (item instanceof Console)
            _consoleDao.updateConsole((Console) item);
        if (item instanceof Tshirt)
            _tShirtDao.updateTshirt((Tshirt) item);
        if (item instanceof Games)
            _gamesDao.updateGame((Games) item);
    }

    private BigDecimal getProcessingFee(PurchaseRequest purchaseRequest) {
        double additionalProcessingFee = purchaseRequest.getQuantity() > 10
                ? 15.49
                : 0;
        return _processingDao.getProcessingFee(purchaseRequest.getItemType()).add(BigDecimal.valueOf(additionalProcessingFee));
    }

    private BigDecimal getTax(PurchaseRequest purchaseRequest) {
        BigDecimal itemPrice = getItem(purchaseRequest).getPrice();
        return _taxDao
                .getTax(purchaseRequest.getState())
                .multiply(itemPrice)
                .multiply(new BigDecimal(purchaseRequest.getQuantity()));
    }

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
}
