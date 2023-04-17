package com.itpearls.agritomarketplace.screen.myhousehold;

import com.itpearls.agritomarketplace.entity.User;
import io.jmix.core.EntityStates;
import io.jmix.core.security.CurrentAuthentication;
import io.jmix.ui.component.CheckBox;
import io.jmix.ui.component.EntityPicker;
import io.jmix.ui.screen.*;
import com.itpearls.agritomarketplace.entity.MyHousehold;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("MyHousehold.edit")
@UiDescriptor("my-household-edit.xml")
@EditedEntityContainer("myHouseholdDc")
public class MyHouseholdEdit extends StandardEditor<MyHousehold> {
    @Autowired
    private EntityStates entityStates;
    @Autowired
    private CurrentAuthentication currentAuthentication;
    @Autowired
    private CheckBox myHouseholdField;
    @Autowired
    private CheckBox agriculturalManufacturerCheckBox;
    @Autowired
    private EntityPicker<User> ownerField;

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        if (entityStates.isNew(getEditedEntity())) {
            ownerField.setValue((User) currentAuthentication.getUser());
            myHouseholdField.setValue(true);
            agriculturalManufacturerCheckBox.setValue(true);
        }
    }
}