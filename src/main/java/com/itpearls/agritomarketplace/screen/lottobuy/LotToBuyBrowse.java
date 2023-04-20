package com.itpearls.agritomarketplace.screen.lottobuy;

import com.itpearls.agritomarketplace.AgritoGlobalValue;
import io.jmix.core.security.CurrentAuthentication;
import io.jmix.ui.component.CheckBox;
import io.jmix.ui.component.HasValue;
import io.jmix.ui.model.CollectionLoader;
import io.jmix.ui.screen.*;
import com.itpearls.agritomarketplace.entity.LotToBuy;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("LotToBuy.browse")
@UiDescriptor("lot-to-buy-browse.xml")
@LookupComponent("lotToBuysTable")
public class LotToBuyBrowse extends StandardLookup<LotToBuy> {
    @Autowired
    private CurrentAuthentication currentAuthentication;
    @Autowired
    private CollectionLoader<LotToBuy> lotToBuysDl;
    @Autowired
    private CheckBox onlyMyLotsCheckBox;

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        onlyMyLotsCheckBox.setValue(true);
    }
    @Subscribe("onlyMyLotsCheckBox")
    public void onOnlyMyLotsCheckBoxValueChange(HasValue.ValueChangeEvent<Boolean> event) {
        if (event.getValue()) {
            lotToBuysDl.setParameter("owner", AgritoGlobalValue.counterparty);
        } else {
            lotToBuysDl.removeParameter("owner");
        }

        lotToBuysDl.load();
    }

}