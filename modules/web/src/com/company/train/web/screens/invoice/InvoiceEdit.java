package com.company.train.web.screens.invoice;

import com.haulmont.cuba.gui.screen.*;
import com.company.train.entity.Invoice;

@UiController("train_Invoice.edit")
@UiDescriptor("invoice-edit.xml")
@EditedEntityContainer("invoiceDc")
@LoadDataBeforeShow
public class InvoiceEdit extends StandardEditor<Invoice> {
}