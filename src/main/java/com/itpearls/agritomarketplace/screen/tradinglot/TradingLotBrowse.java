package com.itpearls.agritomarketplace.screen.tradinglot;

import io.jmix.ui.screen.*;
import com.itpearls.agritomarketplace.entity.TradingLot;

@UiController("TradingLot.browse")
@UiDescriptor("trading-lot-browse.xml")
@LookupComponent("tradingLotsTable")
public class TradingLotBrowse extends StandardLookup<TradingLot> {
}