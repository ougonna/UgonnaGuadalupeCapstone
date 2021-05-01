package com.company.UgonnaGuadalupeCapstone.viewModel;

import com.company.UgonnaGuadalupeCapstone.model.Invoice;

import java.math.BigDecimal;

public class InvoiceViewModel {
    private Invoice _invoice;

    private String _exception;

    public InvoiceViewModel(Invoice invoice) {
        _invoice = invoice;
    }

    public int getInvoiceID() {
        return _invoice.getInvoiceID();
    }

    public String getName() {
        return _invoice.getName();
    }

    public String getStreet() {
        return _invoice.getStreet();
    }

    public String getCity() {
        return _invoice.getCity();
    }

    // TODO: Finish or just return
    public String getState() {
        switch (_invoice.getState()){
            case "CA":
                return "California";
        }
        return  _invoice.getState();
    }

    public String getZipcode() {
        return _invoice.getZipcode();
    }

    public String getItemType() {
        return _invoice.getItemType();
    }

    public int getItemId() {
        return _invoice.getItemId();
    }

    public int getQuantity() {
        return _invoice.getQuantity();
    }

    public BigDecimal getUnitPrice() {
        return _invoice.getUnitPrice();
    }

    public String getSubtotal() {
        return "$" + _invoice.getSubtotal();
    }

    public BigDecimal getTax() {
        return _invoice.getTax();
    }

    public BigDecimal getProcessingFee() {
        return _invoice.getProcessingFee();
    }

    public BigDecimal getTotal() {
        return _invoice.getTotal();
    }

    //buildInvoiceViewModel
//    public Invoice buildInvoiceViewModel(Invoice invoice) {
//
//        Invoice ivm = new Invoice();
//        ivm.setInvoiceID(invoice.getInvoiceID());
//        ivm.setName(invoice.getName());
//        ivm.setStreet(invoice.getStreet());
//        ivm.setCity(invoice.getCity());
//        ivm.setState(invoice.getState());
//        ivm.setZipcode(invoice.getZipcode());
//        ivm.setItemType(invoice.getItemType());
//        ivm.setItemId(invoice.getItemId());
//        ivm.setUnitPrice(invoice.getUnitPrice());
//        ivm.setQuantity(invoice.getQuantity());
//        ivm.setSubtotal(invoice.getSubtotal());
//        ivm.setTax(invoice.getTax());
//        ivm.setProcessingFee(invoice.getProcessingFee());
//        ivm.setTotal(invoice.getTotal());
//        return ivm;
//    }

//    public String get_exception() {
//        return _exception;
//    }
//
//    public void set_exception(String _exception) {
//        switch (_exception){
//            case "Invalid state":
//                this._exception = _invoice.getState() + "is not a valid state";
//        }
//        this._exception = _exception;
//    }
}
