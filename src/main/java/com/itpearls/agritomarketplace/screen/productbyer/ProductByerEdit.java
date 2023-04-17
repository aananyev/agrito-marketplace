package com.itpearls.agritomarketplace.screen.productbyer;

import com.itpearls.agritomarketplace.entity.User;
import io.jmix.core.EntityStates;
import io.jmix.core.security.CurrentAuthentication;
import io.jmix.ui.component.CheckBox;
import io.jmix.ui.component.EntityPicker;
import io.jmix.ui.screen.*;
import com.itpearls.agritomarketplace.entity.ProductByer;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("ProductByer.edit")
@UiDescriptor("product-byer-edit.xml")
@EditedEntityContainer("productByerDc")
public class ProductByerEdit extends StandardEditor<ProductByer> {
    @Autowired
    private CurrentAuthentication currentAuthentication;
    @Autowired
    private EntityStates entityStates;
    @Autowired
    private CheckBox byersField;
    @Autowired
    private EntityPicker<User> ownerField;
    @Autowired
    private CheckBox myTradeOrganisationField;

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
       if (entityStates.isNew(getEditedEntity())) {
           byersField.setValue(true);
           ownerField.setValue((User) currentAuthentication.getUser());
           myTradeOrganisationField.setValue(true);
       }
    }
}