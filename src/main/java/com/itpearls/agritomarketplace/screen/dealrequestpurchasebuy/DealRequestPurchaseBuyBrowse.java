package com.itpearls.agritomarketplace.screen.dealrequestpurchasebuy;

import com.itpearls.agritomarketplace.AgritoGlobalValue;
import com.itpearls.agritomarketplace.entity.Bidding;
import com.itpearls.agritomarketplace.entity.BiddingStatus;
import com.itpearls.agritomarketplace.entity.LotForSell;
import com.itpearls.agritomarketplace.screen.bidding.BiddingEdit;
import io.jmix.core.DataManager;
import io.jmix.core.Metadata;
import io.jmix.ui.ScreenBuilders;
import io.jmix.ui.UiComponents;
import io.jmix.ui.component.*;
import io.jmix.ui.model.CollectionLoader;
import io.jmix.ui.screen.*;
import com.itpearls.agritomarketplace.entity.DealRequestPurchaseBuy;
import io.jmix.ui.screen.LookupComponent;
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
    @Autowired
    private Metadata metadata;
    @Autowired
    private GroupTable<DealRequestPurchaseBuy> dealRequestPurchaseBuysTable;
    @Autowired
    private ScreenBuilders screenBuilders;
    @Autowired
    private CollectionLoader<DealRequestPurchaseBuy> dealRequestPurchaseBuysDl;
    @Autowired
    private CheckBox onlyMyDealRequests;

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

        retLabel.setValue(getReservedAmount(dealRequestPurchaseBuy.getLotForSell(), dealRequestPurchaseBuy));

        return retLabel;
    }

    BigDecimal getReservedAmount(LotForSell lotForSell,
                                 DealRequestPurchaseBuy dealRequestPurchaseBuy) {
        BigDecimal resreved = BigDecimal.ZERO;

        try {
            resreved = dataManager.loadValue("select sum(e.amount) " +
                            "from Bidding e " +
                            "where e.tradingLot = :tradingLot " +
                            "and e.dealRequest = :dealRequest " +
                            "and e.biddingStatus = :biddingStatus", BigDecimal.class)
                    .parameter("biddingStatus", BiddingStatus.APPROVE)
                    .parameter("dealRequest", dealRequestPurchaseBuy)
                    .parameter("tradingLot", lotForSell)
                    .one();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        return resreved;
    }

    @Subscribe("startBiddingButton")
    public void onStartBiddingButtonClick(Button.ClickEvent event) {
        Bidding bidding = metadata.create(Bidding.class);

        bidding.setParentBidding(null);
        bidding.setBiddingStatus(BiddingStatus.COUNTER_OFFER);
        bidding.setAmount(dealRequestPurchaseBuysTable.getSingleSelected().getAmount());
        bidding.setCouterparty(AgritoGlobalValue.counterparty);
        bidding.setTradingLot(dealRequestPurchaseBuysTable.getSingleSelected().getLotForSell());

        screenBuilders.editor(Bidding.class, this)
                .editEntity(bidding)
                .withScreenClass(BiddingEdit.class)
                .withAfterCloseListener(biddingEditAfterScreenCloseEvent -> {
                    dealRequestPurchaseBuysDl.load();
                    dealRequestPurchaseBuysTable.repaint();
                })
                .build()
                .show();
    }

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        onlyMyDealRequests.setValue(true);
    }

    @Subscribe("onlyMyDealRequests")
    public void onOnlyMyDealRequestsValueChange(HasValue.ValueChangeEvent<Boolean> event) {
        if (event.getValue()) {
            dealRequestPurchaseBuysDl.setParameter("owner_seller", AgritoGlobalValue.counterparty);
            dealRequestPurchaseBuysDl.setParameter("owner_buyer", AgritoGlobalValue.counterparty);
        } else {
            dealRequestPurchaseBuysDl.removeParameter("owner_seller");
            dealRequestPurchaseBuysDl.removeParameter("owner_buyer");

        }

        dealRequestPurchaseBuysDl.load();
    }


}