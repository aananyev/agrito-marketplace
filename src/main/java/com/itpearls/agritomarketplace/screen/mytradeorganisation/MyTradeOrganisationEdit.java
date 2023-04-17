package com.itpearls.agritomarketplace.screen.mytradeorganisation;

import com.itpearls.agritomarketplace.entity.User;
import io.jmix.core.EntityStates;
import io.jmix.core.security.CurrentAuthentication;
import io.jmix.ui.component.CheckBox;
import io.jmix.ui.component.EntityPicker;
import io.jmix.ui.screen.*;
import com.itpearls.agritomarketplace.entity.MyTradeOrganisation;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("MyTradeOrganisation.edit")
@UiDescriptor("my-trade-organisation-edit.xml")
@EditedEntityContainer("myTradeOrganisationDc")
public class MyTradeOrganisationEdit extends StandardEditor<MyTradeOrganisation> {
    @Autowired
    private EntityStates entityStates;
    @Autowired
    private CheckBox byersField;
    @Autowired
    private EntityPicker<User> ownerField;
    @Autowired
    private CheckBox myTradeOrganisationField;
    @Autowired
    private CurrentAuthentication currentAuthentication;

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        if (entityStates.isNew(getEditedEntity())) {
            byersField.setValue(true);
            ownerField.setValue((User) currentAuthentication.getUser());
            myTradeOrganisationField.setValue(true);
        }

    }
}