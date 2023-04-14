package com.itpearls.agritomarketplace.screen.dealrequestpurchasebuy;

import com.itpearls.agritomarketplace.entity.BiddingStatus;
import com.itpearls.agritomarketplace.entity.LotForSell;
import io.jmix.core.DataManager;
import io.jmix.ui.UiComponents;
import io.jmix.ui.component.Component;
import io.jmix.ui.component.Label;
import io.jmix.ui.screen.*;
import com.itpearls.agritomarketplace.entity.DealRequestPurchaseBuy;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

@UiController("DealRequestPurchaseBuy.browse")
@UiDescriptor("deal-request-purchase-buy-browse.xml")
@LookupComponent("dealRequestPurchaseBuysTable")
public class DealRequestPurchaseBuyBrowse extends StandardLookup<DealRequestPurchaseBuy> {
    @Autowired
    private UiComponents uiComponents;
    @Autowired
    private MessageBundle messageBundle;
    @Autowired
    private DataManager dataManager;

    @Install(to = "dealRequestPurchaseBuysTable.total", subject = "columnGenerator")
    private Component dealRequestPurchaseBuysTableTotalColumnGenerator(DealRequestPurchaseBuy dealRequestPurchaseBuy) {
        Label retLabel = uiComponents.create(Label.class);
        retLabel.setValue(dealRequestPurchaseBuy.getProposalCost().doubleValue()
                * dealRequestPurchaseBuy.getAmount().doubleValue()
                + " "
                + messageBundle.getMessage("msgRub"));
        return retLabel;
    }

    @Install(to = "dealRequestPurchaseBuysTable.reservedAmount", subject = "columnGenerator")
    private Component dealRequestPurchaseBuysTableReservedAmountColumnGenerator(DealRequestPurchaseBuy dealRequestPurchaseBuy) {
        Label retLabel = uiComponents.create(Label.class);

        retLabel.setValue(getReservedAmount(dealRequestPurchaseBuy.getLotForSell()));

        return retLabel;
    }

    BigDecimal getReservedAmount(LotForSell lotForSell) {
        BigDecimal resreved = BigDecimal.ZERO;

        try {
            resreved = dataManager.loadValue("select sum(e.amount) " +
                            "from Bidding e " +
                            "where e.tradingLot = :tradingLot and e.biddingStatus = :biddingStatus", BigDecimal.class)
                    .parameter("biddingStatus", BiddingStatus.APPROVE)
                    .parameter("tradingLot", lotForSell)
                    .one();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        return resreved;
    }
}