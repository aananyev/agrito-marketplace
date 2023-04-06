package com.itpearls.agritomarketplace.screen.lotforsell;

import io.jmix.core.EntityStates;
import io.jmix.ui.component.CheckBox;
import io.jmix.ui.screen.*;
import com.itpearls.agritomarketplace.entity.LotForSell;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("LotForSell.edit")
@UiDescriptor("lot-for-sell-edit.xml")
@EditedEntityContainer("lotForSellDc")
public class LotForSellEdit extends StandardEditor<LotForSell> {
    @Autowired
    private EntityStates entityStates;
    @Autowired
    private CheckBox sellField;

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        if(entityStates.isNew(getEditedEntity())) {
            sellField.setValue(true);
        }
    }
}