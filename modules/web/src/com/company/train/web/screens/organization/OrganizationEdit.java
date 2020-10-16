package com.company.train.web.screens.organization;

import com.haulmont.cuba.gui.screen.*;
import com.company.train.entity.Organization;

@UiController("train_Organization.edit")
@UiDescriptor("organization-edit.xml")
@EditedEntityContainer("organizationDc")
@LoadDataBeforeShow
public class OrganizationEdit extends StandardEditor<Organization> {
}