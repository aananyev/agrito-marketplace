package com.itpearls.agritomarketplace.screen.lottobuy;

import io.jmix.core.EntityStates;
import io.jmix.ui.component.CheckBox;
import io.jmix.ui.screen.*;
import com.itpearls.agritomarketplace.entity.LotToBuy;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("LotToBuy.edit")
@UiDescriptor("lot-to-buy-edit.xml")
@EditedEntityContainer("lotToBuyDc")
public class LotToBuyEdit extends StandardEditor<LotToBuy> {
    @Autowired
    private EntityStates entityStates;
    @Autowired
    private CheckBox buyField;

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
       if (entityStates.isNew(getEditedEntity())) {
           buyField.setValue(true);
       }
    }
}