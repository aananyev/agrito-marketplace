package com.itpearls.agritomarketplace.screen.dealrequestsaleoffer;

import io.jmix.ui.UiComponents;
import io.jmix.ui.component.Component;
import io.jmix.ui.component.Label;
import io.jmix.ui.screen.*;
import com.itpearls.agritomarketplace.entity.DealRequestSaleOffer;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("DealRequestSaleOffer.browse")
@UiDescriptor("deal-request-sale-offer-browse.xml")
@LookupComponent("dealRequestSaleOffersTable")
public class DealRequestSaleOfferBrowse extends StandardLookup<DealRequestSaleOffer> {
    @Autowired
    private UiComponents uiComponents;
    @Autowired
    private MessageBundle messageBundle;

    @Install(to = "dealRequestSaleOffersTable.total", subject = "columnGenerator")
    private Component dealRequestSaleOffersTableTotalColumnGenerator(DealRequestSaleOffer dealRequestSaleOffer) {
        Label retLabel = uiComponents.create(Label.class);
        retLabel.setValue(dealRequestSaleOffer.getProposalCost().doubleValue()
                * dealRequestSaleOffer.getAmount().doubleValue()
                + " "
                + messageBundle.getMessage("msgRub"));
        return retLabel;
    }
}