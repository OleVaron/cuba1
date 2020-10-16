package com.company.train.web.screens.stage;

import com.haulmont.cuba.gui.screen.*;
import com.company.train.entity.Stage;

@UiController("train_Stage.edit")
@UiDescriptor("stage-edit.xml")
@EditedEntityContainer("stageDc")
@LoadDataBeforeShow
public class StageEdit extends StandardEditor<Stage> {
}