package com.company.train.web.screens.invoice;

import com.haulmont.cuba.gui.screen.*;
import com.company.train.entity.Invoice;

@UiController("train_Invoice.browse")
@UiDescriptor("invoice-browse.xml")
@LookupComponent("invoicesTable")
@LoadDataBeforeShow
public class InvoiceBrowse extends StandardLookup<Invoice> {
}