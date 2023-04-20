package com.itpearls.agritomarketplace.screen.lotforsell;

import com.itpearls.agritomarketplace.AgritoGlobalValue;
import io.jmix.core.EntityStates;
import io.jmix.core.security.CurrentAuthentication;
import io.jmix.ui.UiComponents;
import io.jmix.ui.component.CheckBox;
import io.jmix.ui.component.Component;
import io.jmix.ui.component.HasValue;
import io.jmix.ui.component.Label;
import io.jmix.ui.model.CollectionLoader;
import io.jmix.ui.screen.*;
import com.itpearls.agritomarketplace.entity.LotForSell;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("LotForSell.browse")
@UiDescriptor("lot-for-sell-browse.xml")
@LookupComponent("lotForSellsTable")
public class LotForSellBrowse extends StandardLookup<LotForSell> {
    @Autowired
    private UiComponents uiComponents;
    @Autowired
    private CurrentAuthentication currentAuthentication;
    @Autowired
    private EntityStates entityStates;
    @Autowired
    private CheckBox onlyMyLotsCheckBox;
    @Autowired
    private CollectionLoader<LotForSell> lotForSellsDl;

    @Install(to = "lotForSellsTable.contractSum", subject = "columnGenerator")
    private Component lotForSellsTableContractSumColumnGenerator(LotForSell lotForSell) {
        Label label = uiComponents.create(Label.class);
        label.setValue(lotForSell.getPrice().doubleValue()
                * lotForSell.getProductAmount().doubleValue());
        return label;
    }

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        onlyMyLotsCheckBox.setValue(true);
    }

    @Subscribe("onlyMyLotsCheckBox")
    public void onOnlyMyLotsCheckBoxValueChange(HasValue.ValueChangeEvent<Boolean> event) {
        if (event.getValue()) {
            lotForSellsDl.setParameter("owner", AgritoGlobalValue.counterparty);
        } else {
            lotForSellsDl.removeParameter("owner");
        }

        lotForSellsDl.load();
    }
}