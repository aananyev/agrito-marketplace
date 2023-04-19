package com.itpearls.agritomarketplace.screen.dealrequestsaleoffer;

import com.itpearls.agritomarketplace.AgritoGlobalValue;
import com.itpearls.agritomarketplace.entity.Counterparty;
import io.jmix.core.EntityStates;
import io.jmix.ui.component.EntityPicker;
import io.jmix.ui.screen.*;
import com.itpearls.agritomarketplace.entity.DealRequestSaleOffer;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("DealRequestSaleOffer.edit")
@UiDescriptor("deal-request-sale-offer-edit.xml")
@EditedEntityContainer("dealRequestSaleOfferDc")
public class DealRequestSaleOfferEdit extends StandardEditor<DealRequestSaleOffer> {
    @Autowired
    private EntityStates entityStates;
    @Autowired
    private EntityPicker<Counterparty> productSellerField;

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        if (entityStates.isNew(getEditedEntity())) {
            productSellerField.setValue(AgritoGlobalValue.counterparty);
        }
    }
}