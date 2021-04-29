package com.company.UgonnaGuadalupeCapstone.service;

import com.company.UgonnaGuadalupeCapstone.model.Invoice;
import com.company.UgonnaGuadalupeCapstone.model.PurchaseRequest;

public interface IPurchaseHandler {
    Invoice processPurchaseRequest(PurchaseRequest purchaseRequest) throws Exception;
}
