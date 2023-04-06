package com.itpearls.agritomarketplace.screen.lottobuy;

import io.jmix.ui.screen.*;
import com.itpearls.agritomarketplace.entity.LotToBuy;

@UiController("LotToBuy.browse")
@UiDescriptor("lot-to-buy-browse.xml")
@LookupComponent("lotToBuysTable")
public class LotToBuyBrowse extends StandardLookup<LotToBuy> {
}