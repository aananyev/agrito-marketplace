package com.itpearls.agritomarketplace.screen.tradinglot;

import io.jmix.ui.screen.*;
import com.itpearls.agritomarketplace.entity.TradingLot;

@UiController("TradingLot.edit")
@UiDescriptor("trading-lot-edit.xml")
@EditedEntityContainer("tradingLotDc")
public class TradingLotEdit extends StandardEditor<TradingLot> {
}