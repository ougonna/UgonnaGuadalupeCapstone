package com.company.UgonnaGuadalupeCapstone.controller;

import com.company.UgonnaGuadalupeCapstone.model.Invoice;
import com.company.UgonnaGuadalupeCapstone.model.PurchaseRequest;
import com.company.UgonnaGuadalupeCapstone.service.IPurchaseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class InvoiceController {
    private final IPurchaseHandler _purchaseHandler;

    @Autowired
    public InvoiceController(IPurchaseHandler purchaseHandler) {
        this._purchaseHandler = purchaseHandler;
    }

    @RequestMapping(value = "/purchase", method = RequestMethod.GET)
    public String index(){
        return "Hi";
    }

    @RequestMapping(value = "/purchase", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Invoice makePurchase(@RequestBody PurchaseRequest purchaseRequest){
        Invoice invoice = _purchaseHandler.processPurchaseRequest(purchaseRequest);
        return invoice;
    }
}
