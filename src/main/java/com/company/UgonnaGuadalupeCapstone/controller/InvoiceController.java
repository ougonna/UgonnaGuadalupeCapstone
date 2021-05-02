package com.company.UgonnaGuadalupeCapstone.controller;

import com.company.UgonnaGuadalupeCapstone.dao.InvoiceDao;
import com.company.UgonnaGuadalupeCapstone.model.Invoice;
import com.company.UgonnaGuadalupeCapstone.model.PurchaseRequest;
import com.company.UgonnaGuadalupeCapstone.service.IPurchaseHandler;
import com.company.UgonnaGuadalupeCapstone.viewModel.InvoiceViewModel;
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

    @Autowired
    InvoiceDao invoiceDao;

    @RequestMapping(value = "/purchase", method = RequestMethod.GET)
    public String index(){
        return "Hi";
    }

    @RequestMapping(value = "/purchase", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Invoice makePurchase(@RequestBody PurchaseRequest purchaseRequest){
        Invoice invoice = null;
        try {
            invoice = _purchaseHandler.processPurchaseRequest(purchaseRequest);
        } catch (Exception e) {
            InvoiceViewModel vm = new InvoiceViewModel(null);
            //vm.set_exception(e.getMessage());
            // TODO: return client friendly message
            e.printStackTrace();
        }
        //return new InvoiceViewModel(invoice);
        return invoice;
    }

    //get invoice by id
    @RequestMapping(value = "/invoice/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Invoice getInvoice(@PathVariable int id){
        System.out.println("getting invoice id = " + id);
        return invoiceDao.getInvoice(id);
    }
}
