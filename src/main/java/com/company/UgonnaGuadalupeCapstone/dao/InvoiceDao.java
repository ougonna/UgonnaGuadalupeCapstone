package com.company.UgonnaGuadalupeCapstone.dao;

import com.company.UgonnaGuadalupeCapstone.model.Invoice;

public interface InvoiceDao {

    int addInvoice(Invoice invoice);
    Invoice getInvoice(int id);

}

