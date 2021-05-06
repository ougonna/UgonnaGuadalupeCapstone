package com.company.UgonnaGuadalupeCapstone.controller;

import com.company.UgonnaGuadalupeCapstone.dao.InvoiceDao;
import com.company.UgonnaGuadalupeCapstone.model.Invoice;
import com.company.UgonnaGuadalupeCapstone.model.PurchaseRequest;
import com.company.UgonnaGuadalupeCapstone.service.IPurchaseHandler;
import com.company.UgonnaGuadalupeCapstone.viewModel.InvoiceViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
public class InvoiceController {
    private final IPurchaseHandler _purchaseHandler;

    @Autowired
    public InvoiceController(IPurchaseHandler purchaseHandler) {
        this._purchaseHandler = purchaseHandler;
    }

    @Autowired
    InvoiceDao invoiceDao;

    @RequestMapping(value = "/purchase", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @Secured({"ROLE_USER", "ROLE_ADMIN", "ROLE_MANAGER"})
    public Invoice makePurchase(@RequestBody @Valid PurchaseRequest purchaseRequest){
        Invoice invoice = null;
        try {
            invoice = _purchaseHandler.processPurchaseRequest(purchaseRequest);
        } catch (Exception e) {
            InvoiceViewModel vm = new InvoiceViewModel(null);
            vm.set_exception(e.getMessage());
            e.printStackTrace();
        }
        return invoice;
    }

    //get invoice by id
    @RequestMapping(value = "/invoice/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @Secured({"ROLE_ADMIN", "ROLE_MANAGER"})
    public Invoice getInvoice(@PathVariable int id){
        System.out.println("getting invoice id = " + id);
        return invoiceDao.getInvoice(id);
    }
}
