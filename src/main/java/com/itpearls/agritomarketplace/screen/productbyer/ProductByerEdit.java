package com.itpearls.agritomarketplace.screen.productbyer;

import io.jmix.core.EntityStates;
import io.jmix.ui.component.CheckBox;
import io.jmix.ui.screen.*;
import com.itpearls.agritomarketplace.entity.ProductByer;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("ProductByer.edit")
@UiDescriptor("product-byer-edit.xml")
@EditedEntityContainer("productByerDc")
public class ProductByerEdit extends StandardEditor<ProductByer> {
    @Autowired
    private EntityStates entityStates;
    @Autowired
    private CheckBox byersCheckBox;

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
       if (entityStates.isNew(getEditedEntity())) {
           byersCheckBox.setValue(true);
       }
    }
}