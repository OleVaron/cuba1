package com.company.train.web.screens.organization;

import com.haulmont.cuba.gui.screen.*;
import com.company.train.entity.Organization;

@UiController("train_Organization.browse")
@UiDescriptor("organization-browse.xml")
@LookupComponent("organizationsTable")
@LoadDataBeforeShow
public class OrganizationBrowse extends StandardLookup<Organization> {
}