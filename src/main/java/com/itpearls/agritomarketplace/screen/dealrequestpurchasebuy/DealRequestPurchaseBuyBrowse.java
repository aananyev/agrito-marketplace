package com.itpearls.agritomarketplace.screen.dealrequestpurchasebuy;

import io.jmix.ui.UiComponents;
import io.jmix.ui.component.Component;
import io.jmix.ui.component.Label;
import io.jmix.ui.screen.*;
import com.itpearls.agritomarketplace.entity.DealRequestPurchaseBuy;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("DealRequestPurchaseBuy.browse")
@UiDescriptor("deal-request-purchase-buy-browse.xml")
@LookupComponent("dealRequestPurchaseBuysTable")
public class DealRequestPurchaseBuyBrowse extends StandardLookup<DealRequestPurchaseBuy> {
    @Autowired
    private UiComponents uiComponents;
    @Autowired
    private MessageBundle messageBundle;

    @Install(to = "dealRequestPurchaseBuysTable.total", subject = "columnGenerator")
    private Component dealRequestPurchaseBuysTableTotalColumnGenerator(DealRequestPurchaseBuy dealRequestPurchaseBuy) {
        Label retLabel = uiComponents.create(Label.class);
        retLabel.setValue(dealRequestPurchaseBuy.getProposalCost().doubleValue()
                * dealRequestPurchaseBuy.getAmount().doubleValue()
                + " "
                + messageBundle.getMessage("msgRub"));
        return retLabel;
    }
}