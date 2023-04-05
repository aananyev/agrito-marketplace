package com.itpearls.agritomarketplace.screen.agriculturalmanufacturer;

import io.jmix.core.EntityStates;
import io.jmix.ui.component.CheckBox;
import io.jmix.ui.screen.*;
import com.itpearls.agritomarketplace.entity.AgriculturalManufacturer;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("AgriculturalManufacturer.edit")
@UiDescriptor("agricultural-manufacturer-edit.xml")
@EditedEntityContainer("agriculturalManufacturerDc")
public class AgriculturalManufacturerEdit extends StandardEditor<AgriculturalManufacturer> {
    @Autowired
    private EntityStates entityStates;
    @Autowired
    private CheckBox manufacturerCheckBox;

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        if(entityStates.isNew(getEditedEntity())) {
            manufacturerCheckBox.setValue(true);
        }
    }
}