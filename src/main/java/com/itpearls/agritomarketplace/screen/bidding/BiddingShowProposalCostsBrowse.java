package com.itpearls.agritomarketplace.screen.bidding;

import com.itpearls.agritomarketplace.entity.BiddingStatus;
import com.itpearls.agritomarketplace.entity.TradingLot;
import io.jmix.ui.model.CollectionLoader;
import io.jmix.ui.screen.*;
import com.itpearls.agritomarketplace.entity.Bidding;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("BiddingShowProposalCosts.browse")
@UiDescriptor("bidding-show-proposal-costs-browse.xml")
@LookupComponent("biddingsTable")
public class BiddingShowProposalCostsBrowse extends StandardLookup<Bidding> {
    private TradingLot tradingLot;
    @Autowired
    private CollectionLoader<Bidding> biddingsDl;

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        biddingsDl.setParameter("biddingStatus", BiddingStatus.COUNTER_OFFER);
        biddingsDl.load();
    }

    public void setTradingLot(TradingLot tradingLot) {
        this.tradingLot = tradingLot;
        biddingsDl.setParameter("tradingLot", tradingLot);
        biddingsDl.load();
    }
}