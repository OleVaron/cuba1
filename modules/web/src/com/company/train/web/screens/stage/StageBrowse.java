package com.company.train.web.screens.stage;

import com.haulmont.cuba.gui.screen.*;
import com.company.train.entity.Stage;

@UiController("train_Stage.browse")
@UiDescriptor("stage-browse.xml")
@LookupComponent("stagesTable")
@LoadDataBeforeShow
public class StageBrowse extends StandardLookup<Stage> {
}