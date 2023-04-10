package com.itpearls.agritomarketplace.screen.lotforsell;

import io.jmix.ui.UiComponents;
import io.jmix.ui.component.Component;
import io.jmix.ui.component.Label;
import io.jmix.ui.screen.*;
import com.itpearls.agritomarketplace.entity.LotForSell;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("LotForSell.browse")
@UiDescriptor("lot-for-sell-browse.xml")
@LookupComponent("lotForSellsTable")
public class LotForSellBrowse extends StandardLookup<LotForSell> {
    @Autowired
    private UiComponents uiComponents;

    @Install(to = "lotForSellsTable.contractSum", subject = "columnGenerator")
    private Component lotForSellsTableContractSumColumnGenerator(LotForSell lotForSell) {
        Label label = uiComponents.create(Label.class);
        label.setValue(lotForSell.getPrice().doubleValue()
                * lotForSell.getProductAmount().doubleValue());
        return label;
    }
}