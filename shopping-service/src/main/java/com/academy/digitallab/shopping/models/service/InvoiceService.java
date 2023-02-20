package com.academy.digitallab.shopping.models.service;

import java.util.List;

import com.academy.digitallab.shopping.models.entity.Invoice;

public interface InvoiceService {
	
    public List<Invoice> findInvoiceAll();

    public Invoice createInvoice(Invoice invoice);
    
    public Invoice updateInvoice(Invoice invoice);
    
    public Invoice deleteInvoice(Invoice invoice);

    public Invoice getInvoice(Long id);
}
