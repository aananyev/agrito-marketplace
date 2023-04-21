package com.itpearls.agritomarketplace.screen.dealrequestsaleoffer;

import com.itpearls.agritomarketplace.entity.*;
import io.jmix.core.DataManager;
import io.jmix.ui.UiComponents;
import io.jmix.ui.component.Component;
import io.jmix.ui.component.Label;
import io.jmix.ui.screen.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

@UiController("DealRequestSaleOffer.browse")
@UiDescriptor("deal-request-sale-offer-browse.xml")
@LookupComponent("dealRequestSaleOffersTable")
public class DealRequestSaleOfferBrowse extends StandardLookup<DealRequestSaleOffer> {
    @Autowired
    private DataManager dataManager;

    @Install(to = "dealRequestSaleOffersTable.reservedAmount", subject = "columnGenerator")
    private Component dealRequestSaleOffersTableReservedAmountColumnGenerator(DealRequestSaleOffer dealRequestSaleOffer) {
        Label retLabel = uiComponents.create(Label.class);

        retLabel.setValue(getReservedAmount(dealRequestSaleOffer.getLotForBuy(), dealRequestSaleOffer));

        return retLabel;
    }

    BigDecimal getReservedAmount(LotToBuy lotToBuy,
                                 DealRequestSaleOffer dealRequestSaleOffer) {
        BigDecimal resreved = BigDecimal.ZERO;

        try {
            resreved = dataManager.loadValue("select sum(e.amount) " +
                            "from Bidding e " +
                            "where e.tradingLot = :tradingLot " +
                            "and e.dealRequest = :dealRequest " +
                            "and e.biddingStatus = :biddingStatus", BigDecimal.class)
                    .parameter("biddingStatus", BiddingStatus.APPROVE)
                    .parameter("dealRequest", dealRequestSaleOffer)
                    .parameter("tradingLot", lotToBuy)
                    .one();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        return resreved;
    }
    @Autowired
    private UiComponents uiComponents;
    @Autowired
    private MessageBundle messageBundle;

    @Install(to = "dealRequestSaleOffersTable.total", subject = "columnGenerator")
    private Component dealRequestSaleOffersTableTotalColumnGenerator(DealRequestSaleOffer dealRequestSaleOffer) {
        Label retLabel = uiComponents.create(Label.class);
        retLabel.setValue(String.format("%.2f", dealRequestSaleOffer.getProposalCost().doubleValue()
                * dealRequestSaleOffer.getAmount().doubleValue())
                + " "
                + messageBundle.getMessage("msgRub"));
        return retLabel;
    }
}