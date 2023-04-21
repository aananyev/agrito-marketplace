package com.itpearls.agritomarketplace.screen.lotforsell;

import com.itpearls.agritomarketplace.AgritoGlobalValue;
import com.itpearls.agritomarketplace.entity.Counterparty;
import com.itpearls.agritomarketplace.entity.TradingLotType;
import io.jmix.core.EntityStates;
import io.jmix.ui.component.CheckBox;
import io.jmix.ui.component.EntityPicker;
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
    @Autowired
    private EntityPicker<Counterparty> agriculturalManufacturerField;

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        if(entityStates.isNew(getEditedEntity())) {
            sellField.setValue(true);
            agriculturalManufacturerField.setValue(AgritoGlobalValue.myHousehold);
            getEditedEntity().setTradingLotType(TradingLotType.SALE);
        }
    }
}